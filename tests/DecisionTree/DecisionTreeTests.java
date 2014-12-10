package DecisionTree;

import RandomForestHOG.DecisionTree.DecisionTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTests {
    
    private DecisionTree tree;
    private List<List<Double>> testingData;

    public DecisionTreeTests() {
        setupTestingData();
        setup();
        
    }
    
    public DecisionTreeTests(List<List<Double>> testingData) {
        this.testingData = testingData;
        setup();
    }
    
    @Before
    public void setupTestingData() {
        int dataSize = 100;
        int attrSize = 10;
        int classSize = 10;

        testingData = new ArrayList<List<Double>>(dataSize);
        for (List<Double> record : testingData) {
            record = new ArrayList<Double>(attrSize+1);
            record.add((double)(int)Math.random()*classSize);
            for (int i = 0; i < attrSize; i++) {
                record.add(Math.random());
            }
        }
    }

    
    @Before
    public void setup() {
        tree = new DecisionTree(testingData, 0);
    }
    
    @Test
    public void testBootstrapSample() {
        
    }
    
    
    
    

}