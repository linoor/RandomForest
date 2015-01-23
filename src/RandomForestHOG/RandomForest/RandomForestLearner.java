package RandomForestHOG.RandomForest;

import RandomForestHOG.DecisionTree.DecisionTree;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.classifier.Classifier;
import fr.ensmp.caor.levis.learner.Learner;
import fr.ensmp.caor.levis.sample.DataBase;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Utils.DataVector;

@objid ("f13ea57b-2648-48bf-8e5e-b1319a05eaba")
public class RandomForestLearner extends Learner {

    Classifier _model = new RandomForest(200, 100);

    private int bootstrapSize;
    private int attributesSize;

    private static int numOfClass;
    private static int numOfAttr;
    /* number of attributes to be bootstrapped when creating decision trees
     * suggested by Breiman: = (int)Math.round(Math.log([# of attr])/Math.log(2)+1)
     */
    private static int numOfAttrSample;

    private int numOfTree;
    private int numOfThread;
    private List<List<Double>> data;
    private List<List<Double>> testData;

    /* the thread pool that generates decision tree concurrently */
    private ExecutorService treePool;

//    @objid("89e7d33d-e15d-4fd6-b44f-324059a4e1f8")
//    RandomForest _model() {
//        return null;
//    }

    @objid ("1eaa0854-8e11-4e6b-8a90-5a8d3e57821e")
    public RandomForestLearner(int bootstrapSize, int attributeSize) {
        this.bootstrapSize = bootstrapSize;
        this.attributesSize = attributeSize;
    }

    protected Classifier learn() {
        RandomForest model = (RandomForest) _model;
        treePool = Executors.newFixedThreadPool(numOfThread);
        for (int i = 0; i < numOfTree; i++) {
            treePool.execute(new CreateTree(data, model, i));
        }
        treePool.shutdown();
        try {
            if (!treePool.awaitTermination(10, TimeUnit.SECONDS)) {
                treePool.shutdownNow();
                if (!treePool.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("treePool did not terminate...");
                }
            }
        }
        catch (InterruptedException ie) {
            System.out.println("interrupted exception in RandomForestLearner...");
            treePool.shutdownNow();
        }

        return model;
    }

    @objid ("84799113-d630-4eef-bd4d-a074581d5b7f")
    protected Classifier learn(DataBase p0) throws Exception {
        return null;
    }

    private class CreateTree implements Runnable {
        private List<List<Double>> data;
        private RandomForest forest;
        private int treeId;

        public CreateTree(List<List<Double>> data, RandomForest forest, int treeId) {
            this.data = data;
            this.forest = forest;
            this.treeId = treeId;
        }
        @Override
        public void run() {
            forest.dTree.add(new DecisionTree(data, treeId));
        }
    }

    @objid("eafec0c5-2d8e-48a2-b23c-cb59b300755a")
    public int getAttributesSize() {
        return attributesSize;
    }

    @objid("462886f4-e68d-48c1-b2dd-1b27d8dc39a2")
    public int getBootstrapSize() {
        return bootstrapSize;
    }


    @objid ("4aff82c9-54dd-4e50-960f-3fcb7c21264b")
    protected Object getLearnerParameter(String p0) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("5f02503d-159a-4a86-beea-64e6de3555ee")
    protected String getLearnerParameterDescription(String p0) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("09f39372-e4c2-40ef-be1d-63f4a85ee741")
    protected String[] getLearnerParameters() {
        return new String[0];
    }

    @objid ("ed4da577-e9c1-44db-b292-1ddb4aeab813")
    protected Object[] getLearnerPossibleValues(String p0) {
        return new Object[0];
    }

    @objid ("9b2f1c61-6be7-4156-85db-15202c387c02")
    protected void setLearnerParameter(String p0, Object p1) throws Exception {
    }

    @objid ("0b5c008f-76ac-4c0e-b2de-4a29ece8a2f6")
    public Float testAccuracy(DataBase testData) {
        // TODO Auto-generated return
        return 0f;
    }

}
