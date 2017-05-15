/**
 * 
 */
package hp.simulation.transport;

import hp.simulation.Order;

/**
 * @author rgn
 *
 */
public class Parcel extends Order implements Comparable<Parcel>{
	public double weight = 0.0d;
	public int distance = 0;
	
	Parcel(double weight, int distance) {
		this.weight = weight;
		this.distance = distance;
	}
	
	public String toString() {
		return "Parcel[weight = " + weight + ", distance = " + distance
				+ ", arrivalTime = " + arrivalTime 
				+ ", enterTransportTime = " + enterResourceTime 
				+ ", leaveTransportTime = " + leaveResourceTime
				+ "]";
	}

	@Override
	public int compareTo(Parcel arg0) {
		// TODO Auto-generated method stub
		return (int) ((this.weight + this.arrivalTime + this.distance) - (arg0.arrivalTime + arg0.weight + arg0.distance));
	}
}
