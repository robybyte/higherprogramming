/**
 * 
 */
package hp.simulation.transport;

import hp.simulation.Order;
import hp.simulation.Resource;
import hp.simulation.SchedulingStrategy;
import hp.simulation.WaitingArea;
import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByList;

/**
 * @author rgn
 *
 */
public class ParcelStation implements SchedulingStrategy, WaitingArea {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rgn.unibw.hp.interfaces.WaitingArea#enterWaitingArea(de.rgn.unibw.hp.
	 * interfaces.Order)
	 */
	private QueueSet queue = null;

	public ParcelStation() {
		queue = new QueueSetByList<Parcel>();
	}

	public ParcelStation(QueueSet queue ){
		this.queue = queue;
	}

	@Override
	public void enterWaitingArea(Order order) {
		// TODO Auto-generated method stub
		queue.enqueue((Parcel) order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rgn.unibw.hp.interfaces.SchedulingStrategy#scheduleNextOrders(de.rgn.
	 * unibw.hp.interfaces.WaitingArea, de.rgn.unibw.hp.interfaces.Resource)
	 */
	@Override
	public void scheduleNextOrders(WaitingArea waitingArea, Resource resource) {
		// TODO Auto-generated method stub
		while (!queue.isEmpty() && ((Transporter) resource).addParcel((Parcel) queue.first())) {
			queue.dequeue();
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

}
