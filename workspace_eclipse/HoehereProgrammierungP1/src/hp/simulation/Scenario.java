/**
 * 
 */
package hp.simulation;

/**
 * @author Robin Grimsmann
 *
 */
public interface Scenario {
	public Order createNewOrder();
	public WaitingArea getWaitingArea();
	public Resource getResource();
	public SchedulingStrategy getSchedulingStrategy();
}
