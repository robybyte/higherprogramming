/**
 * 
 */
package hp.simulation.crossing;

import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByList;

/**
 * @author rgn TODO fix it
 */
public enum Direction {
	SOUTH(new QueueSetByList<Car>()), WEST(new QueueSetByList<Car>()), NORTH(new QueueSetByList<Car>()), EAST(new QueueSetByList<Car>());
	private QueueSet<Car> queue;

	private Direction(QueueSet<Car> queue) {
		this.queue = queue;
	}
	
	boolean isEmpty() {
		return queue.isEmpty();
	}
	
	void enqueue(Car car) {
		if(car != null && !queue.contains(car) &&  car.direction == this) {
			queue.enqueue(car);
		}
	} 
	
	Car dequeue() {
		return (Car)queue.dequeue();
	}
}