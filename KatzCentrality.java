import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Provides the method for Katz centrality
 * 
 * @author Kieron
 *
 */
public class KatzCentrality {

	/**
	 * Calculates the Katz centrality of a given matrix
	 * 
	 * @param edgeMatrix
	 * @param vertexReferences links the vertex reference to the vertex name
	 * @param alphaKatz the alpha value to use in the Katz calculation
	 * @return vertices with the top five Katz centrality values
	 */
	public static int[] calculateKatzCentrality(int[][] edgeMatrix, ArrayList<Integer> vertexReferences, float alphaKatz){
		int[] topFive = new int[5];
		float[] katzClosenessArray = new float[vertexReferences.size()];
		
		//make an array with all the edge counts of each vertex
		int[] edgeCounts = new int[vertexReferences.size()];
		for(int i = 0 ; i < vertexReferences.size(); i++){
			for(int j = 0 ; j < vertexReferences.size(); j++){
				edgeCounts[i] = edgeCounts[i] + edgeMatrix[i][j];
			}
		}
		
		
		//Dijkstra's algorithm to find shortest paths
		int lowestPriority = Integer.MAX_VALUE;// set the lowest priority to the highest possible integer
		PriorityQueue<QueueEntry> queue = new PriorityQueue<QueueEntry>();// Queue to hold the vertex values

		// make the key array and fill with lowest priority
		int[] key = new int[vertexReferences.size()];
		for(int i = 0 ; i < vertexReferences.size() ; i++){
			key[i] = lowestPriority;
		}

		// make the queue with each vertex priority set to the lowest priority
		for (int i = 0; i < vertexReferences.size(); i++) {
			QueueEntry initialVertex = new QueueEntry(i, lowestPriority);
			queue.add(initialVertex);
		}

		for(int vertexRoot = 0 ; vertexRoot < vertexReferences.size(); vertexRoot++){
		PQMethods.changePriority(queue, vertexRoot, 0);//set the priority of vertex vertexRoot to highest priority
		key[vertexRoot] = 0;
		
		while (!queue.isEmpty()) {
			int u = queue.remove().getVertexReference();//remove the highest priority item in queue and set it to u
			for (int v = 0; v < vertexReferences.size(); v++) {
				if (edgeMatrix[v][u] == 1 && v != u) {//check each neighbour of u
						if (1 + key[u] < key[v] && PQMethods.contains(queue,  v)) {//test if key and pseudoQueue need updating
								PQMethods.changePriority(queue, v, 1 + key[u]);
							key[v] = 1 + key[u];//set the new weight/priority/relax the edge
						}
				}
			}
		}
		for(int i = 0 ; i < key.length ; i++) {
			if(key[i] == lowestPriority) {
				key[i] = 0;
			}
		}
		
		//calculate the root vertex Katz centrality and add to katzClosenessArray with key[] X edgeCounts[]
		katzClosenessArray[vertexRoot] = alphaKatz * edgeCounts[vertexRoot];
		for(int i = 0 ; i < vertexReferences.size(); i++){
		katzClosenessArray[vertexRoot] = (float) (katzClosenessArray[vertexRoot] + edgeCounts[i]*Math.pow(alphaKatz, key[i] + 1));
		}
		}
		
		PriorityQueue<QueueEntryFloat> katzQueue = new PriorityQueue<QueueEntryFloat>();
		for(int i = 0 ; i < katzClosenessArray.length ; i ++){
			katzQueue.add(new QueueEntryFloat(vertexReferences.get(i), katzClosenessArray[i]));
		}
		
		for(int i = 0 ; i < topFive.length ; i++){
			topFive[i] = katzQueue.remove().getVertexReference();
		}
		
		
		return topFive;
	}

}
