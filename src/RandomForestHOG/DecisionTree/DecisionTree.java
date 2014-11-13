package RandomForestHOG.DecisionTree;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("61261918-d6ad-4d4d-a19f-e6c7088f5dd6")
public @interface DecisionTree  {
    @objid ("ebb8f3f9-dd89-4d5b-b771-a57032b21977")
    int dataN();

    @objid ("0e6e5a8a-cb9a-426f-99dd-bbc8cd785220")
    int trainN();

    @objid ("c8d51bcf-a123-439a-a3af-27758281545f")
    int attrN();

    @objid ("bd717090-214d-4987-9ab8-8616e6eb28ed")
    TreeNode rootNode();

    @objid ("92a699ab-b86a-40cc-8fdb-4eca568fa8a6")
    public DecisionTree() {
    }

    @objid ("95f01270-0b39-4c6b-bbf3-fb177f21545e")
    public DecisionTree(final List<Float> data) {
    }

    @objid ("bd35c418-14d7-4599-891b-34837487a39c")
    private void bootStrapSample(final List<Float> data, List<Float> train, List<Float> test) {
    }

    @objid ("22963c8e-9140-49f2-beb7-3b2458a06c51")
    private List<Integer> bootStrapAttr() {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("11f42db2-137b-4fd3-8d5c-065ee3ecdf65")
    private void createTree(final List<Float> train, final int nTree) {
    }

    @objid ("008d3f40-e60d-4c6e-9eb2-7018b83bf180")
    private void recursiveSplit(final TreeNode parent) {
    }

    @objid ("943639d0-f911-4e72-b5b3-3087f8f11863")
    public List<Integer> classify(final List<Float> testData) {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("76d43c96-c471-4b7d-a417-d0a16d9c295c")
    public String saveToString() {
        // TODO Auto-generated return
        return null;
    }

    @objid ("359fef83-1f91-4226-b4e6-33118900cb50")
    public void loadFromString(final String tree) {
    }

}
