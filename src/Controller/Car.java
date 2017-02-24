package Controller;

import Model.ActuatorInterface;
import Model.UltraSonicInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nimish on 28/01/2017.
 */
public class Car implements ParkCarInterface {
    private CarSituation carSituation;
    public PositionInfo posInfo;
    public int isEmptyCounter;
    private UltraSonicInterface sensor1;
    private UltraSonicInterface sensor2;
    private ActuatorInterface actuator;
    private ArrayList<Integer> parkingSpaces;

    public int sensorCounter1;
    public int sensorCounter2;

    public Car(UltraSonicInterface sensor1, UltraSonicInterface sensor2, ActuatorInterface actuator){
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.actuator = actuator;
        this.parkingSpaces = new ArrayList<>();
        carSituation = new CarSituation(500,false);
        this.posInfo = new PositionInfo(this.carSituation.streetPosition,parkingSpaces);
        this.isEmptyCounter = 0;
        this.sensorCounter1 = 0;
        this.sensorCounter2 = 0;
    }

    @Override
    public PositionInfo moveForward() {
        if (this.carSituation.isParked){
            System.out.println("Cant go forward please unpark");
        }
        else if(this.carSituation.getPosition() > 0 && this.carSituation.streetPosition <= 500){
            this.carSituation.streetPosition--;
            actuator.moveForward();
            isEmpty();
        }
        else {
            System.out.println("Cant go forward");
        }
        return this.posInfo;
    }
    @Override
    public PositionInfo moveBackward() {
        if (this.carSituation.isParked){
            System.out.println("Cant go back please unpark");
        }
        else if (this.carSituation.getPosition() < 500 && this.carSituation.getPosition() >= 0){
            this.getPosInfo().increasePosition();
            this.carSituation.streetPosition++;
            actuator.moveBackward();
        }
        else {
            System.out.println("The car cannot go back");
        }
        return this.posInfo;
    }

    @Override
    public int isEmpty() {
        int average = 0;
        int sensor1Sum = 0;
        int sensor2Sum = 0;
        for (int i = 0; i < 5; i++) {
            this.sensorCounter1 = 0;
            this.sensorCounter2 = 0;

            int sensor1Val = sensor1.getDistance(getCarPosition());
            int sensor2Val = sensor2.getDistance(getCarPosition());
            if (sensor1Val > 200) {
                sensorCounter1++;
            }
            if (sensor2Val > 200) {
                sensorCounter2++;
            }
            sensor1Sum += sensor1Val;
            sensor2Sum += sensor2Val;
        }
        if (sensorCounter1 > 2 && sensorCounter2 < 3){
            average = sensor2Sum / 5;
        }
        else if (sensorCounter1 < 3 && sensorCounter2 > 2){
            average = sensor1Sum / 5;
        }
        else if (sensorCounter1 > 2 && sensorCounter2 > 2){
            average = 0;
        }
        else {
            average = ((sensor1Sum / 5) + (sensor2Sum / 5)) / 2;
        }
        if (average > 100) {
            System.out.println("parking counter is " + this.isEmptyCounter + " at " + this.getCarPosition());
            if (this.isEmptyCounter == 4) {
                this.isEmptyCounter = 0;
                parkingSpaces.add(this.carSituation.getPosition());
                System.out.println("parking space at " + this.carSituation.getPosition());
            } else {
                this.isEmptyCounter++;
            }
        } else {
            this.isEmptyCounter = 0;
        }
        return average;
    }

    @Override
    public void park() {
        if (this.parkingSpaces.contains(this.carSituation.getPosition())){
            this.carSituation.isParked = true;
            this.carSituation.streetPosition += 5;
        }
        else{
            while(!this.parkingSpaces.contains(this.carSituation.getPosition())){
                moveForward();
                if (this.carSituation.streetPosition == 0) break;
            }
            if (this.carSituation.getPosition() == 0){
                this.carSituation.isParked = false;
            }
            else {
                this.carSituation.isParked = true;
                this.carSituation.streetPosition += 5;
            }
        }
    }

    @Override
    public void unPark() {
        if(this.carSituation.isCarParked()){
            this.carSituation.isParked = false;
            this.carSituation.streetPosition -= 5;
        }
        else {
            System.out.println("Car Unparked");
        }
    }

    @Override
    public CarSituation whereIs() {
        return this.carSituation;
    }

    public ArrayList<Integer> getParkingSpaces(){
        return this.parkingSpaces;
    }

    public int getCarPosition(){
        return this.carSituation.streetPosition;
    }
    public boolean isCarParked(){
        return this.carSituation.isParked;
    }
    public CarSituation getCarSituation(){
        return this.carSituation;
    }
    public PositionInfo getPosInfo(){
        return this.posInfo;
    }
    public int getIsEmptyCounter(){
        return this.isEmptyCounter;
    }

}
