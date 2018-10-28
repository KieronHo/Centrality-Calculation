
/**
 * This Object holds the vertex Reference integer and a float comparator key to sort with
 * in a PriorityQueue
 * @author Kieron Ho 20500057
 *
 */
public class QueueEntryFloat implements Comparable<QueueEntryFloat>{
		private int vertexReference;
		private float key;
		
		public QueueEntryFloat() {
			vertexReference = 0;
			key = 0;
		}
		
		public QueueEntryFloat(int vertexReference, float key){
			this.vertexReference = vertexReference;
			this.key = key;
		}
		
		public int getVertexReference(){
			return vertexReference;
		}
		
		public Float getKey(){
			return key;
		}
		
		public void updateKey(float keyChange){
			key = keyChange;
		}
		
		@Override
		public int compareTo(QueueEntryFloat other){
			return this.getKey().compareTo(other.getKey());
		}
	}