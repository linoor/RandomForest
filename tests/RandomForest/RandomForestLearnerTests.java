package RandomForest;

import RandomForestHOG.RandomForest.RandomForestLearner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Linoor on 2014-12-10.
 */
public class RandomForestLearnerTests {

    @Test
    public void testConstructor() {
        RandomForestLearner randomForestLearner = new RandomForestLearner(4, 5);
        assertEquals(randomForestLearner.getBootstrapSize(), 4);
        assertEquals(randomForestLearner.getAttributesSize(), 5);
    }
}
