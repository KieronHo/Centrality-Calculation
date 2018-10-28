import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Contains the method to calculate degree centrality
 * 
 * @author Kieron Ho 20500057
 *
 */
public class DegreeCentrality {

	/**
	 * Calculates the degree centrality of a given edgematrix
	 * 
	 * @param edgeMatrix
	 * @param vertexReferences
	 * @return the vertices with the top five degree centralities
	 */
public static int[] calculateDegreeCentrality(int[][] edgeMatrix, ArrayList<Integer> vertexReferences){
	int[] topFive = new int[5];
	PriorityQueue<QueueEntry> degreeResults = new PriorityQueue<QueueEntry>(1, Collections.reverseOrder());
	for(int i = 0 ; i < edgeMatrix[0].length ; i++){
		int edgeCount = 0;
		for(int j = 0 ; j < edgeMatrix[1].length ; j++){
			if(edgeMatrix[i][i] == 1){
				edgeCount++;
			}
		}
		QueueEntry vertexEdgeCount = new QueueEntry(vertexReferences.get(i), edgeCount);
		degreeResults.add(vertexEdgeCount);
	}
	
	for(int i = 0 ; i < 5 ; i++){
		topFive[i] = degreeResults.remove().getVertexReference();
	}
	return topFive;
}
}
