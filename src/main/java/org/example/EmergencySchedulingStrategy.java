package org.example;

public class EmergencySchedulingStrategy implements SchedulingStrategy{

    @Override
    public void schedule(Elevator elevator, FloorRequest floorRequest) {
        elevator.floorRequestList.clear();
        elevator.floorRequestList.add(floorRequest);

    }
}
