/**
 * 
 */
package hp.simulation.transport;

import hp.simulation.Resource;
import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByArray;

/**
 * @author rgn
 *
 */
public class Transporter implements Resource {
	int speed = 0;
	double maxLoad = 0.0d; // read Only? ^
	double currentLoad = 0.0d;
	int currentDistance = 0;

	private QueueSet currentParcels = new QueueSetByArray();

	Transporter(double maxLoad, int speed) {
		this.speed = speed; 
		this.maxLoad = maxLoad;
	}

	boolean addParcel(Parcel parcel) {
		if (parcel != null && maxLoad >= currentLoad + parcel.weight && !currentParcels.contains(parcel)) {
			currentLoad += parcel.weight;
			currentDistance += parcel.distance;
			currentParcels.enqueue(parcel);
			return true;
			// Paket in die Queue legen - fehlende Zeilen würde das erklären.
		}
		return false;
	}

	@Override
	public void freeResource() {
		// TODO Auto-generated method stub
		currentLoad = 0.0d; // 1 zeile fehlt
		currentDistance = 0;
		currentParcels.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.Resource#getCurrentOrder()
	 */
	@Override
	public QueueSet getCurrentOrders() {
		// TODO Auto-generated method stub
		return currentParcels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.Resource#getTimeResourceIsOccupied()
	 */
	@Override
	public long getTimeResourceIsOccupied() {
		// TODO Auto-generated method stub
		return currentDistance / speed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rgn.unibw.hp.interfaces.Resource#isFree()
	 */
	@Override
	public boolean isFree() {
		// TODO Auto-generated method stub
		return (currentParcels.isEmpty());
	}

	public String toString() {
		String s = "";
		s += "Transporter[Speed = " + speed + ", maxLoad = " + maxLoad + ", currentLoad = " + currentLoad
				+ ", currentDistance = " + currentDistance + ", currentParcels: " + currentParcels.toString();
		return s;
	}
}
