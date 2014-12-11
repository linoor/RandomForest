package RandomForestHOG.DecisionTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("61261918-d6ad-4d4d-a19f-e6c7088f5dd6")
public class DecisionTree  {
    private int dataN;
    private int trainN;
    private int testN;
    private int attrN;
    private int attrSampleN;
    private TreeNode rootNode;

    @objid ("92a699ab-b86a-40cc-8fdb-4eca568fa8a6")
    public DecisionTree() {
        // TODO
    }

    @objid ("95f01270-0b39-4c6b-bbf3-fb177f21545e")
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
        List<Integer> attr = new ArrayList<Integer>();

        bootstrapSample(data, train, test);
        attr = bootstrapAttr(attr);

        rootNode = createTree(train, attr, treeNum);
    }

    @objid ("bd35c418-14d7-4599-891b-34837487a39c")
    private void bootstrapSample(final List<List<Double>> data, List<List<Double>> train, List<List<Double>> test) {
        ArrayList<Integer> rand = new ArrayList<Integer>(dataN);
        for (int i = 0; i < dataN; i++) {
            rand.add(i);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < trainN; i++) {
            train.add(data.get(rand.get(i)));
        }
        for (int i = trainN; i < trainN+testN; i++) {
            test.add(data.get(rand.get(i)));
        }
    }

    @objid ("22963c8e-9140-49f2-beb7-3b2458a06c51")
    private List<Integer> bootstrapAttr(List<Integer> attr) {
        ArrayList<Integer> rand = new ArrayList<Integer>(attrN);

        // start from 1 because the first entry of a record is class value
        for (int i = 0; i < attrN; i++) {
            rand.add(i);
        }
        Collections.shuffle(rand);
        attr = rand.subList(0, attrSampleN);
        return attr;
    }

    @objid ("11f42db2-137b-4fd3-8d5c-065ee3ecdf65")
    private TreeNode createTree(final List<List<Double>> train, List<Integer> attr, final int nTree) {
        TreeNode root = new TreeNode();
        root.setData(train);
        recursiveSplit(root, attr);
        return root;
    }

    @objid ("008d3f40-e60d-4c6e-9eb2-7018b83bf180")
    private void recursiveSplit(final TreeNode parent, List<Integer> attr) {
        double curClass = parent.checkIfSameClass();
        if (-1 == curClass) {

            // Step A
            // find the split attribute and its value based on minimum entropy
//            int minAt = -1;
//            double minAtVal = -1;
            SplitAttrObj attrObj = new SplitAttrObj();
            
            assert(0 < attr.size());
            findSplitPosition(parent.getData(), attr, attrObj);
            System.out.println("Min Attr: "+attrObj.attr+" Min Val: "+ attrObj.val);
            parent.setSplitAttr(attrObj.attr);
            parent.setSplitVal(attrObj.val);

            // Step B
            // Split data of parent for its two children
            // Set up children and do recursive call
            List<List<Double>>[] childData;
            childData = splitData(parent.getData(), attrObj.attr, attrObj.val);
            
            if (0 != childData[0].size()) {
                parent.setLeftChild(new TreeNode(childData[0]));
                recursiveSplit(parent.getLeftChild(), attr);
            }
            if (0 != childData[1].size()) {
                parent.setRightChild(new TreeNode(childData[1]));
                recursiveSplit(parent.getRightChild(), attr);
            }

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
    private void findSplitPosition(List<List<Double>> data, List<Integer> attr, SplitAttrObj attrObj) {
        double minEntropy = Double.MAX_VALUE;
        for (int at : attr) {
            for (int i = 0, len = data.size(); i < len; i++) {
                double ent = checkPosition(data, at, data.get(i).get(at));
                System.out.println("ent: "+ent);
                if (ent < minEntropy) {
                    minEntropy = ent;
                    attrObj.attr = at;
                    attrObj.val = data.get(i).get(at);
                }
            }
        }
//        System.out.println("[Find Pos] Min Attr: "+minAt+" Min Val: "+ minAtVal);
    }

    private double checkPosition(List<List<Double>> data, int attr, double val) {
        List<List<Double>>[] childData = splitData(data, attr, val);
        List<Double> pl = getClassProbs(childData[0]);
        List<Double> pu = getClassProbs(childData[1]);
        double el = calcEntropy(pl);
        double eu = calcEntropy(pu);
        
        double e = (el*childData[0].size() + eu*childData[1].size())/(double)data.size();
        
        return e;
    }
    
    private List<List<Double>>[] splitData(List<List<Double>> data, int minAt, double minAtVal) {
        @SuppressWarnings("unchecked")
        List<List<Double>>[] childData = (List<List<Double>>[]) new List[2];
        childData[0] = new ArrayList<List<Double>>();
        childData[1] = new ArrayList<List<Double>>();
        for (List<Double> record : data) {
            if (record.get(minAt) < minAtVal) {
                childData[0].add(record);
            }
            else {
                childData[1].add(record);
            }
        }
        return childData;
    }
    
    
    private List<Double> getClassProbs(List<List<Double>> data) {
        HashMap<Double, Integer> counts = new HashMap<Double, Integer>();
        for (List<Double> record : data) {
            double clas = record.get(0);
            if (counts.containsKey(clas)) {
                counts.put(clas, counts.get(clas)+1);
            }
            else {
                counts.put(clas, 1);
            }
        }

        double N = data.size();
        List<Double> ps = new ArrayList<Double>();
        for (double key : counts.keySet()) {
            ps.add(counts.get(key)/N);
        }
        
        return ps;
    }

    private double logOfTwo = Math.log(2);
    
    
    /**
     * Given a probability mass function indicating the frequencies of 
     * class representation, calculate an "entropy" value using the method
     * in Tan Steinbach Kumar's "Data Mining" textbook
     */
    private double calcEntropy(List<Double> ps) {
        double e = 0;
        for (double p : ps) {
            if (p != 0) {
                e += p*Math.log(p)/logOfTwo;
            }
        }
        return -e;
    }

    @objid ("943639d0-f911-4e72-b5b3-3087f8f11863")
    public double classify(final List<Double> testData) {
        // TODO Auto-generated return
        if (null == rootNode) {
            System.out.println("Tree not created yet...");
            return -1;
        }
        
        TreeNode evalNode = rootNode;
        while (true) {
            if (evalNode.isLeaf()) {
                return evalNode.getClassVal();
            }
            else {
                int splitAttr = evalNode.getSplitAttr();
                double splitVal = evalNode.getSplitVal();
                if (testData.get(splitAttr) < splitVal) {
                    evalNode = evalNode.getLeftChild();
                }
                else {
                    evalNode = evalNode.getRightChild();
                }
            }
        }
    }
    
    public TreeNode getRootNode() {
        return rootNode;
    }

    @objid ("76d43c96-c471-4b7d-a417-d0a16d9c295c")
    public String saveToString() {
        // TODO Auto-generated return
        return null;
    }

    @objid ("359fef83-1f91-4226-b4e6-33118900cb50")
    public void loadFromString(final String tree) {
        // TODO
    }
    
    private class SplitAttrObj {
        public int attr;
        public double val;
        SplitAttrObj() {
            attr = -1;
            val = -1;
        }
    }

}
