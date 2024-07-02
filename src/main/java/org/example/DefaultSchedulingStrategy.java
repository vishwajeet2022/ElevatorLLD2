package org.example;

public class DefaultSchedulingStrategy implements SchedulingStrategy
{

    @Override
    public void schedule(Elevator elevator, FloorRequest floorRequest) {
        System.out.println("Default moving state");
        elevator.addRequest(floorRequest);
    }
}
