import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Provides  additional methods for use with the PriorityQueue<QueueEntry>
 * 
 * @author Kieron Ho 20500057
 *
 */
	public class PQMethods{

		/**
		 * Checks if a given PriorityQueue<QueueEntry> has a QueueEntry with a particular value
		 * 
		 * @param queue
		 * @param itemValue is the value to search through the PriorityQueue for
		 * @return true if the queue contains that value, otherwise false
		 */
	public static boolean contains(PriorityQueue<QueueEntry> queue, int itemValue) {
		Iterator<QueueEntry> queueIterator = queue.iterator();
		while(queueIterator.hasNext()) {
			QueueEntry checkingItem = queueIterator.next();
			if(checkingItem.getVertexReference() == itemValue) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Changes the priority of a QueueEntry in a given PriorityQueue<QueueEntry>
	 * It achieves this by finding a matching QueueEntry using an iterator, removing it, then
	 * re-entering the value with a new priority
	 * 
	 * @param queue
	 * @param itemValue
	 * @param newPriority
	 */
	public static void changePriority(PriorityQueue<QueueEntry> queue, int itemValue, int newPriority){
		Iterator<QueueEntry> queueIterator = queue.iterator();
				if(contains(queue, itemValue)) {
		QueueEntry soughtAfter = new QueueEntry();
		while(queueIterator.hasNext()) {
			soughtAfter = queueIterator.next();
			if(soughtAfter.getVertexReference() == itemValue){
				queue.remove(soughtAfter);
				queue.add(new QueueEntry(itemValue, newPriority));
				break;
			}
		}
		}
	}
	}
