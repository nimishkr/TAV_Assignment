package Model;

import Controller.ParkCarInterface;

/**
 * Created by Nimish on 13/02/2017.
 */
public interface ActuatorInterface {
    public ParkCarInterface.PositionInfo moveForward();
    public ParkCarInterface.PositionInfo moveBackward();
}
