package RandomForestHOG.RandomForest;

import RandomForestHOG.HOG.HOGAppli;
import RandomForestHOG.HOG.HOGParam;
import Utils.DataVector;
import Utils.ExtDataParser;
import Utils.Helper;

import java.io.File;
import java.util.List;

import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;

/**
 * Created by ericchiu on 1/24/15.
 */
public class MainRun {

    private static boolean runWithThread = true;
    private static int numOfTree = 100;
    private static int depthOfTree = 10;

    public static void main(String[] args) {
        runHOGDB();
//        runHOGSmallPatch();
//        runTestPendigits();
    }

    public static void runHOGSmallPatch() {
        HOGAppli hogAppli, hogAppliTest;
        File[] files = new File(Helper.getAssetsFolderStr() + "/Test/small_patch/train").listFiles();
        File[] testFiles = new File(Helper.getAssetsFolderStr() + "/Test/small_patch/test").listFiles();

        try {
            hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));
            hogAppliTest = new HOGAppli(testFiles, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));

            runRandomForest(hogAppli.getDataVectors(), hogAppliTest.getDataVectors());

        } catch (Exception e) {
            System.err.print("\n\tError in testCreation()\n");
            e.printStackTrace();
        }
    }

    public static void runHOGDB() {
        HOGAppli hogAppli, hogAppliTest;
        File[] files = new File(Helper.getAssetsFolderStr() + "/DB").listFiles();
        File[] testFiles = new File(Helper.getAssetsFolderStr() + "/Test/testset").listFiles();

        try {
            hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));
            hogAppliTest = new HOGAppli(testFiles, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));

            runRandomForest(hogAppli.getDataVectors(), hogAppliTest.getDataVectors());

        } catch (Exception e) {
            System.err.print("\n\tError in testCreation()\n");
            e.printStackTrace();
        }
    }

    public static void runTestPendigits() {
        String folderPath = Helper.getAssetsFolderStr() + "/external/pendigits/";
        List<DataVector> train = ExtDataParser.parsePendigits(folderPath + "pendigits.tra", 0);
        List<DataVector> test = ExtDataParser.parsePendigits(folderPath + "pendigits.tes", 1);

        runRandomForest(train, test);
    }

    private static void runRandomForest(List<DataVector> trainSet, List<DataVector> testSet) {
        RandomForestLearner rfLearner = new RandomForestLearner(trainSet, 100, 10);
        rfLearner.setTestData(testSet);
        RandomForest rfModel = (RandomForest) rfLearner.learn(true);
    }
}
