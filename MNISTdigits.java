
public class MNISTdigits {

	public MNISTdigits(double learningRate, double desiredAccuracy, int numHiddenNeurons) {
		NeuralNet MNISTdigitsNeuralNet = new NeuralNet(784, numHiddenNeurons, 10, learningRate);
		
		System.out.println(" ---------- running MNISTdigits ---------- ");
		System.out.println("   -   learning rate: " + learningRate);
		System.out.println("   -   desired training accuracy: " + desiredAccuracy);
		System.out.println("   -   number of hidden neurons: " + numHiddenNeurons);
		System.out.println();
		
		
		Example[] trainingExamples = ReadMNISTFiles.ReadMNISTFiles("train-labels-idx1-ubyte", "train-images-idx3-ubyte");
		Example[] testingExamples = ReadMNISTFiles.ReadMNISTFiles("t10k-labels-idx1-ubyte", "t10k-images-idx3-ubyte");
		

		
		MNISTdigitsNeuralNet.learnManyEx(trainingExamples, testingExamples, desiredAccuracy, 0, 1);
		

	}
	
}
