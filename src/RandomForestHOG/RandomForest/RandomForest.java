package RandomForestHOG.RandomForest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import RandomForestHOG.DecisionTree.DecisionTree;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.classifier.Classifier;
import fr.ensmp.caor.levis.sample.Sample;

@objid ("a97a3fa3-3a4e-46a1-b40f-98098d41b00f")
public class RandomForest extends Classifier {
    @objid("8883cce6-777e-4b9c-8805-76062396071d")
    int _maxDepth() {
        return 0;
    }

    @objid("cef76905-bdae-42d0-827b-37e6adbe4154")
    int _maxNumOfTrees() {
        return 0;
    }

    @objid("b5a6a8ac-1ab9-4f93-b678-95119fd19ca6")
    double _forestError() {
        return 0;
    }

    @objid("28c4ffde-4f11-49c5-804f-9a61039cded6")
    List<DecisionTree> _trees() {
        return null;
    }

    @objid ("a775e74a-83d3-42c8-b3d9-6336733ea164")
    public RandomForest(final Integer maxDepth, final Integer maxNumOfTrees) {
    }

    @objid ("cbe45e05-de9e-49f4-b0cb-481904bc80f9")
    public void write(BufferedWriter p0, boolean p1) throws IOException {
    }

    @objid ("f96d5246-c8ac-49c9-a26f-6a2b8de8cc38")
    public void read(BufferedReader p0) throws Exception {
    }

    @objid ("825cee3b-bf63-4b2f-96a7-c6fe53cf1387")
    public int classify(Sample p0) {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("7d351bea-7309-41e8-8d48-de66b414bc25")
    public int voteOfDTree(final List<Integer> predictions) {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("4e02065e-bd36-4784-a14a-e65f379b4cbd")
    public void save() {
    }

    @objid ("f6bfeaa8-ce9f-4055-85d5-142cad082fa0")
    public void load(final String filename) {
    }

    @objid ("b9f976bc-034c-42e9-84d5-f7e6eba0502a")
    public void createForest(final List<Float> data) {
    }

    @Override
    public String[] getParameters() {
        return new String[0];
    }

    @Override
    public Object getParameter(String s) {
        return null;
    }

    @Override
    public void setParameter(String s, Object o) throws Exception {

    }

    @Override
    public String getParameterDescription(String s) {
        return null;
    }

    @Override
    public Object[] getPossibleValues(String s) {
        return new Object[0];
    }
}
