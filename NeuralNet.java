//read through for typos
//

import java.util.List;
import java.util.Arrays;

//Layton Rosenfeld

public class NeuralNet {
	
	private int numInputs;
	private int numHiddenNeurons;
	private int numOutputNeurons;
	private double learningRate;
	private Neuron[] hiddenNeuronList;
	private Neuron[] outputNeuronList;

		
	public NeuralNet(int numInputs, int numHiddenNeurons, int numOutputNeurons, double learningRate) {
		this.numInputs = numInputs;
		this.numHiddenNeurons = numHiddenNeurons;
		this.numOutputNeurons = numOutputNeurons;
		this.learningRate = learningRate;
		this.hiddenNeuronList = new Neuron[numHiddenNeurons];
		this.outputNeuronList = new Neuron[numOutputNeurons];

		for (int i=0; i<numHiddenNeurons; i+=1) {
			Neuron n = new Neuron(numInputs);
			n.assignRandomWeights();
			hiddenNeuronList[i] = n;
		}

		for (int i=0; i<numOutputNeurons; i+=1) {
			Neuron n = new Neuron(numHiddenNeurons);
			n.assignRandomWeights();
			outputNeuronList[i] = n;
		}
		
	}
	
	
	public double[] getOutputs(double[] inputs, Neuron[] neuronLayer) {
		double[] outputs = new double[neuronLayer.length];
		for (int i=0; i<neuronLayer.length; i+=1) {
			outputs[i] = neuronLayer[i].calcOutput(inputs);
		}
		
		return outputs;
		
		
	}
	
	public int classifyOneEx(Example e) {
		double[] outputs = getOutputs(getOutputs(e.getInputList(), hiddenNeuronList), outputNeuronList);
		
		double greatestOutput = 0;
		int greatestOutputNeuronIndex = -1;
		for (int j=0; j<outputs.length; j+=1) {
			double output = outputs[j];
			if (output > greatestOutput) {
				greatestOutput = output;
				greatestOutputNeuronIndex = j;				
			}
		}
		return greatestOutputNeuronIndex;
	}
		

	public double calculateAccuracy(Example[] exampleList) {
		int correctCount = 0;
		for (Example x : exampleList) {
			if (x.getExpectedOutput(classifyOneEx(x)) == 1.0) {
				correctCount+=1;
			}
		}

		return correctCount * 1.0/exampleList.length;
	}
	
	public void learnOneEx(Example e) {
		
		double[] outputs = getOutputs(getOutputs(e.getInputList(), hiddenNeuronList), outputNeuronList);


		//calc output error signal
		
		for (int o=0; o<numOutputNeurons; o+=1) {
			outputNeuronList[o].es((e.getExpectedOutput(o) - outputs[o]) * outputs[o] * (1-outputs[o]));
		}
		
		//calc hidden error signal
		for (int h=0; h<numHiddenNeurons; h+= 1) {
			double hiddenES = 0;
			for (int o=0; o<numOutputNeurons; o+=1) {
				hiddenES += outputNeuronList[o].weights()[h] * outputNeuronList[o].getES();
			}
			hiddenNeuronList[h].es(hiddenES * hiddenNeuronList[h].output() * (1-hiddenNeuronList[h].output()));
		}
		//update output weights
		for (int o=0; o<numOutputNeurons; o+=1) {
			for (int h=0; h<numHiddenNeurons; h+= 1) {
				outputNeuronList[o].weights()[h] = outputNeuronList[o].weights()[h] + outputNeuronList[o].getES() * hiddenNeuronList[h].output() * learningRate;
			}
			//bias
			outputNeuronList[o].bias(outputNeuronList[o].getES() * learningRate);
		}
		//update hidden weights 
		for (int h=0; h<numHiddenNeurons; h+= 1) {
			for (int i=0; i<e.getInputList().length; i+=1) {
				hiddenNeuronList[h].weights()[i] = hiddenNeuronList[h].weights()[i] + hiddenNeuronList[h].getES() * e.getInputList()[i] * learningRate;
				
			}
			//bias
			hiddenNeuronList[h].bias(hiddenNeuronList[h].getES() * learningRate);			
		}
	}
	
	public void printUpdate(int epochs, double accuracy) {
		System.out.println("epochs = " + epochs + ", training accuracy = " + accuracy);
	}

	public void learnManyEx(Example[] tExList, Example[] testExList, double desiredAccuracy, double percentValidate,  int numEpochsReported) {
		long startTime = System.currentTimeMillis();
		

		Example[] validateList; 
		Example[] trainingList;
		
		
		//if no validation
		if (percentValidate == 0) {
			validateList = tExList;
			trainingList = tExList;
			
		}
		
		else {
			int numValidate = (int) (percentValidate*tExList.length);
			validateList = Arrays.copyOfRange(tExList, 0, numValidate);
			trainingList = Arrays.copyOfRange(tExList, numValidate, tExList.length);
		}
		
		
		
		int epochs = 0;
		double currentAccuracy = calculateAccuracy(validateList);
		
		while (currentAccuracy < desiredAccuracy) {
			for (Example e : trainingList) {
				learnOneEx(e);
			}
			
		
			
			if (epochs % numEpochsReported == 0) {
				printUpdate(epochs, currentAccuracy);
			}
			currentAccuracy = calculateAccuracy(validateList);

			
			if (epochs > 30000) {
				System.out.println("***cut off, called again");
				for (Neuron n: hiddenNeuronList) {
					n.assignRandomWeights();
				}
				for (Neuron n: outputNeuronList) {
					n.assignRandomWeights();
				}
				epochs = 0;
				
			}
			epochs +=1;
			
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("------");
		System.out.println("reached desired trianing accuracy of " + desiredAccuracy + " at " + epochs + " epochs in " + elapsedTime + " milliseconds");
		System.out.println("testing accuracy = " + calculateAccuracy(testExList));

		
		
	}
}
