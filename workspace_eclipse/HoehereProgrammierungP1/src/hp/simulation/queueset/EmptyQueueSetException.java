/**
 * 
 */
package hp.simulation.queueset;

/**
 * @author rgn
 *
 */
public class EmptyQueueSetException extends RuntimeException {

	public EmptyQueueSetException() {
		super();
	}

	public EmptyQueueSetException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyQueueSetException(String message) {
		super(message);
	}

	public EmptyQueueSetException(Throwable cause) {
		super(cause);
	}
}
