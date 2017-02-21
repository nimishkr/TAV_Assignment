package Model;

import java.util.Random;

/**
 * Created by Nimish on 21/02/2017.
 */
public class UltraSonic implements UltraSonicInterface {
    @Override
    public int getDistance() {
        Random rand = new Random();
        int distance = rand.nextInt(250);

        return distance;
    }
}
