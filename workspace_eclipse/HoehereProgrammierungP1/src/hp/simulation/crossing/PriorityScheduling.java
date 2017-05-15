/**
 * 
 */
package hp.simulation.crossing;

import hp.simulation.Resource;
import hp.simulation.SchedulingStrategy;
import hp.simulation.WaitingArea;

/**
 * @author rgn
 *
 */
public class PriorityScheduling implements SchedulingStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.SchedulingStrategy#scheduleNextOrders(hp.simulation.
	 * WaitingArea, hp.simulation.Resource)
	 */
	@Override
	public void scheduleNextOrders(WaitingArea waitingArea, Resource resource) {
		if(waitingArea != null && resource != null) {
			QueuesForCrossing qfc = (QueuesForCrossing)waitingArea;
			Crossing cross	= (Crossing)resource;
			if(!qfc.isEmpty()) {
				if(!Direction.NORTH.isEmpty() || !Direction.SOUTH.isEmpty()) {
					if(!Direction.NORTH.isEmpty()) {
						cross.enterCrossing(Direction.NORTH.dequeue());
					}
					if(!Direction.SOUTH.isEmpty()) {
						cross.enterCrossing(Direction.SOUTH.dequeue());
					}
				} else {
					// Ost West
					if(!Direction.EAST.isEmpty()) {
						cross.enterCrossing(Direction.EAST.dequeue());
					}
					if(!Direction.WEST.isEmpty()) {
						cross.enterCrossing(Direction.WEST.dequeue());
					}
				}
			}
		} 
	}

}
