/**
 * 
 */
package de.rgn.unibw.hp.test;

import hp.simulation.*;
import hp.simulation.crossing.CrossingScenario;
import hp.simulation.crossing.CrossingScenario.CrossingScenarioBuilder;
import hp.simulation.queueset.QueueSet;
import hp.simulation.queueset.QueueSetByList;
import hp.simulation.transport.PriorityTransportScenario;
import hp.simulation.transport.TransportScenario;
/**
 * @author rgn
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*QueueSet<String> sQ = new QueueSetByList<>();
		
		CrossingScenarioBuilder b = new CrossingScenarioBuilder();
		CrossingScenario crossing = null;
		try {
			b.maxSpeed(15);
			b.size(100);
			b.minSpeed(5);
			crossing = b.build();
		} catch (Exception e) {

		}

        System.out.println(Simulation.runSimulation(crossing, 10000, 5));
        
        TransportScenario transport = new TransportScenario(100, 100, 15, 30, 100, 600);
        System.out.println(Simulation.runSimulation(transport, 100, 5));
		QueueSet byArray = new QueueSetByArray();
		byArray.enqueue("Hallo");
		byArray.enqueue("HP");
		byArray.enqueue("Welt");
		System.out.println(byArray.contains("HP"));
		System.out.println(byArray.contains("asdda"));
		System.out.println(byArray.first().toString());
		System.out.println(byArray.get(2).toString());
		byArray.insert("WEEEEEELELLLLTTTTTTTTTTT");
		System.out.println(byArray.toString());
		byArray.remove("Welt");
		System.out.println(byArray.toString());
		System.out.println(byArray.dequeue());
		System.out.println(byArray.toString());
		System.out.println("__________________________________");
		QueueSet byList = new QueueSetByList();
		byList.enqueue("Hallo");
		byList.enqueue("HP");
		byList.enqueue("Welt");
		System.out.println(byList.contains("HP"));
		System.out.println(byList.contains("asdda"));
		System.out.println("First " + byList.first().toString());
		System.out.println("get " + byList.get(2).toString());
		byArray.insert("WEEEEEELELLLLTTTTTTTTTTT");
		System.out.println(byList.toString());
		byArray.remove("Welt");
		System.out.println(byList.dequeue());
		System.out.println(byList.toString());*/
		double maxLoad = 500.0;
		int speed = 100;
		int minWeight = 15;
		int maxWeight = 100;
		int minDistance = 10;
		int maxDistance = 1000;
		System.out.println("--- TransportScenario ---");
		TransportScenario ts = new TransportScenario(
		maxLoad, speed, minWeight, maxWeight, minDistance, maxDistance);
		System.out.println(Simulation.runSimulation(ts, 10000, 6));
		System.out.println("--- PriorityTransportScenario ---");
		PriorityTransportScenario pts = new PriorityTransportScenario(
		maxLoad, speed, minWeight, maxWeight, minDistance, maxDistance);
		System.out.println(Simulation.runSimulation(pts, 10000, 6));
	}

}
