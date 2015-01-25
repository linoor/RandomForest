package DecisionTree;

import RandomForestHOG.DecisionTree.TreeNode;
import org.junit.Before;
import org.junit.Test;

public class TreeNodeTests {

    private TreeNode parent;
    private TreeNode rightChild;
    private TreeNode leftChild;

    @Before
    public void setup() {
        parent = new TreeNode();
        leftChild = new TreeNode();
        parent.setLeftChild(leftChild);
        rightChild = new TreeNode();
        parent.setRightChild(rightChild);
    }

    @Test
    public void testAddingChildren() {
        org.junit.Assert.assertNotNull("left child should not be null", parent.getLeftChild());
        org.junit.Assert.assertNotNull("right child should not be null", parent.getRightChild());
    }

    @Test
    public void testIsLeaf() {
        org.junit.Assert.assertFalse("parent should not be a leaf", parent.isLeaf());
        org.junit.Assert.assertTrue("left child should be a leaf", parent.getLeftChild().isLeaf());
        org.junit.Assert.assertTrue("right child should be a leaf", parent.getRightChild().isLeaf());
    }

    @Test
    public void testLevel() {
        org.junit.Assert.assertSame("The level of the parent should be equal to 0", 0, parent.getLevel());
        org.junit.Assert.assertSame("The level of the left child should be equal to 1", 1, leftChild.getLevel());
        org.junit.Assert.assertSame("The level of the right child should be equal to 1", 1, rightChild.getLevel());
    }

    @Test
    public void testGetParent() {
        org.junit.Assert.assertSame("left child's parent should be \"parent\"", parent, leftChild.getParent());
        org.junit.Assert.assertSame("right child's parent should be \"parent\"", parent, leftChild.getParent());
    }

    //TODO
    @Test
    public void testSplitAttr() {
        if (-99 != parent.getSplitAttr()) {
            org.junit.Assert.assertTrue("current node should be a leaf", parent.isLeaf());
        } else {
            org.junit.Assert.assertFalse("current node should not be a leaf", parent.isLeaf());
        }
    }

    //TODO
    @Test
    public void testSplitVar() {
        if (-99 != parent.getSplitVal()) {
            org.junit.Assert.assertTrue("current node should be a leaf", parent.isLeaf());
        } else {
            org.junit.Assert.assertFalse("current node should not be a leaf", parent.isLeaf());
        }
    }
}
