import org.junit.Test;

/**
 * Created by Linoor on 2014-11-07.
 */
public class TreeNodeTests {

    @Test
    public void testIsLeaf() {
        TreeNode parent;
        parent = new TreeNode();
        parent.setLeftChild(new TreeNode());
        parent.setRightChild(new TreeNode());

        org.junit.Assert.assertFalse("parent should not be a leaf", parent.isLeaf());
        org.junit.Assert.assertTrue("left child should be a leaf", parent.getLeftChild().isLeaf());
        org.junit.Assert.assertTrue("right child should be a leaf", parent.getRightChild().isLeaf());
    }

    @Test
    public void testLevel() {
        TreeNode parent;
        parent = new TreeNode();
        parent.setLeftChild(new TreeNode());
        parent.setRightChild(new TreeNode());

        org.junit.Assert.assertSame("The level of the parent should be equal to 0", 0, parent.getLevel());
        org.junit.Assert.assertSame("The level of the left child should be equal to 1", 1, parent.getLevel());
        org.junit.Assert.assertSame("The level of the right child should be equal to 1", 1, parent.getLevel());
    }
}
