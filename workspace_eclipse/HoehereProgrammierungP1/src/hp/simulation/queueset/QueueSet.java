/**
 * 
 */
package hp.simulation.queueset;

/**
 * @author rgn
 *
 */
public interface QueueSet<E> extends Iterable<E>{
	public void enqueue(E element);

	public E dequeue();

	public E first();

	public void insert(E element);

	public void remove(E element);

	public boolean contains(E order);

	public E get(int i);

	public int size();

	public boolean isEmpty();

	public void clear();

	public String toString();
}
