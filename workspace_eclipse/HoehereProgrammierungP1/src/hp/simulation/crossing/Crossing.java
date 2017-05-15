/**
 * 
 */
package hp.simulation.crossing;

import hp.simulation.Resource;
import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByList;

/**
 * @author rgn
 *
 */
public class Crossing implements Resource {
	int size ;
	int slowestSpeed = Integer.MAX_VALUE;
	
	private QueueSet<Resource> inCrossing = new QueueSetByList<>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Resource#freeResource()
	 */
	Crossing(int size) {
		this.size = size;
	}
	
	void enterCrossing(Car car) {
		if(car != null && !inCrossing.contains((Resource)car)) {
			if(car.speed < slowestSpeed) 
				slowestSpeed = car.speed;
			inCrossing.enqueue((Resource)car);
		}
	}
	
	@Override
	public void freeResource() {
		// TODO Auto-generated method stub
		inCrossing.clear();
		slowestSpeed = Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Resource#getCurrentOrder()
	 */
	@Override
	public QueueSet<Resource> getCurrentOrders() {
		// TODO Auto-generated method stub
		return inCrossing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Resource#getTimeResourceIsOccupied()
	 */
	@Override
	public long getTimeResourceIsOccupied() {
		// TODO Auto-generated method stub
		return size / slowestSpeed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Resource#isFree()
	 */
	@Override
	public boolean isFree() {
		// TODO Auto-generated method stub
		return inCrossing.isEmpty();
	}

	/**
	 * TODO fill with data
	 */
	public String toString() {
		return "Crossing[size = " + size + ", slowestSpeed = " + slowestSpeed + ", Nord = " + " " + ", Süd = " + " "
				+ ", Ost = " + " " + ", West = " + " " + "]";
	}
}
