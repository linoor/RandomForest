package RandomForest;

import RandomForestHOG.RandomForest.RandomForestLearner;
import Utils.DataVector;
import Utils.Helper;
import fr.ensmp.caor.levis.classifier.Classifier;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Linoor on 2014-12-10.
 */
public class RandomForestLearnerTests {

    int dataSize = 20;
    int numOfAttr = 20;
    int numOfClass = 10;
    private RandomForestLearner rfLearner;
    private List<DataVector> testingData;

    @Before
    public void setup() {
        testingData = Helper.setupTestingData(dataSize, numOfAttr, numOfClass);
    }

    @Test
    public void testConstructor() {
        rfLearner = new RandomForestLearner(testingData, 100, 10);
    }

    @Test
    public void testLearn() throws Exception {
        rfLearner = new RandomForestLearner(testingData, 10, 10);
        Classifier model = rfLearner.learn(false);
    }
}
