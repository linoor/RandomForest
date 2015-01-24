package DecisionTree;

import RandomForestHOG.DecisionTree.DecisionTree;
import RandomForestHOG.DecisionTree.TreeNode;

import Utils.DataVector;
import Utils.Helper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DecisionTreeTests {

    private DecisionTree tree;
    private List<DataVector> testingData;

    int dataSize = 20;
    int numOfAttr = 20;
    int numOfClass = 10;
    private double bootstrapRate = 2.0/3;

    @Before
    public void setup() {
        testingData = Helper.setupTestingData(dataSize, numOfAttr, numOfClass);
        int numOfAttrSample = getNumOfAttrSample(testingData);
        if (0 > numOfAttrSample) {
            Assert.fail();
        }
        else {
            tree = new DecisionTree(testingData, bootstrapRate, numOfAttrSample, 0);
        }
    }

    @Test
    public void testConstructor() {
//        int dataSize = 20;
//        int numOfAttr = 20;
//        int numOfClass = 10;
//        testingData = setupTestingData(dataSize, numOfAttr, numOfClass);
//        int numOfAttrSample = getNumOfAttrSample(testingData);
//        DecisionTree testTree = new DecisionTree(testingData, bootstrapRate, numOfAttrSample, 0);

        Assert.assertEquals(dataSize, tree.getDataN());
        Assert.assertEquals((int) Math.round(dataSize * bootstrapRate), tree.getTrainN());
        Assert.assertEquals(dataSize - (int) Math.round(dataSize * bootstrapRate), tree.getTestN());
        Assert.assertEquals(numOfAttr, tree.getAttrN());
        Assert.assertEquals(getNumOfAttrSample(testingData), tree.getAttrSampleN());
    }

    
    @Test
    public void testClassify() {
        DataVector testRecord = Helper.setupTestingData(1, 10, 5).get(0);
        double result = tree.classify(testRecord);
        System.out.println("Test Result: " + result);
    }

    @Test
    public void printCreatedTree() {
        TreeNode root = tree.getRootNode();
        System.out.println(root.getLevel()+":"+root.getSplitAttr()+"("+root.getSplitVal()+")");
        final int PRINT_LVL_MAX = 5;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (null != q.peek()) {
            TreeNode node = q.poll();
            System.out.println("Level " + node.getLevel() + " ---------------------");
            System.out.println(node);
            Helper.printData(node.getData());

            if (PRINT_LVL_MAX < node.getLevel()) {
                break;
            }

            TreeNode left = node.getLeftChild();
            TreeNode right = node.getRightChild();
            if (null != left) {
                q.add(left);
            }
            if (null != right) {
                q.add(right);
            }
        }
    }

    private int getNumOfAttrSample(List<DataVector> data) {
        try {
            int numOfAttr = data.get(0).feature.length;
            return (int)Math.round(Math.log(numOfAttr)/Math.log(2)+1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}