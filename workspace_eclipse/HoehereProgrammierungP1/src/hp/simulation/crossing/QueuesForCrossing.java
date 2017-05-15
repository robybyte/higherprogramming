/**
 * 
 */
package hp.simulation.crossing;

import hp.simulation.Order;
import hp.simulation.WaitingArea;

/**
 * @author rgn
 *
 */
public class QueuesForCrossing implements WaitingArea {

	private Direction roadNord = Direction.NORTH;
	private Direction roadSouth = Direction.SOUTH;
	private Direction roadWest = Direction.WEST;
	private Direction roadEast = Direction.EAST;
	
	@Override
	public void enterWaitingArea(Order order) {
		// TODO Auto-generated method stub
		Car c = (Car)order;
		c.direction.enqueue(c);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		boolean itIs = isEmpty(roadNord);
		itIs &= isEmpty(roadSouth);
		itIs &= isEmpty(roadWest);
		itIs &= isEmpty(roadEast);
		return itIs;
	}
	
	boolean isEmpty(Direction direction) {
		return direction.isEmpty();
	}

}
