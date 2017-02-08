import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nimish on 28/01/2017.
 */
public class Car implements ParkCar{
    private int streetLength;
    private boolean isEmpty;
    private String status;
    private ArrayList<Double> arr;
    private ArrayList<Boolean> checkSpace;

   public Car(){
       this.isEmpty = false;
       this.status = "un-parked";
       this.arr = new ArrayList<>();
       this.checkSpace = new ArrayList<>();
   }
    class sensorValues{
        int sensor1;
        int sensor2;
        public sensorValues(int sensor1, int sensor2){
            this.sensor1 = sensor1;
            this.sensor2 = sensor2;
        }
    }

    @Override
    public void moveForward() {
        if(this.streetLength < 1){
            System.out.println("Cant go forward");
        }
        if (this.status.equals("Parked") ){
            System.out.println("Cant go forward");
        }
        else {
            this.streetLength--;
        }
    }

    @Override

    // Store every value in the sensorValue array, mean and standard deviation for outliers.
    // add/subtract standard deviation from mean to remove outliers.
    public boolean isEmpty() {
        for (int i = 0; i < 5; i++){
            Random ran = new Random();
            Random ran2 = new Random();
            int sensor1 = ran.nextInt(200);
            int sensor2 = ran.nextInt(200);
            double average = (sensor1 + sensor2) / 2;
            this.arr.add(average);
        }
        return false;
    }

    @Override
    public void moveBackward() {

    }

    @Override
    public void park() {

    }

    @Override
    public void unPark() {

    }

    @Override
    public CarPosition whereIs() {
        CarPosition pos = new CarPosition(this.streetLength, this.status);
        return pos;
    }
    public boolean isParkingSpace(){
       boolean hasParkingSpace = true;
       for (int i = 5; i > 0; i--){
            if (!this.checkSpace.get(checkSpace.size() - 1)){
                hasParkingSpace = false;
            }
       }
           return hasParkingSpace;

    }
}
