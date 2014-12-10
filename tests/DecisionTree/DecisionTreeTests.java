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
        
        System.out.println("Test Data---------------------");
        for (int i = 0; i < dataSize; i++) {
            data.add(new ArrayList<Double>(attrSize+1));
            List<Double> record = data.get(i);
            
            record.add(Math.floor(Math.random()*classSize));
            System.out.print(record.get(record.size()-1) + " : ");
            
            for (int j = 0; j < attrSize; j++) {
                record.add(Math.floor(Math.random()*100));
                System.out.print(record.get(j+1) + ",");
            }
            
            System.out.println();
        }
        System.out.println("Test Data end---------------------");
        
        return data;
    }

    @Before
    public void setup() {
        testingData = setupTestingData(15, 10, 5);
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
            System.out.println(node.getLevel()+":"+node.getSplitAttr()+"("+node.getSplitVal()+")");
            
            if (PRINT_LVL_MAX < node.getLevel()) {
                break;
            }
            
            TreeNode left = node.getLeftChild();
            TreeNode right = node.getLeftChild();
            if (null != left) {
                q.add(left);
            }
            if (null != right) {
                q.add(right);
            }
        }
    }
    
    @Test
    public void testClassify() {
        List<Double> testRecord = setupTestingData(1, 10, 5).get(0);
        double result = tree.classify(testRecord);
        System.out.println("Test Result: " + result);
        return;
    }
}