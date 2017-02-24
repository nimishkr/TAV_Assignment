package tests;

import Controller.Car;
import Controller.ParkCarInterface;
import Model.ActuatorInterface;
import Model.UltraSonicInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;

/**
 * Created by nimis on 20/02/2017.
 */
public class IntegrationTest {
    @Mock
    private ActuatorInterface actuator;

    @Mock
    private UltraSonicInterface sensor1;

    @Mock
    private UltraSonicInterface sensor2;

    @InjectMocks
    private Car testCar;

    private int minSpacesCounter = 500;
    private int lastSpaceCounter = 0;
    private int minSpacePosition = 0;

    @Before
    public void setUp(){
        testCar = new Car();
        sensor1 = Mockito.mock(UltraSonicInterface.class);
        sensor2 = Mockito.mock(UltraSonicInterface.class);
        actuator = Mockito.mock(ActuatorInterface.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void IntegrationTest1(){
        for (int i = 500; i >= 0; i--) {
            if (i == 0) {
                Mockito.when(actuator.moveForward()).thenReturn(testCar.getPosInfo());
            } else {
                testCar.getPosInfo().decreasePosition();
                Mockito.when(actuator.moveForward()).thenReturn(testCar.getPosInfo());
            }
        }
        for (int i = 500; i > 0; i--){
            if (i > 350 && i < 354){
                lastSpaceCounter++;
                Mockito.when(sensor1.isEmpty(i)).thenReturn(120);
                Mockito.when(sensor2.isEmpty(i)).thenReturn(120);
                sensor1.isEmpty(i);
                sensor2.isEmpty(i);
                actuator.moveForward();
            }
            else if (i > 250 && i < 256){
                lastSpaceCounter++;
                Mockito.when(sensor1.isEmpty(i)).thenReturn(150);
                Mockito.when(sensor2.isEmpty(i)).thenReturn(150);
                sensor1.isEmpty(i);
                sensor2.isEmpty(i);
                actuator.moveForward();
            }
            else if (i > 150 && i < 159){
                lastSpaceCounter++;
                Mockito.when(sensor1.isEmpty(i)).thenReturn(120);
                Mockito.when(sensor2.isEmpty(i)).thenReturn(120);
                sensor1.isEmpty(i);
                sensor2.isEmpty(i);
                actuator.moveForward();
            }
            else {
                if (lastSpaceCounter > 0 && lastSpaceCounter < 5){
                    lastSpaceCounter = 0;
                }
               else if (lastSpaceCounter!= 0 && lastSpaceCounter > 4 && lastSpaceCounter < minSpacesCounter){
                    minSpacesCounter = lastSpaceCounter;
                    lastSpaceCounter = 0;
                   minSpacePosition = testCar.getCarPosition();
                }
                Mockito.when(sensor1.isEmpty(i)).thenReturn(20);
                Mockito.when(sensor2.isEmpty(i)).thenReturn(20);
                sensor1.isEmpty(i);
                sensor2.isEmpty(i);
                actuator.moveForward();
            }
        }
        for(int i = 0; i < minSpacePosition; i++){
            testCar.getPosInfo().increasePosition();
            Mockito.when(actuator.moveBackward()).thenReturn(testCar.getPosInfo());
            actuator.moveBackward();
        }
        //Assert.assertEquals(250,testCar.getCarPosition());
        testCar.park();
        Assert.assertTrue(testCar.isCarParked());
        testCar.unPark();
        for (int i = testCar.getCarPosition(); i >= 0; i--){
            Mockito.when(sensor1.isEmpty(i)).thenReturn(20);
            Mockito.when(sensor2.isEmpty(i)).thenReturn(20);
            actuator.moveForward();
        }
        System.out.println(testCar.getCarPosition());


    }
}
