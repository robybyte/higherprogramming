/**
 * 
 */
package hp.simulation;

/**
 * @author rgn
 *
 */
public class BadConfigurationException extends Exception {
	public BadConfigurationException() {
		super();
	}

	public BadConfigurationException(String message) {
		super(message);
	}

	public BadConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadConfigurationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStrackTrace) {
		super(message, cause, enableSuppression, writableStrackTrace);
	}

	public BadConfigurationException(Throwable cause) {
		super(cause);
	}
}
