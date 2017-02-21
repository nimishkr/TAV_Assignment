package tests;

import Model.UltraSonicInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

/**
 * Created by nimis on 14/02/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SensorTest {
    @Mock
    private UltraSonicInterface ultraSonic;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(ultraSonic);
    }

    @Test
    public void getDistanceTest1(){
        ultraSonic.getDistance();
        Mockito.verify(ultraSonic,times(1)).getDistance();
        Assert.assertTrue((0 <= ultraSonic.getDistance() && ultraSonic.getDistance() <= 250));
    }
    @Test
    public void getDistanceTest2() {
        Mockito.when(ultraSonic.getDistance()).thenReturn(0);
        Assert.assertEquals(0, ultraSonic.getDistance());

    }


}
