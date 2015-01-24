package DecisionTree;

import RandomForestHOG.DecisionTree.DecisionTree;
import RandomForestHOG.DecisionTree.TreeNode;

import Utils.DataVector;
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

    private double bootstrapRate = 2.0/3;
    
    private List<DataVector> setupTestingData(int dataSize, int attrSize, int classSize) {
        List<DataVector> data = new ArrayList<DataVector>();
        
        for (int i = 0; i < dataSize; i++) {
            int cls = (int) Math.floor(Math.random()*classSize);
            double[] feature = new double[attrSize];
            for (int j = 0; j < attrSize; j++) {
                feature[j] = (Math.floor(Math.random()*100));
            }
            DataVector record = new DataVector(cls, feature);
            data.add(record);
        }
        System.out.println("Test Data-------------------------");
        printData(data);
        System.out.println("Test Data end---------------------");
        
        return data;
    }

    @Before
    public void setup() {
        testingData = setupTestingData(10, 10, 5);
        int numOfAttrSample = getNumOfAttrSample(testingData);
        if (0 > numOfAttrSample) {
            Assert.fail();
        }
        else {
            tree = new DecisionTree(testingData, bootstrapRate, numOfAttrSample, 0);
        }
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
            printData(node.getData());
            
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

    @Test
    public void testConstructor() {

    }
    
    @Test
    public void testClassify() {
        DataVector testRecord = setupTestingData(1, 10, 5).get(0);
        double result = tree.classify(testRecord);
        System.out.println("Test Result: " + result);
        return;
    }

    private void printData(List<DataVector> data) {
        for (int i = 0, len = data.size(); i < len; i++) {
            System.out.print(data.get(i).cls);
            System.out.print(" : ");
            for (int j = 0, len2 = data.get(i).feature.length; j < len2; j++) {
                System.out.print(data.get(i).feature[i]);
                System.out.print(", ");
            }
            System.out.println();
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