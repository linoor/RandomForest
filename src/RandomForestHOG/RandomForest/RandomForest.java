package RandomForestHOG.RandomForest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import RandomForestHOG.DecisionTree.DecisionTree;
import Utils.DataVector;
import Utils.Helper;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.classifier.Classifier;
import fr.ensmp.caor.levis.sample.Sample;

@objid ("a97a3fa3-3a4e-46a1-b40f-98098d41b00f")
public class RandomForest extends Classifier {

    /* if not specified, maxDepth = -1 */
    private static int maxDepth = -1;
    private static int maxNumOfTrees = 100;

    public List<DecisionTree> dTree;
    public List<Integer> finalPredictions;
    public List<Integer> correctPredictions;

    public RandomForest() {
        super();
        dTree = new ArrayList<DecisionTree>();
        finalPredictions = new ArrayList<Integer>();
        correctPredictions = new ArrayList<Integer>();
    }

    @objid ("a775e74a-83d3-42c8-b3d9-6336733ea164")
    public RandomForest(final int maxDepth, final int maxNumOfTrees) {
        this();
        RandomForest.maxDepth = maxDepth;
        RandomForest.maxNumOfTrees = maxNumOfTrees;
    }

    @objid ("825cee3b-bf63-4b2f-96a7-c6fe53cf1387")
    public int classify(Sample p0) {
        return 0;
    }

    public List<Integer> classify(List<DataVector> data, boolean calAccuracy) {
//        List<Integer> prediction = new ArrayList<>(data.size());
        for (DataVector vector : data) {
            List<Integer> treePredictions = new ArrayList<>(data.size());
            for (DecisionTree tree : dTree) {
                int curPrediction = tree.classify(vector);
                treePredictions.add(curPrediction);
            }
            int forestPrediction = Helper.getModeInt(treePredictions);
            finalPredictions.add(forestPrediction);
            correctPredictions.add(vector.cls);
        }
        if (calAccuracy) {
            calAccuracy();
        }
        return finalPredictions;
    }

    public static void setMaxDepth(int maxDepth) {
        RandomForest.maxDepth = maxDepth;
    }

    public static void setMaxNumOfTrees(int maxNumOfTrees) {
        RandomForest.maxNumOfTrees = maxNumOfTrees;
    }

    private void calAccuracy() {
        int err = 0;
        for (int i = 0, len = finalPredictions.size(); i < len; i++) {
            if (finalPredictions.get(i) != correctPredictions.get(i)) {
                err++;
                System.out.print(correctPredictions.get(i));
                System.out.print(" => ");
                System.out.println(finalPredictions.get(i));
            }
            else {
                System.out.println(correctPredictions.get(i));
            }
        }
        System.out.print("Errors: ");
        System.out.println(err);
        System.out.print("Error rate: ");
        System.out.println((double) err / correctPredictions.size());
    }

    @objid ("cbe45e05-de9e-49f4-b0cb-481904bc80f9")
    public void write(BufferedWriter p0, boolean p1) throws IOException {
    }

    @objid ("f96d5246-c8ac-49c9-a26f-6a2b8de8cc38")
    public void read(BufferedReader p0) throws Exception {
    }

    @objid("8883cce6-777e-4b9c-8805-76062396071d")
    public int getMaxDepth() {
        return maxDepth;
    }

    @objid("cef76905-bdae-42d0-827b-37e6adbe4154")
    public int getMaxNumOfTrees() {
        return maxNumOfTrees;
    }

    @objid("28c4ffde-4f11-49c5-804f-9a61039cded6")
    public List<DecisionTree> getTrees() {
        return dTree;
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
