import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nimish on 28/01/2017.
 */
public class Car implements ParkCar{
    private CarSituation carSituation;
    private PositionInfo posInfo;
    private int isEmptyCounter;
    private ArrayList<Integer> parkingSpaces;

   public Car(){
       this.parkingSpaces = new ArrayList<>();
       carSituation = new CarSituation(500,false);
       this.posInfo = new PositionInfo(this.carSituation.streetPosition,parkingSpaces);
       this.isEmptyCounter = 0;
   }

    @Override
    public PositionInfo moveForward() {
        if(this.carSituation.getPosition() < 1){
            System.out.println("Cant go forward");
        }
        if (this.carSituation.isParked){
            System.out.println("Cant go forward please unpark");
        }
        else {
            this.carSituation.streetPosition --;
            isEmpty();
        }
        return this.posInfo;
    }
    @Override
    public PositionInfo moveBackward() {
        if (this.carSituation.getPosition()==500){
            System.out.println("Cant move back");
        }
        if (this.carSituation.isParked){
            System.out.println("Cant go back please unpark");
        }
        else {
            this.carSituation.streetPosition++;
        }
        return this.posInfo;
    }

    @Override
    public int isEmpty() {
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
        for (Integer aTemp3arr : temp3arr) {
            sum += aTemp3arr;
        }
        average = sum / temp3arr.size();

        if (average < 120){
            if (this.isEmptyCounter == 4){
                this.isEmptyCounter = 0;
                parkingSpaces.add(this.carSituation.getPosition());
                System.out.println("parking space at " + this.carSituation.getPosition());
            }
            else {
                this.isEmptyCounter++;
            }
        }
        else {
            this.isEmptyCounter = 0;
        }
        return (int)average;
    }

    @Override
    public void park() {
        if (this.parkingSpaces.contains(this.carSituation.getPosition())){
            this.carSituation.isParked = true;
            this.carSituation.streetPosition -= 5;
        }
        else{
            while(!this.parkingSpaces.contains(this.carSituation.getPosition())){
                moveForward();
            }
            if (this.carSituation.getPosition() == 0){
                this.carSituation.isParked = false;
            }
            else {
                this.carSituation.isParked = true;
                this.carSituation.streetPosition -= 5;
            }
        }
    }

    @Override
    public void unPark() {
        if(this.carSituation.isCarParked()){
            this.carSituation.isParked = false;
            this.carSituation.streetPosition += 5;
        }
        else {
            System.out.println("Car Unparked");
        }
    }

    @Override
    public CarSituation whereIs() {
       return this.carSituation;
    }

    public static void main (String args[]){
       Car car = new Car();

       for (int i = 0; i < 100; i++){
           car.moveForward();
       }
    }
}
