package hp.simulation.transport;

import java.util.Random;

import hp.simulation.Order;
import hp.simulation.Resource;
import hp.simulation.Scenario;
import hp.simulation.SchedulingStrategy;
import hp.simulation.WaitingArea;

public class TransportScenario implements Scenario {
	private ParcelStation parcelStation;
	private Transporter transporter;
	protected int minWeight, maxWeight, minDistance, maxDistance;

	public TransportScenario(double maxLoad, int speed, int minWeight, int maxWeight, int minDistance,
			int maxDistance) {
		transporter = new Transporter(maxLoad, speed);
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
		parcelStation = new ParcelStation();
	}

	@Override
	public Order createNewOrder() {
		// TODO Auto-generated method stub
		Random r = new Random();
		return new Parcel(this.minWeight + (int)(Math.random() * (this.maxWeight - this.minWeight)),
				minDistance + (int)(Math.random() * (maxDistance - minDistance)));
	}

	@Override
	public WaitingArea getWaitingArea() {
		// TODO Auto-generated method stub
		return parcelStation;
	}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return transporter;
	}

	@Override
	public SchedulingStrategy getSchedulingStrategy() {
		// TODO Auto-generated method stub
		return parcelStation;
	}

}
