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

    @Before
    public void setUp(){
        spy = new Car();
        testCar = new Car();
        MockitoAnnotations.initMocks(this);
        spy = Mockito.spy(Car.class);
    }

    @Test
    public void IntegrationTest1(){
        testCar.posInfo.setStreetPosition(500);
        for (int i = 500; i >= 0; i--){
            if (i > 350 && i < 358){
                Mockito.when(sensor1.getDistance()).thenReturn(120);
                Mockito.when(sensor2.getDistance()).thenReturn(120);
                Mockito.when(actuator.moveForward(i)).thenReturn(i--);
                testCar.moveForward();
            }
            else if (i > 250 && i < 256){
                Mockito.when(sensor1.getDistance()).thenReturn(150);
                Mockito.when(sensor2.getDistance()).thenReturn(150);
                Mockito.when(actuator.moveForward(i)).thenReturn(i--);
                testCar.moveForward();
            }
            else if (i > 150 && i < 159){
                Mockito.when(sensor1.getDistance()).thenReturn(120);
                Mockito.when(sensor2.getDistance()).thenReturn(120);
                Mockito.when(actuator.moveForward(i)).thenReturn(i--);
                testCar.moveForward();
            }
            else {
                Mockito.when(sensor1.getDistance()).thenReturn(20);
                Mockito.when(sensor2.getDistance()).thenReturn(20);
                Mockito.when(actuator.moveForward(i)).thenReturn(i--);
                testCar.moveForward();
            }
        }
        int min = testCar.getParkingSpaces().get(0);
        for(Integer i: testCar.getParkingSpaces()){
            if (i < min) min = i;
        }
        for(int i = 0; i < min; i++){
            Mockito.when(actuator.moveBackward(i)).thenReturn(i++);
            testCar.moveBackward();
        }
        Assert.assertEquals(251,testCar.whereIs());

        testCar.park();
        Assert.assertTrue(testCar.isCarParked());
        testCar.unPark();
        for (int i = testCar.getCarPosition(); i >= 0; i--){
            Mockito.when(sensor1.getDistance()).thenReturn(20);
            Mockito.when(sensor2.getDistance()).thenReturn(20);
            Mockito.when(actuator.moveForward(i)).thenReturn(i--);
            testCar.moveForward();
        }


    }
}
