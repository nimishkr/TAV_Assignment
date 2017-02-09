import java.util.ArrayList;

/**
 * Created by nimis on 01/02/2017.
 */
public interface ParkCar {
    PositionInfo moveForward();
    boolean isEmpty();
    void moveBackward();
    void park();
    void unPark();
    CarSituation whereIs();

    class CarSituation {
        int position;
        String status;

        public CarSituation(int position, String status){
            this.position = position;
            this.status = status;
        }
        public int getPosition(){
            return this.position;
        }
        public String getStatus(){
            return this.status;
        }
    }
    class PositionInfo{
        int position;
        ArrayList<Integer>parkingSpaces;
        public PositionInfo(int position, ArrayList parkingSpaces){
            this.position = position;
            this.parkingSpaces = parkingSpaces;
        }
        public int getPosition(){
            return this.position;
        }
        public ArrayList<Integer> getParkingSpaces(){
            return this.parkingSpaces;
        }
    }

}
