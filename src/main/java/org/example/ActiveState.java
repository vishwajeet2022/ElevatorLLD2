package org.example;

public class ActiveState implements ElevatorState{

    @Override
    public void handleRequest(Elevator elevator, FloorRequest floorRequest) {
        System.out.println("Lift is in active State");
        elevator.addRequest(floorRequest);

    }
}
