import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Provides methods for closeness centrality
 * 
 * @author Kieron Ho 20500057
 *
 */
public class ClosenessCentrality {

	/**
	 * Calculates the Closeness Centrality for each vertex and returns the five highest vertices
	 * @param edgeMatrix the edge matrix for the graph being analysed
	 * @param vertexReferences 
	 * @return the top five vertices
	 */
public static int[] calculateClosenessCentrality(int[][] edgeMatrix, ArrayList<Integer> vertexReferences){
	int[] topFive = new int[5];
	PriorityQueue<QueueEntry> closenesses = new PriorityQueue<QueueEntry>();
	for(int i = 0 ; i < vertexReferences.size(); i++){
	closenesses.add(new QueueEntry(i, getClosenessValue(edgeMatrix, i, vertexReferences)));
	}
	for(int i = 0 ; i < topFive.length ; i++) {
		topFive[i] = vertexReferences.get(closenesses.remove().getVertexReference());
	}
	return topFive;
}

/**
 * getShortestPaths uses the priority first search method and Dijkstra's algorithm to find the shortest distance to each vertex for a single root vertex
 * @param arg0 the graph to be analysed
 * @param arg1 the vertex to use as the root for analysis
 * @return an integer array containing the shortest distances to each vertex, or -1 if the vertex is unreachable
 * 
 */
public static int getClosenessValue(int[][] edgeMatrix, int vertexRoot, ArrayList<Integer> vertexReferences) {
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
	
	int keySum = 0;
	for(int i = 0 ; i < key.length ; i++) {
		keySum = keySum + key[i];
	}
	return keySum;
}
}
