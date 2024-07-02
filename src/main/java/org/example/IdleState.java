package org.example;

public class IdleState implements ElevatorState {


    @Override
    public void handleRequest(Elevator elevator, FloorRequest floorRequest) {
        elevator.addRequest(floorRequest);
        elevator.setElevatorState(new ActiveState());
    }
}
