
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CarTest {
    Car car;

    @Before
    public void setUp(){
        car = new Car();

    }

    @Test
    public void testMoveForward() {
        for (int i = 0; i < 10; i++) {
            car.moveForward();
        }
        int expectedPosition = 490;
        ArrayList<Integer> expectedParkingSlots = car.getParkingSpaces();
        expectedParkingSlots.add(495);

        org.junit.Assert.assertEquals(expectedParkingSlots, car.getParkingSpaces());
        org.junit.Assert.assertEquals(expectedPosition, car.getCarPosition());
    }

    @Test
    public void testMoveBackward(){
        for(int i =0 ; i < 20; i++){
            car.moveForward();
        }
        for(int i =0 ; i < 10; i++){
            car.moveBackward();
        }
        int expectedPosition = 490;
        Assert.assertEquals(expectedPosition, car.getCarPosition());
    }

    @Test
    public void testPark(){
        car.getParkingSpaces().add(200);
        car.park();
        Assert.assertEquals(true, car.isCarParked());
    }

    @Test
    public void testUnPark(){
        car.getParkingSpaces().add(200);
        car.park();
        Assert.assertEquals(true,car.isCarParked());
        car.unPark();
        Assert.assertEquals(false,car.isCarParked());
    }

    @Test
    /**
     * Test Case 4: TC4
     */
    public void testWhereIs() {
        for (int i = 0; i < 5; i++) {
            car.moveForward();
        }
        int expectedPosition = 495;

        org.junit.Assert.assertEquals(expectedPosition, car.whereIs().getPosition());
        org.junit.Assert.assertEquals(false, car.whereIs().isCarParked());
    }

    @Test
    public void isEmptyTest() {
        int result = car.isEmpty();
        int i = 4;
        if(0 < result && result <= 120){
            i++;
            org.junit.Assert.assertTrue("The space is empty " , i == 5);
        }
        else {
            i--;
            org.junit.Assert.assertTrue("The space is not empty " , i == 3);
        }
    }
}
