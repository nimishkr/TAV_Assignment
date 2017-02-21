package tests;

import Controller.Car;
import Model.Actuator;
import Model.ActuatorInterface;
import Model.UltraSonic;
import Model.UltraSonicInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * Created by nimis on 20/02/2017.
 */
public class IntegrationTest {
    @Spy
    private Car spy;

    @Mock
    private Actuator actuator;

    @Mock
    private UltraSonic sensor1;

    @Mock
    private UltraSonic sensor2;

    @InjectMocks
    private Car testCar;

    private int minSpacesCounter = 500;
    private int lastSpaceCounter = 0;
    private int minSpacePosition = 0;

    @Before
    public void setUp(){
        spy = new Car();
        testCar = new Car();
        MockitoAnnotations.initMocks(this);
        spy = Mockito.spy(Car.class);
    }

    @Test
    public void IntegrationTest1(){
        for (int i = 500; i >= 0; i--) {
            if (i == 0) {
                Mockito.when(actuator.moveForward(i)).thenReturn(i);
            } else {
                Mockito.when(actuator.moveForward(i)).thenReturn(i - 1);
            }
        }
        for (int i = 500; i > 0; i--){
            if (i > 350 && i < 358){
                lastSpaceCounter++;
                Mockito.when(sensor1.getDistance()).thenReturn(120);
                Mockito.when(sensor2.getDistance()).thenReturn(120);
                testCar.moveForward();
            }
            else if (i > 250 && i < 256){
                lastSpaceCounter++;
                Mockito.when(sensor1.getDistance()).thenReturn(150);
                Mockito.when(sensor2.getDistance()).thenReturn(150);
                testCar.moveForward();
            }
            else if (i > 150 && i < 159){
                lastSpaceCounter++;
                Mockito.when(sensor1.getDistance()).thenReturn(120);
                Mockito.when(sensor2.getDistance()).thenReturn(120);
                testCar.moveForward();
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
                Mockito.when(sensor1.getDistance()).thenReturn(20);
                Mockito.when(sensor2.getDistance()).thenReturn(20);
                testCar.moveForward();
            }
        }
        for(int i = 0; i < minSpacePosition; i++){
            Mockito.when(actuator.moveBackward(i)).thenReturn(i+1);
            testCar.moveBackward();
        }
        Assert.assertEquals(250,testCar.getCarPosition());
        testCar.park();
        Assert.assertTrue(testCar.isCarParked());
        testCar.unPark();
        for (int i = testCar.getCarPosition(); i >= 0; i--){
            Mockito.when(sensor1.getDistance()).thenReturn(20);
            Mockito.when(sensor2.getDistance()).thenReturn(20);
            testCar.moveForward();
        }
        System.out.println(testCar.getCarPosition());


    }
}
