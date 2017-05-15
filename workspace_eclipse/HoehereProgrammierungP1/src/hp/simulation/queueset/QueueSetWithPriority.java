/**
 * 
 */
package hp.simulation.queueset;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author rgn
 *
 */
public class QueueSetWithPriority<E> implements QueueSet<E> {
	private final PriorityQueue<E> queue = new PriorityQueue<>();

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return queue.iterator();
	}

	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		if(!queue.contains(element))
			queue.offer(element);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return queue.poll();
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return queue.peek();
	}

	@Override
	public void insert(E element) {
		// TODO Auto-generated method stub
		queue.add(element);
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		queue.remove(element);
	}

	@Override
	public boolean contains(E order) {
		// TODO Auto-generated method stub
		return queue.contains(order);
	}

	@Override
	public E get(int i) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Iterable<E> it = (Iterable<E>) queue.iterator();
		int cnt = 0;
		for (E e : it) {
			if(cnt == i) 
				return e;
			cnt++;
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
