package Model;

import Controller.ParkCarInterface;

/**
 * Created by Nimish on 21/02/2017.
 */
public class Actuator implements ActuatorInterface {
    @Override
    public int moveForward(int position) {
        int newPos = position;
        if (position > 0 && position <= 500){
            newPos--;
        }
        return newPos;
    }

    @Override
    public int moveBackward(int position) {
        int newPos = position;
        if (position >= 0 && position < 500){
            newPos++;
        }
        return newPos;
    }
}
