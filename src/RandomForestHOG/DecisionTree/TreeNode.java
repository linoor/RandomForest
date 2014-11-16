package RandomForestHOG.DecisionTree;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("67321fdf-07c5-4b25-973e-2c0c213fa851")
public class TreeNode  {
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;

    @objid("2299517a-7564-4108-ab5c-71ec87dc0b77")
    public boolean isLeaf() {
        return false;
    }

    @objid("eba4fa88-e515-4ffe-92c8-c4719ccedcf3")
    public int getLevel() {
        return 0;
    }

    @objid("4f2aede1-a0e6-4376-8945-3f234d7973ed")
    int splitAttr() {
        return 0;
    }

    @objid("17f327ce-60bb-464b-89fd-8117dccb10a2")
    float splitVal() {
        return 0;
    }

    @objid("d790bcf1-f79e-4f49-be58-cb0de2cd71fe")
    TreeNode leftChild() {
        return null;
    }

    @objid("f49884cb-a796-455c-89d2-3049637325cd")
    TreeNode rightChild() {
        return null;
    }

    @objid("f30ffbce-5ef9-47c6-9c47-d8546919c4db")
    TreeNode parent() {
        return null;
    }

    @objid ("51f070ca-6fe8-4d4e-868c-c62c2b31c082")
    public TreeNode() {
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public TreeNode getParent() {
        return parent;
    }
}
