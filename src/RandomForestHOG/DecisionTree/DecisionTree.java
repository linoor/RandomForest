package RandomForestHOG.DecisionTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import com.modeliosoft.modelio.javadesigner.annotations.objid;

// @objid ("61261918-d6ad-4d4d-a19f-e6c7088f5dd6")
public class DecisionTree  {	
	private int dataN;
	private int trainN;
	private int testN;
	private int attrN;
	private int attrSampleN;
	private TreeNode rootNode;

    // @objid ("92a699ab-b86a-40cc-8fdb-4eca568fa8a6")
    public DecisionTree() {
    	// TODO
    }

    // @objid ("95f01270-0b39-4c6b-bbf3-fb177f21545e")
    public DecisionTree(final List<List<Double>> data, final int treeNum) {
    	dataN = data.size();
    	if (0 >= dataN) {
    		System.out.println("DecisionTree: data empty...");
    		return;
    	}
    	
    	trainN = dataN * 2 / 3; // TODO should discuss how to determine the size of training set
    	testN = dataN - trainN;
    	attrN = data.get(0).size() - 1; // -1 for the first element being class (undetermined)
    	attrSampleN = attrN / 2;  // TODO should discuss how many bootstrapped attributes
    	
    	/* Initialize training, testing data set */
    	List<List<Double>> train, test;
    	train = new ArrayList<List<Double>>(trainN);
    	test = new ArrayList<List<Double>>(testN);
    	for (List<Double> d : train) {
    		d = new ArrayList<Double>();
    	}
    	for (List<Double> d : test) {
    		d = new ArrayList<Double>();
    	}
    	List<Integer> attr = new ArrayList<Integer>(attrSampleN);
    	
    	bootstrapSample(data, train, test);
    	bootstrapAttr(attr);
    	
    	rootNode = createTree(train, attr, treeNum);
    }

    // @objid ("bd35c418-14d7-4599-891b-34837487a39c")
    private void bootstrapSample(final List<List<Double>> data, List<List<Double>> train, List<List<Double>> test) {
    	ArrayList<Integer> rand = new ArrayList<Integer>(dataN);
    	for (int i = 0; i < dataN; i++) {
    		rand.set(i, i);
    	}
    	Collections.shuffle(rand);
    	for (int i = 0; i < dataN*2/3; i++) {
    		train.set(i, data.get(rand.get(i)));
    	}
    	for (int i = dataN*2/3+1; i < dataN; i++) {
    		test.set(i, data.get(rand.get(i)));
    	}
    }

    // @objid ("22963c8e-9140-49f2-beb7-3b2458a06c51")
    private void bootstrapAttr(List<Integer> attr) {
    	ArrayList<Integer> rand = new ArrayList<Integer>(attrN);
    	
    	// start from 1 because the first entry of a record is class value
    	for (int i = 1; i < attrN+1; i++) {
    		rand.set(i, i);
    	}
    	Collections.shuffle(rand);
        attr = rand.subList(0, attrSampleN);
    }

    // @objid ("11f42db2-137b-4fd3-8d5c-065ee3ecdf65")
    private TreeNode createTree(final List<List<Double>> train, List<Integer> attr, final int nTree) {
    	TreeNode root = new TreeNode();
    	root.setData(train);
    	recursiveSplit(root, attr);
    	return root;
    }

    // @objid ("008d3f40-e60d-4c6e-9eb2-7018b83bf180")
    private void recursiveSplit(final TreeNode parent, List<Integer> attr) {
    	double curClass = parent.checkIfSameClass(); 
    	if (-1 == curClass) {

    		// Step A
    		int minAt = -1;
    		double minAtVal = -1;
    		findSplitPosition(parent.getData(), attr, minAt, minAtVal);
    		
    		// Step B
    		// TODO

    		// Step C
    		// TODO
    		
    	} else {
    		parent.setClassVal(curClass);
    		parent.setLeftChild(null);
    		parent.setRightChild(null);
    		return;
    	}
    }

    /**
     * Find the splitting attribute and value that create minimum entropy
     * @param data
     * @param attr
     * @param minAt
     * @param minAtVal
     */
    private void findSplitPosition(List<List<Double>> data, List<Integer> attr, int minAt, double minAtVal) {
    	double minEntropy = Double.MAX_VALUE;
    	for (int at : attr) {
    		for (int i = 0, len = data.size(); i < len; i++) {
    			double ent = calEntropy(data, at, data.get(i).get(at));
    			if (ent < minEntropy) {
    				minEntropy = ent;
    				minAt = at;
    				minAtVal = data.get(i).get(at);
    			}
    		}
		}
	}

	private double calEntropy(List<List<Double>> data, int attr, double val) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	// @objid ("943639d0-f911-4e72-b5b3-3087f8f11863")
    public List<Integer> classify(final List<Double> testData) {
        // TODO Auto-generated return
        return new ArrayList<Integer>();
    }

    // @objid ("76d43c96-c471-4b7d-a417-d0a16d9c295c")
    public String saveToString() {
        // TODO Auto-generated return
        return null;
    }

    // @objid ("359fef83-1f91-4226-b4e6-33118900cb50")
    public void loadFromString(final String tree) {
    	// TODO
    }

}
