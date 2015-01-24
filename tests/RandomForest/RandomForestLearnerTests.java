package RandomForest;

import RandomForestHOG.RandomForest.RandomForestLearner;
import Utils.DataVector;
import Utils.Helper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Linoor on 2014-12-10.
 */
public class RandomForestLearnerTests {

    private RandomForestLearner rfLearner;
    private List<DataVector> testingData;

    int dataSize = 20;
    int numOfAttr = 20;
    int numOfClass = 10;

    @Before
    public void setup() {
        testingData = Helper.setupTestingData(dataSize, numOfAttr, numOfClass);
    }

    @Test
    public void testConstructor() {
//        RandomForestLearner randomForestLearner = new RandomForestLearner(4, 5);
//        assertEquals(randomForestLearner.getBootstrapSize(), 4);
//        assertEquals(randomForestLearner.getAttributesSize(), 5);

        rfLearner = new RandomForestLearner(testingData, 100, 10);

    }


}
