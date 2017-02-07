/**
 * Created by nimis on 01/02/2017.
 */
public interface ParkCar {
    void moveForward();
    boolean isEmpty();
    void moveBackward();
    void park();
    void unPark();
    CarPosition whereIs();

}
