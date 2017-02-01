/**
 * Created by nimis on 01/02/2017.
 */
public interface ParkCar {
    int roadLength = 500;
    boolean isEmpty = false;
    String status = "un-parked";
    void moveForward();
    boolean isEmpty();
    void moveBackward();
    void park();
    void unPark();
    void whereIs();

}
