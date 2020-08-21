import java.util.List;
import java.util.ArrayList;

//Layton Rosenfeld

public class ReadDigitFile {
	
	public static Example[] ReadDigitFile(String textFile) {
		SimpleFile file = new SimpleFile(textFile);
		List<Example> examples = new ArrayList<Example>();
		
		
		for (String line : file) {
			double[] inputs = new double[64];
			String[] inputsString = line.split(",");
			for (int i = 0; i < 64; i += 1) {
				inputs[i] = Double.parseDouble(inputsString[i]);
			}
			examples.add(new Example(Integer.parseInt(inputsString[64]), inputs));
			
			
			
				
				
		}
		
		Example[] exampleArray = examples.toArray(new Example[0]);
		
		
		return exampleArray;
		
	}
	
	

}

