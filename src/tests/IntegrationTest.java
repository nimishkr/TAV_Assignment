package tests;

import Controller.Car;
import Model.ActuatorInterface;
import Model.UltraSonicInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.*;

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
    private int minSpacePosition = 500;

    @Before
    public void setUp(){
        sensor1 = Mockito.mock(UltraSonicInterface.class);
        sensor2 = Mockito.mock(UltraSonicInterface.class);
        actuator = Mockito.mock(ActuatorInterface.class);
        testCar = new Car(sensor1,sensor2,actuator);
        testCar.getPosInfo().setStreetPosition(500);
        MockitoAnnotations.initMocks(this);
        File file = new File("parkingspace.txt");
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(file))){
            line = read.readLine();
            int previousNum = 500;
            while (line != null && !line.equals("")){
                int position = Integer.parseInt(line);
                if (previousNum - position > 1){
                    if (lastSpaceCounter > 4){
                        if (lastSpaceCounter < minSpacesCounter){
                            minSpacesCounter = lastSpaceCounter;
                            minSpacePosition = previousNum;
                        }
                    }
                    lastSpaceCounter = 1;
                    previousNum = position;
                }
                else{
                    lastSpaceCounter++;
                    System.out.println("Last space counter is " + lastSpaceCounter);
                    previousNum = position;
                }

                Mockito.when(sensor1.getDistance(position)).thenReturn(150);
                Mockito.when(sensor2.getDistance(position)).thenReturn(150);
                line = read.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntegrationTest1(){
        for (int i = 500; i >= 0; i--){
            Mockito.when(actuator.moveForward()).thenReturn(testCar.getPosInfo());
            testCar.moveForward();
        }
        for(int i = 0; i < minSpacePosition; i++){
            Mockito.when(actuator.moveBackward()).thenReturn(testCar.getPosInfo());
            testCar.moveBackward();
        }
        Assert.assertEquals(251,testCar.getCarPosition());
        testCar.park();
        Assert.assertTrue(testCar.isCarParked());
        testCar.unPark();
        for (int i = testCar.getCarPosition(); i >= 0; i--){
            Mockito.when(sensor1.getDistance(i)).thenReturn(20);
            Mockito.when(sensor2.getDistance(i)).thenReturn(20);

            testCar.moveForward();
        }
        System.out.println(testCar.getCarPosition());


    }
}
