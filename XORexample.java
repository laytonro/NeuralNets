
public class XORexample {
	public XORexample(double learningRate, double desiredAccuracy, int numHiddenNeurons) {
		NeuralNet xORNeuralNet = new NeuralNet(2, numHiddenNeurons, 2, learningRate);
		
		
		System.out.println(" ---------- running XOR ---------- ");
		System.out.println("   - learning rate: " + learningRate);
		System.out.println("   - desired training accuracy: " + desiredAccuracy);
		System.out.println("   - number of hidden neurons: " + numHiddenNeurons);
		
		
		Example[] xORExamples = new Example[4];
	
		xORExamples[0] =  new Example(0, new double[]{1,1});
		xORExamples[3] = new Example(0, new double[]{0,0});
		xORExamples[1] = new Example(1, new double[]{1,0});
		xORExamples[2] =new Example(1, new double[]{0,1});


		xORNeuralNet.learnManyEx(xORExamples, xORExamples, desiredAccuracy, 0, 10000);
	}
	
	
	
}
