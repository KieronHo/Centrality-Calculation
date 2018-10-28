import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides methods to extract data from provided text files and puts them into the program
 * 
 * @author Kieron Ho 20500057
 *
 */
public class DataParser {

	/**
	 * Create a list of the edges created from a file
	 * @param dataFile the file to extract data from
	 * @return an ArrayList containing new edges created
	 */
	public static ArrayList<int[]> fileToNewEdgeList(String dataFile){
		String fileName = dataFile;
		String line = "";
		int nodeOne = 0;
		int nodeTwo = 0;
		ArrayList<int[]> newEdgeList = new ArrayList<int[]>();

	try{
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while((line = bufferedReader.readLine()) != null){
			nodeOne = Integer.parseInt(line.trim().split(" ")[0]);
			nodeTwo = Integer.parseInt(line.trim().split(" ")[1]);
			int[] newEdge = {nodeOne, nodeTwo};
			newEdgeList.add(newEdge);
		}
		bufferedReader.close();
	}
		catch(FileNotFoundException fnfx){
			System.out.println("Could not find file '" + fileName + "'");
			return null;
		}
		catch(IOException iox){
			System.out.println("Error reading file '" + fileName + "'");
			return null;
		}
	return newEdgeList;
	}
	
	
	/**
	 * Makes a matrix from a provided ArrayList containing the graph edges
	 * @param edgeList
	 * @param vertexReferences is the relationship between the vertex number and their "int names"
	 * @param numberOfVertices
	 * @return a matrix based on the supplied edges, with self-edges = 0 and existing edges = 1
	 */
	public static int[][] matrixMaker(ArrayList<int[]> edgeList, ArrayList<Integer> vertexReferences, int numberOfVertices){
		int[][] newEdgeMatrix = new int[numberOfVertices][numberOfVertices];
		int[] edgeBuffer = new int[2];
		for(int i = 0 ; i < edgeList.size() ; i++){
			edgeBuffer = edgeList.get(i);
			int index1 = vertexReferences.indexOf(edgeBuffer[0]);
			int index2 = vertexReferences.indexOf(edgeBuffer[1]);
			//both should be identical, but just in case
			if((newEdgeMatrix[index1][index2] == 0 )||(newEdgeMatrix[index2][index1] == 0)){
				newEdgeMatrix[index1][index2] = 1;
				newEdgeMatrix[index2][index1] = 1;
			}
		}
		
		return newEdgeMatrix;
	}
	

}
