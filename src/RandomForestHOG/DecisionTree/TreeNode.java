package RandomForestHOG.DecisionTree;

//import com.modeliosoft.modelio.javadesigner.annotations.objid;

import java.util.ArrayList;

////@objid ("67321fdf-07c5-4b25-973e-2c0c213fa851")
public class TreeNode implements Cloneable {
	private int level;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;
    private ArrayList<Double> data;

    public boolean isLeaf;
    public int splitAttr;
    public double splitVal;
    
//    //@objid ("51f070ca-6fe8-4d4e-868c-c62c2b31c082")
    public TreeNode() {
    	isLeaf = false;
        level = 0;
        splitAttr = -99;
        splitVal = -99;
    }
    
    public TreeNode clone() {
    	TreeNode copy = new TreeNode();
    	copy.isLeaf = isLeaf;
    	copy.splitAttr = splitAttr;
    	copy.splitVal = splitVal;
    	copy.setLevel(level);
    	copy.setLeftChild(leftChild);
    	copy.setRightChild(rightChild);
    	copy.setParent(parent);
    	return copy;
    }
    
    public boolean isLeaf() {
    	return isLeaf;
    }

  //@objid("eba4fa88-e515-4ffe-92c8-c4719ccedcf3")
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int lvl) {
    	level = lvl;
    }

    //@objid("d790bcf1-f79e-4f49-be58-cb0de2cd71fe")
    public TreeNode getLeftChild() {
        return leftChild;
    }

    //@objid("f49884cb-a796-455c-89d2-3049637325cd")
    public TreeNode getRightChild() {
        return rightChild;
    }

    //@objid("f30ffbce-5ef9-47c6-9c47-d8546919c4db")
    public TreeNode getParent() {
        return parent;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
        setupChild(leftChild);
    }

    private void incrementLevel() {
        level++;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
        setupChild(rightChild);
    }

    private void setupChild(TreeNode rightChild) {
        rightChild.setParent(this);
        rightChild.incrementLevel();
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
