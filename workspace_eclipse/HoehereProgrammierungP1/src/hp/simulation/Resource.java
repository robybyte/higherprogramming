/**
 * 
 */
package hp.simulation;

import hp.simulation.queueset.QueueSet;

/**
 * @author rgn
 *
 */
public interface Resource {
	public void freeResource();
	public QueueSet<Resource> getCurrentOrders();
	public long getTimeResourceIsOccupied();
	public boolean isFree();
	public String toString();
}