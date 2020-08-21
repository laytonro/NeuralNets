//Layton Rosenfeld

public class Main {
	
	public static void main(String[] args) {
		

	
	//ANDexample ex1 = new ANDexample(8.0, 1.0, 8);
	
	//XORexample ex2 = new XORexample(2.0, 1.0, 500);
		
	//digitExamples ex3 = new digitExamples(.1, .98, 150, .3);
	
	//MNISTdigits ex4 = new MNISTdigits(.05, .93, 70);
		
		
		NeuralNet guiNet = new NeuralNet(784, 70, 10, .05);
		GUI gui = new GUI(guiNet, ReadMNISTFiles.ReadMNISTFiles("train-labels-idx1-ubyte", "train-images-idx3-ubyte"));
	}
}
