/**
 * 
 */
package hp.simulation.crossing;

import java.util.Random;

import hp.simulation.BadConfigurationException;
import hp.simulation.Order;
import hp.simulation.Resource;
import hp.simulation.Scenario;
import hp.simulation.SchedulingStrategy;
import hp.simulation.WaitingArea;

/**
 * @author rgn
 *
 */
public class CrossingScenario implements Scenario {

	protected int minSpeed;
	protected int maxSpeed;
	private PriorityScheduling schedulingStrategy;
	private Crossing crossing;
	private QueuesForCrossing waitingArea;

	public static class CrossingScenarioBuilder {
		private int minSpeed;
		private int maxSpeed;
		private int size;

		public CrossingScenarioBuilder minSpeed(int minSpeed) throws IllegalArgumentException {
			if (minSpeed <= 0)
				throw new IllegalArgumentException("Illegal Parameter. int minSpeed has to be greater than 0!");
			this.minSpeed = minSpeed;
			return this;
		}

		public CrossingScenarioBuilder maxSpeed(int maxSpeed) throws IllegalArgumentException {
			if (maxSpeed <= 0)
				throw new IllegalArgumentException("Illegal Parameter. int maxSpeed has to be greater than 0!");
			this.maxSpeed = maxSpeed;
			return this;
		}

		public CrossingScenarioBuilder size(int size) throws IllegalArgumentException {
			if (size <= 0)
				throw new IllegalArgumentException("Illegal Parameter. int size has to be greater than 0!");
			this.size = size;
			return this;
		}

		public CrossingScenario build() throws BadConfigurationException {
			if (this.minSpeed > this.maxSpeed)
				throw new BadConfigurationException("maxSpeed must be greater then minSpeed!");
			return new CrossingScenario(this);
		}
	}

	public CrossingScenario(CrossingScenarioBuilder builder) {
		this.maxSpeed = builder.maxSpeed;
		this.minSpeed = builder.minSpeed;
		this.crossing = new Crossing(builder.size);
		this.schedulingStrategy = new PriorityScheduling();
		this.waitingArea = new QueuesForCrossing();
	}

	public CrossingScenario(int size, int minSpeed, int maxSpeed) {
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.crossing = new Crossing(size);
		this.schedulingStrategy = new PriorityScheduling();
		this.waitingArea = new QueuesForCrossing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Scenario#createNewOrder()
	 */
	@Override
	public Order createNewOrder() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int speed = rand.nextInt(Math.abs(this.maxSpeed - this.minSpeed)) + this.minSpeed;
		int decision = rand.nextInt(9);
		Direction direction;
		switch (decision) {
		case 0:
			direction = Direction.EAST;
			break;
		case 1:
			direction = Direction.WEST;
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			direction = Direction.SOUTH;
			break;
		default:
			direction = Direction.NORTH;
		}
		return new Car(speed, direction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Scenario#getWaitingArea()
	 */
	@Override
	public WaitingArea getWaitingArea() {
		// TODO Auto-generated method stub
		return waitingArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Scenario#getResource()
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return crossing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hp.simulation.Scenario#getSchedulingStrategy()
	 */
	@Override
	public SchedulingStrategy getSchedulingStrategy() {
		// TODO Auto-generated method stub
		return schedulingStrategy;
	}

}
