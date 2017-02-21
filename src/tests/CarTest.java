package tests;


import static org.junit.Assert.*;

import Controller.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CarTest {
    Car car;
    @Before
    public void setUp(){
        car = new Car();

    }
    // TC 1
    @Test
    public void unParkTest1(){
        car.getCarSituation().streetPosition = 400;
        car.getCarSituation().isParked = true;
        car.isEmptyCounter = 5;
        car.getParkingSpaces().add(car.getCarPosition());
        car.unPark();
        Assert.assertEquals(false,car.isCarParked());
    }

    // TC 2
    @Test
    public void isEmptyTest1(){
        car.getCarSituation().streetPosition = 400;
        car.isEmptyCounter = 3;
        int actual = car.isEmpty();
        if (actual > 100){
            Assert.assertEquals(4,car.getIsEmptyCounter());
        }
    }

    // TC 3
    @Test
    public void isEmptyTest2(){
        car.getCarSituation().streetPosition = 0;
        car.isEmptyCounter = 4;
        int actual = car.isEmpty();
        if (actual > 100){
            Assert.assertEquals(0,car.getIsEmptyCounter());
        }
    }

    // TC 4
    @Test
    public void isEmptyTest3(){
        car.isEmptyCounter = 3;
        for (int i = 0; i < 7; i++){
            car.moveForward();
            if (car.isCarParked()){
                Assert.assertEquals(0,car.getIsEmptyCounter());
                System.out.println("sup");
            }
        }
    }
    // TC 5
    @Test
    public void moveBackwardTest1(){
        car.getCarSituation().streetPosition = 0;
        car.moveBackward();
        Assert.assertEquals(1,car.getCarPosition());
    }

    // TC 6
    @Test
    public void moveBackwardTest2(){
        car.getCarSituation().streetPosition = 500;
        car.moveBackward();
        Assert.assertEquals(500,car.getCarPosition());
    }

    //TC 7
    @Test
    public void moveBackwardTest3(){
        car.getCarSituation().streetPosition = 400;
        car.moveBackward();
        Assert.assertEquals(401,car.getCarPosition());
    }

    //TC 8
    @Test
    public void moveBackwardTest4(){
        car.getCarSituation().streetPosition = 400;
        car.getCarSituation().isParked = true;
        car.moveBackward();
        Assert.assertEquals(400,car.getCarPosition());
    }

    //TC 9
    @Test
    public void moveForwardTest1(){
        car.getCarSituation().streetPosition = 0;
        car.moveForward();
        Assert.assertEquals(0,car.getCarPosition());
    }

    //TC 10
    @Test
    public void moveForwardTest2(){
        car.getCarSituation().streetPosition = 500;
        car.moveForward();
        Assert.assertEquals(499,car.getCarPosition());
    }

    //TC 11
    @Test
    public void moveForwardTest3(){
        car.getCarSituation().streetPosition = 400;
        car.moveForward();
        Assert.assertEquals(399,car.getCarPosition());
    }

    //TC 12
    @Test
    public void moveForwardTest4(){
        car.getCarSituation().streetPosition = 400;
        car.getCarSituation().isParked = true;
        car.moveForward();
        Assert.assertEquals(400,car.getCarPosition());
    }

    //TC 13
    @Test
    public void parkTest1(){
        car.getCarSituation().streetPosition = 201;
        car.getParkingSpaces().add(200);
        car.park();
        Assert.assertEquals(true, car.isCarParked());
    }

    //TC 14
    @Test
    public void parkTest2(){
        car.getCarSituation().streetPosition = 1;
        car.park();
        Assert.assertEquals(false, car.isCarParked());
    }

    //TC 15
    @Test
    public void unParkTest2(){
        car.getCarSituation().streetPosition = 500;
        car.unPark();
        Assert.assertEquals(false, car.isCarParked());
    }

    //TC 16
    @Test
    public void whereIsTest1() {
        for (int i = 0; i < 5; i++) {
            car.moveForward();
        }
        int expectedPosition = 495;

        Assert.assertEquals(expectedPosition, car.whereIs().getPosition());
        Assert.assertEquals(false, car.whereIs().isCarParked());
    }

    //TC 17
    @Test
    public void whereIsTest2() {
        car.getCarSituation().streetPosition = 450;
        car.getCarSituation().isParked = true;
        int expectedPosition = 450;
        Assert.assertEquals(expectedPosition, car.whereIs().getPosition());
        Assert.assertEquals(true, car.whereIs().isCarParked());
    }

    //TC 18
    @Test
    public void whereIsTest3() {
        int expectedPosition = 500;
        Assert.assertEquals(expectedPosition, car.whereIs().getPosition());
        Assert.assertEquals(false, car.whereIs().isCarParked());
    }

}
