import java.util.Arrays;
import java.util.Random;

//Layton Rosenfeld


public class Neuron {
	private double[] weights;
	private double output;
	private double bias;
	private double errorSignal;
	
	
	
	public Neuron(int numInputs) {
		this.weights = new double[numInputs];
	}
	
	public double getBias() {
		return this.bias;
	}
	
	public void assignRandomWeights() {
		Random random = new Random();
		for (int w = 0; w < weights.length; w+= 1) {
			double r = random.nextDouble();
			this.weights[w] = -.05 + (r/10);
			
		}
		double biasWeight = random.nextDouble();
		this.bias = -.05 + (biasWeight/10);
	}
	
	public void bias(double b) {
		this.bias += b;
	}
	
	public double[] weights() {
		return this.weights;
	}
	
	public double output() {
		return this.output;
	}
	
	public double getES() {
		return this.errorSignal;
	}
	
	public void es(double es) {
		this.errorSignal = es;
	}
	
	public double calcOutput(double[] inputList) {
		double outputNum = 0;
		for (int i=0; i<weights.length; i+=1) {
			outputNum += weights[i] * inputList[i];

		}
		outputNum += bias;
		output = 1/(1+Math.exp(outputNum*-1));
		return output;
		
	}
	

	
}
