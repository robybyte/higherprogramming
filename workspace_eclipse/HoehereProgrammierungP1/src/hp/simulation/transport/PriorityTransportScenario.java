/**
 * 
 */
package hp.simulation.transport;

import hp.simulation.SchedulingStrategy;
import hp.simulation.WaitingArea;
import hp.simulation.queueset.QueueSetWithPriority;

/**
 * @author rgn
 *
 */
public class PriorityTransportScenario extends TransportScenario {
	private ParcelStation parcelStation = null;
	
	public PriorityTransportScenario(double maxLoad, int speed, int minWeight, int maxWeight, int minDistance,
			int maxDistance) {
		super(maxLoad, speed, minWeight, maxWeight, minDistance, maxDistance);
	}
	
	private ParcelStation createParcelStation() {
		if (parcelStation == null)
			parcelStation = new ParcelStation(new QueueSetWithPriority<Parcel>());
		return parcelStation;
	}

	@Override
	public WaitingArea getWaitingArea() {
		return createParcelStation();
	}

	@Override
	public SchedulingStrategy getSchedulingStrategy() {
		return createParcelStation();
	}

}
