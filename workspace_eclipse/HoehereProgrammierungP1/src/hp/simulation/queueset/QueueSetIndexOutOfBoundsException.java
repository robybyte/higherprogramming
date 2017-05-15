/**
 * 
 */
package hp.simulation.queueset;

/**
 * @author rgn
 *
 */
public class QueueSetIndexOutOfBoundsException extends IndexOutOfBoundsException {
	public QueueSetIndexOutOfBoundsException() {
		super();
	}
	
	public QueueSetIndexOutOfBoundsException(int index) {
		super("Illegal Index: " + index);
	}
	
	public QueueSetIndexOutOfBoundsException(String s) {
		super(s);
	} 
}
