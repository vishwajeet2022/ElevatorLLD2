package org.example;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;


class Elevator extends Thread{
    Logger logger = Logger.getLogger(Elevator.class.getName());
    int elevatorId;

    ElevatorState elevatorState;
    int currentFloor;
    BlockingQueue<FloorRequest> floorRequestList;

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Queue<FloorRequest> getFloorRequestList() {
        return floorRequestList;
    }

    public void setFloorRequestList(BlockingQueue<FloorRequest> floorRequestList) {
        this.floorRequestList = floorRequestList;
    }


    Elevator(int elevatorId){
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
        this.elevatorState = new IdleState();
        floorRequestList = new ArrayBlockingQueue<>(100);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            processRequest();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addRequest(FloorRequest floorRequest){
        floorRequestList.add(floorRequest);
    }

    public void processRequest() {
        while (!floorRequestList.isEmpty()){
            FloorRequest floorRequest = floorRequestList.poll();
            moveToFloor(floorRequest.getFloor());
        }
        setElevatorState(new IdleState());
    }

    public void moveToFloor(int floor) {
        System.out.println("Moving");
        currentFloor = floor;
    }


}
