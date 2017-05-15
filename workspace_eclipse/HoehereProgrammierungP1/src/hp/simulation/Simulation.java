package hp.simulation;

import hp.simulation.queueset.QueueSet;
import java.text.DecimalFormat;

public class Simulation {

    public static String runSimulation(Scenario scenario, int numberOfOrdersToCreate, long meanEnterInterval) {

        long timeNextOrderArrives = -(long) (Math.log(Math.random()) * meanEnterInterval);
        long timeNextOrdersLeaveResource = Long.MAX_VALUE;

        double totalDurationInScenario = 0;
        double totalDurationInResource = 0;
        int countCreatedOrders = 0;
        int countFinishedOrders = 0;

        WaitingArea waitingArea = scenario.getWaitingArea();
        Resource resource = scenario.getResource();
        SchedulingStrategy strategy = scenario.getSchedulingStrategy();

        while (countCreatedOrders < numberOfOrdersToCreate || !resource.isFree() || !waitingArea.isEmpty()) {
            
            long currentTime = (timeNextOrderArrives <= timeNextOrdersLeaveResource)
                    ? timeNextOrderArrives
                    : timeNextOrdersLeaveResource;

            if (currentTime == timeNextOrderArrives) {
                Order car = scenario.createNewOrder();
                car.setArrivalTime(currentTime);
                waitingArea.enterWaitingArea(car);
                countCreatedOrders++;

                timeNextOrderArrives = (countCreatedOrders == numberOfOrdersToCreate)
                        ? Long.MAX_VALUE
                        : currentTime - (long)(Math.log(Math.random()) * meanEnterInterval);
            } 
            
            if (currentTime == timeNextOrdersLeaveResource) {
                QueueSet orders = resource.getCurrentOrders();
                for (int i = 0; i < orders.size(); i++) {
                    Order order = (Order)orders.get(i);
                    order.setLeaveResourceTime(currentTime);
                    countFinishedOrders++;
                    totalDurationInScenario += order.getDurationInScenario();
                    totalDurationInResource += order.getDurationInResource();
                }
                resource.freeResource();
                timeNextOrdersLeaveResource = Long.MAX_VALUE;
            } 
            
            if (resource.isFree() && !waitingArea.isEmpty()) {
                strategy.scheduleNextOrders(waitingArea, resource);
                if (!resource.isFree()) {
                    QueueSet orders = resource.getCurrentOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        Order order = (Order)orders.get(i);
                        order.setEnterResourceTime(currentTime);
                    }
                    timeNextOrdersLeaveResource = currentTime + resource.getTimeResourceIsOccupied();
                }
            }
        }

        DecimalFormat myFormatter = new DecimalFormat("00000.00");
        return "Verweildauer: "
                + myFormatter.format(totalDurationInScenario / countFinishedOrders) + " - Wartedauer: "
                + myFormatter.format((totalDurationInScenario - totalDurationInResource) / countFinishedOrders) + " - Bearbeitungdauer: "
                + myFormatter.format(totalDurationInResource / countFinishedOrders);

    }    
}
