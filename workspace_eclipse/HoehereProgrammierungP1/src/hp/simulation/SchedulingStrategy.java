/**
 * 
 */
package hp.simulation;

/**
 * @author rgn
 *
 */
public interface SchedulingStrategy {
	
	public void scheduleNextOrders(WaitingArea waitingArea, Resource resource);
}
