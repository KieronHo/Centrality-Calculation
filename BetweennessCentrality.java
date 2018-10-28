import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Uses Ulrik Brandes' algorithm for betweenness centrality for each vertex
 * ref http://www.algo.uni-konstanz.de/publications/b-fabc-01.pdf
 * 
 * @author Kieron Ho 20500057
 *
 */
public class BetweennessCentrality {

	/**
	 * 
	 * @param edgeMatrix
	 * @param vertexReferences
	 * @return
	 */
	public static int[] calculateBetweennessCentrality(int[][] edgeMatrix, ArrayList<Integer> vertexReferences) {
		int[] topFive = new int[5];
		float[] betweennessCentrality = new float[vertexReferences.size()];
		for(int i = 0 ; i < vertexReferences.size() ; i++){
			betweennessCentrality[i] = 0;
		}
				
		for(int rootVertex = 0 ; rootVertex < vertexReferences.size(); rootVertex++){//for all vertices as roots
		Queue<Integer> queue = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> predecessors = new ArrayList<Integer>();//to record predecessors
		int[] numberShortPaths = new int[vertexReferences.size()];
		int[] sourceDependency = new int[vertexReferences.size()];
		int[] distance = new int[vertexReferences.size()];
		
		for(int i = 0 ; i < numberShortPaths.length ; i++){
			numberShortPaths[i] = 0;
		}
		numberShortPaths[rootVertex] = 1;
		
		for(int i = 0 ; i < distance.length ; i++){
			distance[i] = Integer.MAX_VALUE;//MAX_VALUE acts as an infinity equivalent
		}
		distance[rootVertex] = 0;
		
		queue.add(rootVertex);
		
		//Breadth First Search
		while(!queue.isEmpty()){
			int w = queue.poll();
			stack.push(w);
			for(int x = 0 ; x < vertexReferences.size() ; x++){
				if(edgeMatrix[x][w] == 1 & x != w){// check neighbors
					if(distance[x] == Integer.MAX_VALUE){//check if vertex has been visited before
						queue.add(x);//add to processing queue
						distance[x] = distance[w] + 1;
					}
					if(distance[x] == distance[w] + 1){
						numberShortPaths[x] = numberShortPaths[x] + numberShortPaths[w];
						predecessors.add(w);
					}
				}
			}
		}
		//Find how many shortest paths require the source vertex
		for(int i = 0 ; i < vertexReferences.size(); i++){
			sourceDependency[i] = 0;
		}
		while(!stack.isEmpty()){
			int w = stack.pop();
			for(int i = 0 ; i < predecessors.size(); i++){
				int v = predecessors.get(i);
				sourceDependency[v] = sourceDependency[v] + (numberShortPaths[v]/numberShortPaths[w]) * (1 + numberShortPaths[w]);
			}
			if(w != rootVertex){
				betweennessCentrality[w] = betweennessCentrality[w] + sourceDependency[w];
			}
		}
		}
		
		PriorityQueue<QueueEntryFloat> betweennessQueue = new PriorityQueue<QueueEntryFloat>();
		for(int i = 0 ; i < betweennessCentrality.length ; i ++){
			betweennessQueue.add(new QueueEntryFloat(vertexReferences.get(i), betweennessCentrality[i]));
		}
		
		for(int i = 0 ; i < 5 ; i++){
			topFive[i] = betweennessQueue.poll().getVertexReference();
		}
		
		return topFive;
	}
	
}
