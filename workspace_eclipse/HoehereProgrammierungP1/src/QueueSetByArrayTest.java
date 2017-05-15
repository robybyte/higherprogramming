import static org.junit.Assert.*;

import org.junit.Test;

import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByArray;

/**
 * 
 */

/**
 * @author rgn
 *
 */
public class QueueSetByArrayTest extends QueueSetTest {

	public QueueSetByArrayTest() {

	}

	@Override
	public QueueSet getQueueSet() {
		// TODO Auto-generated method stub
		return new QueueSetByArray();
	}

}
