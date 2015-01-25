package RandomForestHOG.RandomForest;

import RandomForestHOG.DecisionTree.DecisionTree;
import RandomForestHOG.NotifyingThread.NotifyingThread;
import RandomForestHOG.NotifyingThread.ThreadCompleteListener;
import Utils.DataVector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.classifier.Classifier;
import fr.ensmp.caor.levis.learner.Learner;
import fr.ensmp.caor.levis.sample.DataBase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@objid("f13ea57b-2648-48bf-8e5e-b1319a05eaba")
public class RandomForestLearner extends Learner implements ThreadCompleteListener {

    private static double bootstrapRate = 2.0 / 3;
    private static double attrSampleRate = 1;
    private static int numOfClass;
    private static int numOfAttr;
    /* number of attributes to be bootstrapped when creating decision trees
     * suggested by Breiman: = (int)Math.round(Math.log([# of attr])/Math.log(2)+1)
     */
    private static int numOfAttrSample;
    private static int numOfTree;
    private static int depthOfTree;
    private Classifier _model;
    private List<DataVector> data;
    private List<DataVector> testData;

    /* the thread pool that generates decision tree concurrently */
    private ExecutorService treePool;
    private long startTime;
    private int finishedCount;

    @objid("1eaa0854-8e11-4e6b-8a90-5a8d3e57821e")
    public RandomForestLearner(List<DataVector> data, int numOfTree, int depthOfTree) {
        super();
        if (0 >= data.size()) {
            System.err.println("RandomForestLearner: data empty...");
            return;
        }

        this.data = data;
        this.numOfTree = numOfTree;
        this.depthOfTree = depthOfTree;
        _model = new RandomForest(depthOfTree, numOfTree);

        this.numOfAttr = data.get(0).feature.length;
        this.numOfAttrSample = (int) Math.round(Math.log(this.numOfAttr) / Math.log(2) + 1);
        this.attrSampleRate = ((double) this.numOfAttrSample) / this.numOfAttr;
    }


    public RandomForestLearner(List<DataVector> data, int numOfTree, int depthOfTree, double attrSampleRate) {
        this(data, numOfTree, depthOfTree);
        if (1 < attrSampleRate) {
            System.out.println("RandomForestLearner: attribute sample rate > 1... using default setting");
        } else {
            this.attrSampleRate = attrSampleRate;
            this.numOfAttrSample = (int) Math.round(numOfAttr * attrSampleRate);
        }
    }

    public RandomForestLearner(List<DataVector> data, int numOfTree, int depthOfTree, double attrSampleRate, double bootstrapRate) {
        this(data, numOfTree, depthOfTree, attrSampleRate);
        if (1 < bootstrapRate) {
            System.out.println("RandomForestLearner: data bootstrap rate > 1... using default setting");
        } else {
            this.bootstrapRate = bootstrapRate;
        }
    }

    private static String TimeElapsed(long timeinms) {
        double s = (double) (System.currentTimeMillis() - timeinms) / 1000;
        int h = (int) Math.floor(s / ((double) 3600));
        s -= (h * 3600);
        int m = (int) Math.floor(s / ((double) 60));
        s -= (m * 60);
        return "" + h + "hr " + m + "m " + s + "sec";
    }

    public Classifier learn(boolean threadMode) {
        System.out.println("Start learning...");
        startTime = System.currentTimeMillis();

        RandomForest model = (RandomForest) _model;
        if (threadMode) {
            treePool = Executors.newFixedThreadPool(numOfTree);
            for (int i = 0; i < numOfTree; i++) {
                NotifyingThread createTree = new CreateTree(data, model, i);
                createTree.addListener(this);
                createTree.setThreadId(i);
                treePool.execute(createTree);
            }
            treePool.shutdown();
            try {
                while (!treePool.awaitTermination(10, TimeUnit.SECONDS)) ;
            } catch (InterruptedException ie) {
                System.out.println("interrupted exception in RandomForestLearner...");
            } finally {
                System.out.println("All threads finished...");
                model.classify(testData, true);
            }
        } else {
            for (int i = 0; i < numOfTree; i++) {
                CreateTree create = new CreateTree(data, model, i);
                create.run();
            }
        }

        System.out.println("Learning done in " + TimeElapsed(startTime));

        return model;
    }

    public void setTestData(List<DataVector> testData) {
        this.testData = testData;
    }

    @Override
    public void notifyOfThreadComplete(Runnable thread) {
        NotifyingThread finished = (NotifyingThread) thread;
        System.out.println("Thread " + finished.getThreadId() + " finished...");
        finishedCount++;
    }

    @objid("eafec0c5-2d8e-48a2-b23c-cb59b300755a")
    public double getAttrSampleRate() {
        return attrSampleRate;
    }

    @objid("462886f4-e68d-48c1-b2dd-1b27d8dc39a2")
    public double getBootstrapRate() {
        return bootstrapRate;
    }

    @objid("84799113-d630-4eef-bd4d-a074581d5b7f")
    protected Classifier learn(DataBase p0) throws Exception {
        return null;
    }

    @Override
    protected String[] getLearnerParameters() {
        return new String[0];
    }

    @Override
    protected Object getLearnerParameter(String s) {
        return null;
    }

    @Override
    protected String getLearnerParameterDescription(String s) {
        return null;
    }

    @Override
    protected void setLearnerParameter(String s, Object o) throws Exception {

    }

    @Override
    protected Object[] getLearnerPossibleValues(String s) {
        return new Object[0];
    }

    private class CreateTree extends NotifyingThread {
        private List<DataVector> data;
        private RandomForest forest;
        private int treeId;

        public CreateTree(List<DataVector> data, RandomForest forest, int treeId) {
            this.data = data;
            this.forest = forest;
            this.treeId = treeId;
        }

        @Override
        public void doRun() {
            DecisionTree tree = new DecisionTree(data, bootstrapRate, numOfAttrSample, forest.getMaxDepth(), treeId);
            tree.createTree();
            forest.dTree.add(tree);
        }
    }

}
