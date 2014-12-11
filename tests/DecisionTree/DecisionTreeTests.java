package DecisionTree;

import RandomForestHOG.DecisionTree.DecisionTree;
import RandomForestHOG.DecisionTree.TreeNode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DecisionTreeTests {
    
    private DecisionTree tree;
    private List<List<Double>> testingData;

    
    private List<List<Double>> setupTestingData(int dataSize, int attrSize, int classSize) {
        List<List<Double>> data = new ArrayList<List<Double>>();
        
        for (int i = 0; i < dataSize; i++) {
            data.add(new ArrayList<Double>(attrSize+1));
            List<Double> record = data.get(i);
            record.add(Math.floor(Math.random()*classSize));
            for (int j = 0; j < attrSize; j++) {
                record.add(Math.floor(Math.random()*100));
            }
        }
        System.out.println("Test Data-------------------------");
        printData(data);
        System.out.println("Test Data end---------------------");
        
        return data;
    }

    @Before
    public void setup() {
        testingData = setupTestingData(10, 10, 5);
        tree = new DecisionTree(testingData, 0);
    }
    
    @Test
    public void printCreatedTree() {
        TreeNode root = tree.getRootNode();
        System.out.println(root.getLevel()+":"+root.getSplitAttr()+"("+root.getSplitVal()+")");
        final int PRINT_LVL_MAX = 5;
//        int counter = 0;
        
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
    
    /*@Test
    public void testClassify() {
        List<Double> testRecord = setupTestingData(1, 10, 5).get(0);
        double result = tree.classify(testRecord);
        System.out.println("Test Result: " + result);
        return;
    }*/
    
    private void printData(List<List<Double>> data) {
        for (int i = 0, len = data.size(); i < len; i++) {
            for (int j = 0, len2 = data.get(i).size(); j < len2; j++) {
                System.out.print(data.get(i).get(j));
                if (0 == j) {
                    System.out.print(" : ");
                } else {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}