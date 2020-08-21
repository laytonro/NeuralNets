//Layton Rosenfeld


public class ANDexample {
		
	public ANDexample(double learningRate, double desiredAccuracy, int numHiddenNeurons) {
		NeuralNet andNeuralNet = new NeuralNet(2, numHiddenNeurons, 2, learningRate);
	
		System.out.println(" ---------- running AND ---------- ");
		System.out.println("   - learning rate: " + learningRate);
		System.out.println("   - desired training accuracy: " + desiredAccuracy);
		System.out.println("   - number of hidden neurons: " + numHiddenNeurons);
		
		Example[] andExamples = new Example[4];
	
		andExamples[0] =  new Example(1, new double[]{1,1});
		andExamples[1] = new Example(0, new double[]{1,0});
		andExamples[2] =new Example(0, new double[]{0,1});
		andExamples[3] = new Example(0, new double[]{0,0});

		andNeuralNet.learnManyEx(andExamples, andExamples, desiredAccuracy, 0, 10);
	}
	
	

			
}
