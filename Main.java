import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains the main method. Uses Java Runtime Environment 1.8.0_161
 * 
 * @author Kieron Ho 20500057
 *
 */
public class Main {

	public static void main(String[] args) {
		userInterface();
		System.out.println("Goodbye");
	}

	
	/**
	 * Provides the interactive interface between the data calculations
	 */
private static void userInterface(){
	CentralityData dataPack = new CentralityData();
System.out.println("Welcome to the Centrality Calculator");
	int centralityState = 1;
	Scanner console = new Scanner(System.in);
	String command = "";
	
	while(centralityState == 1){
if(dataPack.isEmpty()){
	System.out.println("\nPlease add data sets to view results");
	System.out.println("Capital words indicate valid options\nOptions: ADD data, CLOSE program");
	command = console.nextLine().toLowerCase();
	switch(command){
	case "add" : 
		System.out.println("Please enter name of .txt file: ");
		String fileName = console.nextLine();
		if(!fileName.endsWith(".txt")){// make text file accept both name and name + extension
		fileName = fileName + ".txt";
		}
		int beforeCount = dataPack.getNumberOfVertices();
		dataPack.updateAllData(fileName);
		if(beforeCount == dataPack.getNumberOfVertices()){
			System.out.println("Duplicate or non-compatible data");
		}
		break;
	case "close" : 
		centralityState = 0;
		break;
	default : 
		System.out.println("Invalid option entered. Please check capital words for valid options");
		break;
	}
}
else{
	System.out.println("\nCurrent data contains " + dataPack.getNumberOfVertices() + " vertices");
	System.out.println("Degree Centrality Vertices = " + Arrays.toString(dataPack.getTopFiveDegree()));
	System.out.println("Closeness Centrality Vertices = " + Arrays.toString(dataPack.getTopFiveCloseness()));
	System.out.println("Betweenness Centrality Vertices = " + Arrays.toString(dataPack.getTopFiveBetweenness()));
	System.out.println("Katz Centrality Vertices = " + Arrays.toString(dataPack.getTopFiveKatz()));
	System.out.println("Capital words indicate valid options\nOption: ADD data, CHANGE Katz alpha, CLEAR existing data, CLOSE program");
	command = console.nextLine().toLowerCase();
	switch(command){
	case "add" : 
		System.out.println("Please enter name of adjacent data file: ");
		String fileName = console.nextLine();
		if(!fileName.endsWith(".txt")){// make text file accept both name and name + extension
		fileName = fileName + ".txt";
		}
		int beforeCount = dataPack.getNumberOfVertices();
		dataPack.updateAllData(fileName);
		if(beforeCount == dataPack.getNumberOfVertices()){
			System.out.println("Duplicate or non-compatible data");
		}
		break;
	case "change" :
		System.out.println("Please enter new alpha value: ");
		float newAlphaKatz = Float.parseFloat(console.nextLine());
		if(newAlphaKatz < 1.0 && newAlphaKatz > 0){
		dataPack.alphaKatzChangeUpdate(newAlphaKatz);
		}
		else{
			System.out.println("Alpha value must be 0 < alpha < 1");
		}
		break;
	case "close" :
			centralityState = 0;
		break;
	case "clear" :
		if(dataPack.clearData()){
			System.out.println("Data successfully cleared");
		}
		break;
		default:
			System.out.println("Invalid option entered. Please check capital words for valid options");	
			break;
	
	}

}
	}
	console.close();
}
}
