/**
 * 
 */
package hp.simulation.queueset;

import java.util.Iterator;

import hp.simulation.queueset.QueueSetByList.ListElement;

/**
 * @author Robin Grimsmann
 * @param <E>
 *
 */
public class QueueSetByArray<E> implements QueueSet<E> {
	@SuppressWarnings("unchecked")
	private E[] objs = (E[]) new Object[16];
	private int cnt = 0;
	private int maxCnt = 16;

	@Override
	public void enqueue(E element) {
		insert(element);
	}

	@Override
	public E dequeue() throws EmptyQueueSetException {
		if (isEmpty()) {
			throw new EmptyQueueSetException("Queue does not contain any elements. Call of dequeue() is illegal.");
		}
		E tmp = first();
		remove(tmp);
		return tmp;
	}

	@Override
	public E first() throws EmptyQueueSetException {
		if (isEmpty()) {
			throw new EmptyQueueSetException("Queue does not contain any elements. Call of dequeue() is illegal.");
		}
		return objs[0];
	}

	@Override
	public void insert(E element) {
		if (!contains(element)) {
			if (cnt == objs.length) {
				@SuppressWarnings("unchecked")
				E[] cpy = (E[]) new Object[maxCnt * 2];
				System.arraycopy(objs, 0, cpy, 0, objs.length);
				objs = cpy;
				maxCnt *= 2;
			}
			objs[cnt] = element;
			cnt++;
		}
	}

	@Override
	public void remove(E element) {
		int i = 0;
		boolean found = false;
		for (; i < cnt; i++) {
			if (objs[i] != null) {
				if (objs[i].equals(element)) {
					found = true;
					break;
				}
			} else if (element == null) {
				found = false;
				break;
			}
		}
		for (; i < cnt - 1; i++) {
			objs[i] = objs[i + 1];
		}
		if (found)
			cnt--;

	}

	@Override
	public boolean contains(E order) {
		for (int i = 0; i < cnt; i++) {
			if (objs[i] != null) {
				if (objs[i].equals(order))
					return true;
			} else if (order == null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int i) throws QueueSetIndexOutOfBoundsException {
		if (i >= cnt)
			throw new QueueSetIndexOutOfBoundsException(i);
		if (i <= cnt && !isEmpty()) {
			return objs[i];
		}
		return null;
	}

	@Override
	public int size() {
		return cnt;
	}

	@Override
	public boolean isEmpty() {
		return cnt == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		cnt = 0;
		objs = (E[]) new Object[16];
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < cnt; i++) {
			s += objs[i] + " ";
		}
		return s;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<E>() {
			E[] elements = objs;
			int element = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return element < elements.length;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				E tmp = elements[element];
				element++;
				return tmp;
			}

		};
	}

}