
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CarTest {
	Car car;
	
	@Before
	public void setUp(){
		
		car = new Car();
	
	}
	
	@Test
	public void isEmptytest() {
		boolean result = car.isEmpty(0,10);
		Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testmoveForward(){
		int result = car.moveForward(75);
		Assert.assertEquals(75, result);
		
	}
	
	@Test
	public void testmoveBackward(){
		int result = car.moveBackward(-75);
		Assert.assertEquals(-75, result);
	}
	
	@Test
	public void testpark(){
		boolean result = car.park(0, 0);
		Assert.assertEquals(true, result);
			
	}
	
	@Test
	public void testunPark(){
		boolean result = car.unPark(10, 10);
		Assert.assertEquals(true, result);
			
	}
	

}
