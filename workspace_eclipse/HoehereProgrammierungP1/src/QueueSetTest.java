import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hp.simulation.queueset.EmptyQueueSetException;
import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetIndexOutOfBoundsException;

/**
 * 
 */

/**
 * @author rgn
 *
 */

public abstract class QueueSetTest {

	private QueueSet q = null;

	public QueueSetTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		q = getQueueSet();
	}

	abstract public QueueSet getQueueSet();

	@After
	public void tearDown() {
	}

	@Test
	public void CheckEmptyQueue() {
		assertTrue(q.isEmpty() && q.size() == 0);
	}

	@Test(expected = EmptyQueueSetException.class)
	public void checkDequeueEmptyQueue() {
		q.dequeue();
	}

	@Test(expected = EmptyQueueSetException.class)
	public void checkFirstEmptyQueue() {
		q.first();
	}

	@Test(expected = QueueSetIndexOutOfBoundsException.class)
	public void getIndexEmptyQueue() {
		q.get(0);
	}

	@Test
	public void insert144InEmptyQueue() {
		q.insert(144);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(144) && q.size() == 1 && q.first().equals(144) && q.get(0).equals(144));
	}

	@Test
	public void insertNullInEmptyQueue() {
		q.insert(null);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(null) && q.size() == 1 && q.first() == null && q.get(0) == null);
	}

	@Test
	public void insertMultipleDataOnceInEmptyQueue() {
		q.insert(144);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(144));
		assertTrue(q.size() == 1); 
		assertTrue(q.first().equals(144));
		assertTrue(q.get(0).equals(144));

		q.insert(null);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(null));
		assertTrue(q.size() == 2);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(1) == (null));

		q.insert("HP");
		assertFalse(q.isEmpty());
		assertTrue(q.contains("HP"));
		assertTrue(q.size() == 3);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(2).equals("HP"));

		q.insert(-145);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(-145));
		assertTrue(q.size() == 4);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(3).equals(-145));

		q.insert(4.5);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(4.5));
		assertTrue(q.size() == 5);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(4).equals(4.5));
	}

	@Test
	public void checkInsertMultipleDataMultipleTimes() {
		q.insert(144);
		q.insert(144);
		q.enqueue(144);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(144));
		assertTrue(q.size() == 1);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(0).equals(144));

		q.insert(null);
		q.insert(null);
		q.enqueue(null);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(null));
		assertTrue(q.size() == 2);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(1) == null);

		q.insert("HP");
		q.insert("HP");
		q.enqueue("HP");
		assertFalse(q.isEmpty());
		assertTrue(q.contains("HP"));
		assertTrue(q.size() == 3);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(2).equals("HP"));

		q.insert(-145);
		q.insert(-145);
		q.enqueue(-145);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(-145));
		assertTrue(q.size() == 4);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(3).equals(-145));

		q.insert(4.5);
		q.insert(4.5);
		q.enqueue(4.5);
		assertFalse(q.isEmpty());
		assertTrue(q.contains(4.5));
		assertTrue(q.size() == 5);
		assertTrue(q.first().equals(144));
		assertTrue(q.get(4).equals(4.5));
	}
	
	@Test
	public void checkClearAnFilledQueue() {
		q.insert(144);
		q.enqueue(null);
		q.enqueue("HP");
		q.insert(-145);
		q.enqueue(4.5);
		
		q.clear();
		assertTrue(q.isEmpty());
		assertTrue(q.size() == 0);
	}
	
	@Test
	public void checkAddingAndDequeueing() {
		for(int i = 0; i < 100; i++) {
			q.insert(i);
		}
		assertTrue(q.size() == 100); // oder 99?
		for(int i = 0; i < 100; i++) {
			assertTrue(q.dequeue().equals(i));
			assertTrue(q.size() == 99 - i); // kann falsch sein
		}
	}
	
	@Test
	public void testRemove() {
		q.insert(144);
		q.enqueue(null);
		q.enqueue("HP");
		q.insert(-145);
		q.enqueue(4.5);
		
		q.remove("HP");
		assertFalse(q.contains("HP"));
		assertTrue(q.size() == 4);
	}

}
