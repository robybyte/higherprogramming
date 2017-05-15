/** 
 * 
*/
package hp.simulation.queueset;

import java.util.Iterator;

/**
 * @author rgn
 *
 */
public class QueueSetByList<E> implements QueueSet<E>{

	private int size = 0;
	private ListElement<E> head, tail;

	static class ListElement<E> {
		private E element = null;
		private ListElement<E> next = null;

		public ListElement(E element, ListElement<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E element) {
		if (!contains(element)) {
			if (size == 0) {
				head = new ListElement<E>(element, null);
				tail = head;
			} else {
				tail.next = new ListElement<E>(element, null);
				tail = tail.next;
			}
			size++;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#dequeue()
	 */
	@Override
	public E dequeue() throws EmptyQueueSetException {
		if (isEmpty()) {
			throw new EmptyQueueSetException("Queue does not contain any elements. Call of dequeue() is illegal.");
		}
		ListElement<E> tmp = head;
		head = head.next;
		size--;
		return tmp.element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#first()
	 */
	@Override
	public E first() throws EmptyQueueSetException {
		if (isEmpty()) {
			throw new EmptyQueueSetException("Queue does not contain any elements. Call of dequeue() is illegal.");
		}
		return head.element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#insert(java.lang.Object)
	 */
	@Override
	public void insert(E element) {
		enqueue(element);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#remove(java.lang.Object)
	 */
	@Override
	public void remove(E element) {
		if (!isEmpty()) {
			if (head.element == element || (head.element != null && head.element.equals(element))) {
				head = head.next;
				size--;
				if (isEmpty()) {
					tail = null;
				}
			} else {
				ListElement<E> tmp = head;
				do {
					if (tmp.next.element == element || (tmp.next.element != null && tmp.next.element.equals(element))) {

						if (tmp.next == tail) {
							tmp.next = null;
							tail = tmp;
						} else {
							tmp.next = tmp.next.next;
						}
						size--;
						break;
					}
					tmp = tmp.next;
				} while (tmp != null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rgn.unibw.hp.interfaces.QueueSet#contains(de.rgn.unibw.hp.interfaces.
	 * Order)
	 */
	@Override
	public boolean contains(E order) {
		// TODO Auto-generated method stub
		ListElement<E> tmp = head;
		for (int i = 0; i < size; i++) {
			if (tmp.element != null) {
				if (tmp.element.equals(order))
					return true;
			} else if (order == null) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#get(int)
	 */
	@Override
	public E get(int i) throws QueueSetIndexOutOfBoundsException {
		if (i >= size)
			throw new QueueSetIndexOutOfBoundsException(i);
		ListElement<E> tmp = head;
		for (int a = 0; a < i; a++) {
			tmp = tmp.next;
		}
		return tmp.element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.QueueSet#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public String toString() {
		String s = "";
		ListElement<E> tmp = head;
		while (tmp != null) {
			s += tmp.element + " ";
			tmp = tmp.next;
		}
		return s;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<E>() {
			ListElement<E> element = head;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return element.next != null;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return element.element;
			}
			
		};
	}
}
