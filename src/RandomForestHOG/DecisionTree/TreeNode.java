package RandomForestHOG.DecisionTree;

import Utils.DataVector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import java.util.List;

@objid ("67321fdf-07c5-4b25-973e-2c0c213fa851")
public class TreeNode implements Cloneable {
    private int level;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;

    private int splitAttr;
    private double splitVal;
    private double classVal;
    private List<DataVector> data;

    @objid ("51f070ca-6fe8-4d4e-868c-c62c2b31c082")
    public TreeNode() {
        this(null, 0, -99, -99);
    }
    
    public TreeNode(List<DataVector> data) {
        this(data, 0, -99, -99);
    }
    
    public TreeNode(List<DataVector> data, int level, int splitAttr, double splitVal) {
        setData(data);
        setLevel(level);
        setSplitAttr(splitAttr);
        setSplitVal(splitVal);
        setClassVal(-1);
    }

    public TreeNode clone() {
        TreeNode copy = new TreeNode();
        copy.setSplitAttr(this.getSplitAttr());
        copy.setSplitVal(this.getSplitVal());
        copy.setClassVal(this.getClassVal());
        copy.setLevel(this.getLevel());
        copy.setLeftChild(this.getLeftChild());
        copy.setRightChild(this.getRightChild());
        copy.setParent(this.getParent());
        return copy;
    }
    
    @Override
    public String toString() {
        return getLevel() + ": attr[" + getSplitAttr() + "," + getSplitVal() 
                         + "] class["+ getClassVal() + "]";
    }

    private void incrementLevel() {
        level++;
    }

    private void setupChild(TreeNode child) {
        if (null == child) {
            return;
        }
        child.setParent(this);
        child.setLevel(getLevel());
        child.incrementLevel();
    }

    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    /**
     * Check if all record of data of this tree node have the same class value.
     * @return that class value, -1 if not
     */
    public double checkIfSameClass() {
        // get class of the first record of data
        // (suppose class is the first element of that record)
        double curClass = data.get(0).cls;
        for (DataVector record : data) {
            if (curClass != record.cls) {
                return -1;
            }
        }
        return curClass;
    }

    @objid("eba4fa88-e515-4ffe-92c8-c4719ccedcf3")
    public int getLevel() {
        return level;
    }

    @objid("d790bcf1-f79e-4f49-be58-cb0de2cd71fe")
    public TreeNode getLeftChild() {
        return leftChild;
    }

    @objid("f49884cb-a796-455c-89d2-3049637325cd")
    public TreeNode getRightChild() {
        return rightChild;
    }

    @objid("f30ffbce-5ef9-47c6-9c47-d8546919c4db")
    public TreeNode getParent() {
        return parent;
    }

    public int getSplitAttr() {
        return splitAttr;
    }

    public double getSplitVal() {
        return splitVal;
    }

    public double getClassVal() {
        return classVal;
    }

    public List<DataVector> getData() {
        return data;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
        setupChild(leftChild);
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
        setupChild(rightChild);
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setSplitAttr(int splitAttr) {
        this.splitAttr = splitAttr;
    }

    public void setSplitVal(double splitVal) {
        this.splitVal = splitVal;
    }

    public void setClassVal(double classVal) {
        this.classVal = classVal;
    }

    public void setData(List<DataVector> data) {
        this.data = data;
    }
}
