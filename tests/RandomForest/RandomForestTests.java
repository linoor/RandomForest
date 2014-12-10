package RandomForest;

import RandomForestHOG.RandomForest.RandomForest;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Linoor on 2014-12-10.
 */
public class RandomForestTests {

    @Test
    public void testConstructor() {
       RandomForest randForest = new RandomForest(10, 100);
       assertEquals(10, randForest.getMaxDepth());
       assertEquals(100, randForest.getMaxNumOfTrees());
    }
    
}
