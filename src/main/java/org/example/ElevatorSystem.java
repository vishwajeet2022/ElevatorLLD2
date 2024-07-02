package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorSystem {
    List<Elevator> elevatorList;
    private static ElevatorSystem INSTANCE;

    int numOfElevators;
    SchedulingStrategy schedulingStrategy;
    private Lock lock;



    private ElevatorSystem(int noOfElevators, SchedulingStrategy schedulingStrategy) {
        this.elevatorList = new ArrayList<>(noOfElevators);
        this.numOfElevators = noOfElevators;  // Move these outside the loop
        this.schedulingStrategy = schedulingStrategy;
        this.lock = new ReentrantLock();

        for (int i = 0; i < noOfElevators; i++) {
            elevatorList.add(new Elevator(i + 1));
            elevatorList.get(i).start();
        }
    }


    public static ElevatorSystem getINSTANCE(int noOfElevators,SchedulingStrategy schedulingStrategy){
        if(INSTANCE==null){
            synchronized (ElevatorSystem.class){
                if(INSTANCE==null){
                    INSTANCE = new ElevatorSystem(noOfElevators,schedulingStrategy);
                }
            }
        }

        return INSTANCE;
    }

    public void requestFloor(int elevatorId,int floor){
        Elevator elevator = elevatorList.get(elevatorId-1);
        FloorRequest floorRequest = new FloorRequest(floor);
        schedulingStrategy.schedule(elevator,floorRequest);
    }


}
