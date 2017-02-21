package tests;

import Controller.ParkCarInterface;
import Model.ActuatorInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * Created by Nimish on 14/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActuatorTest {
    @Mock
    private ActuatorInterface actuator;
    private ParkCarInterface.PositionInfo positionInfo;
    private ArrayList<Integer> parkingSpaces;
    private int carPosition;

    @Before
    public void setActuator() {
        MockitoAnnotations.initMocks(this);
        parkingSpaces = new ArrayList<>();
        carPosition = 500;
        positionInfo = new ParkCarInterface.PositionInfo(carPosition, parkingSpaces);
    }

    @Test
    public void moveForwardTest1(){
        Mockito.when(actuator.moveForward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(250);
        actuator.moveForward();
        positionInfo.decreasePosition();

        assertEquals(249,positionInfo.getPosition());
    }

    @Test
    public void moveForwardTest2(){
        Mockito.when(actuator.moveForward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(500);
        actuator.moveForward();
        positionInfo.decreasePosition();

        assertEquals(499,positionInfo.getPosition());
    }

    @Test
    public void moveForwardTest3(){
        Mockito.when(actuator.moveForward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(0);
        actuator.moveForward();

        assertEquals(0,positionInfo.getPosition());
    }

    @Test
    public void moveBackwardTest1(){
        Mockito.when(actuator.moveBackward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(250);
        actuator.moveBackward();
        positionInfo.increasePosition();

        assertEquals(251,positionInfo.getPosition());
    }

    @Test
    public void moveBackwardTest2(){
        Mockito.when(actuator.moveBackward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(500);
        actuator.moveBackward();

        assertEquals(500,positionInfo.getPosition());
    }

    @Test
    public void moveBackwardTest3(){
        Mockito.when(actuator.moveBackward()).thenReturn(positionInfo);
        positionInfo.setStreetPosition(0);
        actuator.moveBackward();
        positionInfo.increasePosition();

        assertEquals(1,positionInfo.getPosition());
    }

}
