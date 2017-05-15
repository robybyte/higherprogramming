/**
 * 
 */
package hp.simulation.crossing;

import hp.simulation.Order;

/**
 * @author rgn
 *
 */
public class Car extends Order {
	public int speed;
	public Direction direction;
	/* (non-Javadoc)
	 * @see hp.simulation.Order#setArrivalTime(long)
	 */
	
	Car(int speed, Direction direction) {
		this.direction = direction;
		this.speed = speed;
	}
	
	
	public String toString(){
		return "Car[direction = " + direction.toString() + ", speed = " + speed
				+ ", arrivalTime = " + arrivalTime 
				+ ", enterTransportTime = " + enterResourceTime 
				+ ", leaveTransportTime = " + leaveResourceTime
				+ "]";
	}

}
