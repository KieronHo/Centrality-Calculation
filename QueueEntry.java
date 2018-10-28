
/**
 * This Object holds the vertex Reference integer and an integer comparator key to sort with
 * in a PriorityQueue
 * @author Kieron Ho 20500057
 *
 */
public class QueueEntry implements Comparable<QueueEntry>{
		private int vertexReference;
		private int key;
		
		public QueueEntry() {
			vertexReference = 0;
			key = 0;
		}
		
		public QueueEntry(int vertexReference, int key){
			this.vertexReference = vertexReference;
			this.key = key;
		}
		
		public int getVertexReference(){
			return vertexReference;
		}
		
		public Integer getKey(){
			return key;
		}
		
		public void updateKey(int keyChange){
			key = keyChange;
		}
		
		@Override
		public int compareTo(QueueEntry other){
			return this.getKey().compareTo(other.getKey());
		}
	}