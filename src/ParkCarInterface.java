import java.util.ArrayList;

/**
 * Created by nimis on 01/02/2017.
 */
public interface ParkCarInterface {

    /**
     Description: This class holds the position of the car and returns whether the car is parked or not.
     Pre-condition: Car is on the street and in the 0-500 range
     Post-Condition: None
     Test-cases: None
     */
    class CarSituation {
        int streetPosition;
        boolean isParked;

        public CarSituation(int streetPosition, boolean isParked){
            this.streetPosition = streetPosition;
            this.isParked = isParked;
        }
        public int getPosition(){
            return this.streetPosition;
        }
        public boolean isCarParked(){
            return this.isParked;
        }
    }

    /**
     Description: This is the data structure that holds the position of the car as well as the position of free parking spaces
     Pre-condition: Car is on the street and in the 0-500 range
     Post-Condition:  None
     Test-cases: None
     */
    class PositionInfo{
        int streetPosition;
        ArrayList<Integer>parkingSpaces;
        public PositionInfo(int streetPosition, ArrayList parkingSpaces){
            this.streetPosition = streetPosition;
            this.parkingSpaces = parkingSpaces;
        }
        public int getPosition(){
            return this.streetPosition;
        }
        public ArrayList<Integer> getParkingSpaces(){
            return this.parkingSpaces;
        }
    }


    /**
     Description: This method moves forward the car by 1 meter when called and returns the data structure PositionInfo
     Pre-condition: Car is un-parked and in between the range of 0-499.
     Post-condition: The car has moved 1 meter ahead.
     Test Case: T1
     */
    PositionInfo moveForward();


    /**
     Description: This method moves the car backwards by 1 meter when called and returns the data structure PositionInfo
     Pre-condition: Car is un-parked and in between the range of 1-500.
     Post-Condition: The car is moved backwards by 1 meter
     Test Case: T2
     */
    PositionInfo moveBackward();


    /**
     Description: The method checks if the distance between the car and it's right side is equal or greater to the width of the car.
     Pre-condition: Car is on the street and in the 0-500 range.
     Post-Condition: Returns the distance obtained by the sensors.
     Test Case: T3
     */
    int  isEmpty();


    /**
     Description: This method parks the car if the car is on a parking space otherwise keeps moving forward until it finds a free parking space.
     Pre-condition: Car is un-parked and on the street and in the 0-500 range. There must be at least 1 parking spot on the street.
     Post-Condition: The car is parked turning isParked boolean true.
     Test Case: T4
     */
    void park();


    /**
     Description: This method moves the car out of the parking space and moves ahead by 5 metres.
     Pre-condition: Car is parked on the street.
     Post-Condition: The car moves out of the parking space switching isParked variable to false and the car's position
     increases by 5.
     Test Case: T5
     */
    void unPark();


    /**
     Description: The method returns the position of the car along with the car's status.
     Pre-condition: Car is on the street and in the 0-500.
     Post-Condition: None
     Test Case: T6
     */
    CarSituation whereIs();

}
