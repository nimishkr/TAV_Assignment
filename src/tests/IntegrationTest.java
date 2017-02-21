package tests;

import Controller.Car;
import Model.ActuatorInterface;
import Model.UltraSonicInterface;
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
    private ActuatorInterface actuator;

    @Mock
    private UltraSonicInterface sensors;

    @Mock
    private UltraSonicInterface sensor2;

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
                Mockito.when(sensors.isEmpty()).thenReturn(120);
                testCar.moveForward();
                System.out.println(sensors.isEmpty());
            }
            else if (i > 250 && i < 256){
                Mockito.when(sensors.isEmpty()).thenReturn(120);
                testCar.moveForward();
            }
            else if (i > 150 && i < 159){
                Mockito.when(sensors.isEmpty()).thenReturn(120);
                testCar.moveForward();
            }
        }
    }
}
