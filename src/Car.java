import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nimish on 28/01/2017.
 */
public class Car implements ParkCar{
    private CarSituation carPos;
    private PositionInfo posInfo;
    private int isEmptyCounter;
    private ArrayList<Double> arr;
    private ArrayList<Boolean> checkSpace;
    private ArrayList<Integer> parkingSpaces;

   public Car(){
       carPos = new CarSituation(500,"unparked");
       this.parkingSpaces = new ArrayList<>();
       this.posInfo = new PositionInfo(this.carPos.position,parkingSpaces);
       this.isEmptyCounter = 0;
       this.arr = new ArrayList<>();
       this.checkSpace = new ArrayList<>();
   }


    @Override
    public PositionInfo moveForward() {
        if(this.carPos.getPosition() < 1){
            System.out.println("Cant go forward");
        }
        if (this.carPos.getStatus().equals("parked") ){
            System.out.println("Cant go forward");
        }
        else {
            this.carPos.position --;
            isEmpty();
        }
        return(posInfo);
    }

    @Override

    // Store every value in the sensorValue array, mean and standard deviation for outliers.
    // add/subtract standard deviation from mean to remove outliers.
    public boolean isEmpty() {
        ArrayList<Integer> tempArr = new ArrayList<>();
       for (int i = 0; i < 5; i++){
            Random ran = new Random();
            Random ran2 = new Random();
            int sensor1 = ran.nextInt(200);
            tempArr.add(sensor1);
            int sensor2 = ran2.nextInt(200);
            tempArr.add(sensor2);
        }
        ArrayList<Double> temp2Arr = new ArrayList<>();
        int sum = 0;
        double mean;
        for (int i = 0; i < 10; i++){
            sum+=tempArr.get(i);
        }
        mean = sum / 10;
        for (int i = 0; i < 10; i++){
            temp2Arr.add(Math.pow(tempArr.get(i) - mean,2));
        }
        sum = 0;
        for (int i = 0; i < 10;i++){
            sum+=temp2Arr.get(i);
        }
        double average = sum / 10;
        double stanDistr = Math.sqrt(average);

        ArrayList<Integer> temp3arr = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            if (tempArr.get(i) < mean){
                if (mean - stanDistr - 5 < tempArr.get(i)){
                    temp3arr.add(tempArr.get(i));
                }
            }
            else if (tempArr.get(i) > mean){
                if (mean + stanDistr + 5 > tempArr.get(i)){
                    temp3arr.add(tempArr.get(i));
                }
            }
        }
        sum = 0;
        for (int i = 0; i < temp3arr.size(); i++){
            sum+=temp3arr.get(i);
        }
        average = sum / temp3arr.size();

        if (average < 120){
            if (this.isEmptyCounter == 4){
                this.isEmptyCounter = 0;
                parkingSpaces.add(this.carPos.getPosition());
                System.out.println("parking space at " + this.carPos.getPosition());
            }
            else {
                this.isEmptyCounter++;
            }
            return true;
        }
        else {
            this.isEmptyCounter = 0;
        }
        return false;
    }

    @Override
    public void moveBackward() {
        if (this.carPos.getPosition()==500){
            System.out.println("Cant move back");
        }
        else {
            this.carPos.position--;
        }
    }

    @Override
    public void park() {
        if (this.parkingSpaces.contains(this.carPos.getPosition())){
            this.carPos.status = "parked";
            this.carPos.position -= 5;
        }
        else{
            while(!this.parkingSpaces.contains(this.carPos.getPosition())){
                moveForward();
            }
            if (this.carPos.getPosition() == 0){
                this.carPos.status = "unParked";
            }
            else {
                this.carPos.status = "parked";
                this.carPos.position -= 5;
            }
        }
    }

    @Override
    public void unPark() {
        if(this.carPos.getStatus().equals("parked")){
            this.carPos.status = "unParked";
            this.carPos.position += 5;
        }
        else {
            System.out.println("Car Unparked");
        }
    }

    @Override
    public CarSituation whereIs() {
       return this.carPos;
    }

    public static void main (String args[]){
       Car car = new Car();

       for (int i = 0; i < 100; i++){
           car.moveForward();
       }
    }
}
