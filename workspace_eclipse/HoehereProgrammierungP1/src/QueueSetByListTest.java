import static org.junit.Assert.*;

import org.junit.Test;

import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByList;

public class QueueSetByListTest extends QueueSetTest {

	public QueueSetByListTest() {

	}

	@Override
	public QueueSet getQueueSet() {
		// TODO Auto-generated method stub
		return new QueueSetByList();
	}

}
