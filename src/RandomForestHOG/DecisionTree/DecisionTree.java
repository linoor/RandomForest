package RandomForestHOG.DecisionTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Utils.DataVector;
import Utils.Helper;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid("61261918-d6ad-4d4d-a19f-e6c7088f5dd6")
public class DecisionTree {

    private int dataN;
    private int trainN;
    private int testN;
    private int attrN;
    private int attrSampleN;
    private int treeId;
    private int maxDepth;
    private TreeNode rootNode;
    private List<DataVector> train;

    private List<DataVector> test;
    private List<Integer> attrSample;
    private double logOfTwo = Math.log(2);

    @objid("95f01270-0b39-4c6b-bbf3-fb177f21545e")
    public DecisionTree(final List<DataVector> data, final double bootstrapRate, final int attrSampleN, final int maxDepth, final int treeId) {
        this.dataN = data.size();
        if (0 >= dataN) {
            System.out.println("DecisionTree: data empty...");
            return;
        }
        this.trainN = (int) Math.round(bootstrapRate * dataN);
        this.testN = this.dataN - this.trainN;
        this.attrN = data.get(0).feature.length;
        this.attrSampleN = attrSampleN;
        this.treeId = treeId;
        this.maxDepth = maxDepth;

        /* Initialize training, testing data set, attribute samples */
        train = new ArrayList<DataVector>(this.trainN);
        test = new ArrayList<DataVector>(this.testN);
        attrSample = new ArrayList<Integer>(this.attrSampleN);
        bootstrapSample(data);
        bootstrapAttr();
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public int getDataN() {
        return dataN;
    }

    public int getTrainN() {
        return trainN;
    }

    public int getTestN() {
        return testN;
    }

    public int getAttrN() {
        return attrN;
    }

    public int getAttrSampleN() {
        return attrSampleN;
    }

    public int getTreeId() {
        return treeId;
    }

    @objid("76d43c96-c471-4b7d-a417-d0a16d9c295c")
    public String saveToString() {
        // TODO Auto-generated return
        return null;
    }

    @objid("359fef83-1f91-4226-b4e6-33118900cb50")
    public void loadFromString(final String tree) {
        // TODO
    }

    @objid("11f42db2-137b-4fd3-8d5c-065ee3ecdf65")
    public void createTree() {
        rootNode = new TreeNode();
        rootNode.setData(train);
        recursiveSplit(rootNode, attrSample);
    }

    @objid("943639d0-f911-4e72-b5b3-3087f8f11863")
    public int classify(final DataVector testData) {
        if (null == rootNode) {
            System.out.println("Tree not created yet...");
            return -1;
        }

        TreeNode evalNode = rootNode;
        while (true) {
            if (evalNode.isLeaf()) {
                return evalNode.getClassVal();
            } else {
                int splitAttr = evalNode.getSplitAttr();
                double splitVal = evalNode.getSplitVal();
                if (testData.feature[splitAttr] < splitVal) {
                    evalNode = evalNode.getLeftChild();
                } else {
                    evalNode = evalNode.getRightChild();
                }
            }
        }
    }

    @objid("bd35c418-14d7-4599-891b-34837487a39c")
    private void bootstrapSample(final List<DataVector> data) {
        ArrayList<Integer> rand = new ArrayList<Integer>(dataN);
        for (int i = 0; i < dataN; i++) {
            rand.add(i);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < trainN; i++) {
            train.add(data.get(rand.get(i)));
        }
        for (int i = trainN; i < dataN; i++) {
            test.add(data.get(rand.get(i)));
        }
    }

    @objid("22963c8e-9140-49f2-beb7-3b2458a06c51")
    private void bootstrapAttr() {
        ArrayList<Integer> rand = new ArrayList<Integer>(attrN);

        for (int i = 0; i < attrN; i++) {
            rand.add(i);
        }
        Collections.shuffle(rand);
        attrSample = rand.subList(0, attrSampleN);
    }

    @objid("008d3f40-e60d-4c6e-9eb2-7018b83bf180")
    private void recursiveSplit(TreeNode parent, List<Integer> attr) {
//        System.out.println(parent.getLevel());
        int curClass = parent.checkIfSameClass();
        if (-1 == curClass) {

            if (-1 != maxDepth && parent.getLevel() >= maxDepth) {
                parent.setClassVal(parent.voteMajorClass());
                return;
            }

            // Step A
            // find the split attribute and its value based on minimum entropy
//            int minAt = -1;
//            double minAtVal = -1;
            SplitAttrObj attrObj = new SplitAttrObj();

            assert (0 < attr.size());
            assert (attrSampleN == attr.size());
            findSplitPosition(parent.getData(), attr, attrObj);

            System.out.println("Min Attr: " + attrObj.attr + " Min Val: " + attrObj.val);
            parent.setSplitAttr(attrObj.attr);
            parent.setSplitVal(attrObj.val);

            // Step B
            // Split data of parent for its two children
            // Set up children and do recursive call
            List<DataVector>[] childData;
            childData = splitData(parent.getData(), attrObj.attr, attrObj.val);

//            System.out.println("--------------- Child 0 ---------------");
//            Helper.printData(childData[0]);
//            System.out.println("--------------- Child 1 ---------------");
//            Helper.printData(childData[1]);

            if (0 != childData[0].size()) {
                parent.setLeftChild(new TreeNode(childData[0]));
                recursiveSplit(parent.getLeftChild(), attr);
            }
            if (0 != childData[1].size()) {
                parent.setRightChild(new TreeNode(childData[1]));
                recursiveSplit(parent.getRightChild(), attr);
            }
        }
        else {
            parent.setClassVal(curClass);
            return;
        }
    }


    /**
     * Find the splitting attribute and value that create minimum entropy
     *
     * @param data
     * @param attr
     * @param attrObj
     */
    private void findSplitPosition(List<DataVector> data, List<Integer> attr, SplitAttrObj attrObj) {
        double minEntropy = Double.MAX_VALUE;
        for (int at : attr) {
            for (int i = 0, len = data.size(); i < len; i++) {
                double ent = checkPosition(data, at, data.get(i).feature[at]);
//                System.out.println("ent: "+ent);
                if (ent < minEntropy) {
                    minEntropy = ent;
                    attrObj.attr = at;
                    attrObj.val = data.get(i).feature[at];
                }
            }
        }
//        System.out.println("[Find Pos] Min Attr: "+minAt+" Min Val: "+ minAtVal);
    }

    private double checkPosition(List<DataVector> data, int attr, double val) {
        List<DataVector>[] childData = splitData(data, attr, val);
        List<Double> pl = getClassProbs(childData[0]);
        List<Double> pu = getClassProbs(childData[1]);
        double el = calcEntropy(pl);
        double eu = calcEntropy(pu);

        double e = (el * childData[0].size() + eu * childData[1].size()) / (double) data.size();

        return e;
    }

    private List<DataVector>[] splitData(List<DataVector> data, int minAt, double minAtVal) {
        List<DataVector>[] childData = (List<DataVector>[]) new List[2];
        childData[0] = new ArrayList<DataVector>();
        childData[1] = new ArrayList<DataVector>();
        for (DataVector record : data) {
            if (record.feature[minAt] < minAtVal) {
                childData[0].add(record);
            } else {
                childData[1].add(record);
            }
        }
        return childData;
    }

    private List<Double> getClassProbs(List<DataVector> data) {
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (DataVector record : data) {
            int clas = record.cls;
            if (counts.containsKey(clas)) {
                counts.put(clas, counts.get(clas) + 1);
            } else {
                counts.put(clas, 1);
            }
        }

        double N = data.size();
        List<Double> ps = new ArrayList<Double>();
        for (int key : counts.keySet()) {
            ps.add(counts.get(key) / N);
        }

        return ps;
    }

    /**
     * Given a probability mass function indicating the frequencies of
     * class representation, calculate an "entropy" value using the method
     * in Tan Steinbach Kumar's "Data Mining" textbook
     */
    private double calcEntropy(List<Double> ps) {
        double e = 0;
        for (double p : ps) {
            if (p != 0) {
                e += p * Math.log(p) / logOfTwo;
            }
        }
        return -e;
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
