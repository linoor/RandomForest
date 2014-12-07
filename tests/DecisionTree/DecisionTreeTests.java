import RandomForestHOG.DecisionTree.DecisionTree;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DecisionTreeTests {
    
    private DecisionTree tree;
    private List<List<Double>> testingData;

    public DecisionTreeTests(List<List<Double>> testingData) {
        this.testingData = testingData;
    }
    
    @Before
    public void setup() {
        tree = new DecisionTree(testingData, 10);

    }
    
    @Test
    public void testBootstrapSample() {
        
    }
    
    

}