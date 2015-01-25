package RandomForestHOG.RandomForest;

import RandomForestHOG.HOG.HOGAppli;
import RandomForestHOG.HOG.HOGParam;
import Utils.DataVector;
import Utils.ExtDataParser;
import Utils.Helper;
import fr.ensmp.caor.levis.classifier.Classifier;

import java.io.File;
import java.util.List;

import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;

/**
 * Created by ericchiu on 1/24/15.
 */
public class MainRun {
    public static void main(String[] args) {
        runHOGDB();
//        runHOGSmallPatch();
//        runTestPendigits();
    }

    public static void runHOGSmallPatch() {
        HOGAppli hogAppli, hogAppliTest;
        File[] files = new File(Helper.getAssetsFolderStr() + "/Test/small_patch/train").listFiles();
        File[] testFiles = new File(Helper.getAssetsFolderStr() + "/Test/small_patch/test").listFiles();
        for (File f : testFiles) {
            System.out.println(f.getName());
        }
        try {
            hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));
            hogAppliTest = new HOGAppli(testFiles, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));

//            Helper.printData(hogAppli.getDataVectors());
//            Helper.printData(hogAppliTest.getDataVectors());

            RandomForestLearner rfLearner = new RandomForestLearner(hogAppli.getDataVectors(), 10, 10);
            rfLearner.setTestData(hogAppliTest.getDataVectors());
            RandomForest rfModel = (RandomForest) rfLearner.learn(true);

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

            RandomForestLearner rfLearner = new RandomForestLearner(hogAppli.getDataVectors(), 100, 10);
            rfLearner.setTestData(hogAppliTest.getDataVectors());
            RandomForest rfModel = (RandomForest) rfLearner.learn(true);

        } catch (Exception e) {
            System.err.print("\n\tError in testCreation()\n");
            e.printStackTrace();
        }
    }

    public static void runTestPendigits() {
        String folderPath = Helper.getAssetsFolderStr() + "/external/pendigits/";
        List<DataVector> train = ExtDataParser.parsePendigits(folderPath + "pendigits.tra", 0);
        List<DataVector> test = ExtDataParser.parsePendigits(folderPath + "pendigits.tes", 1);
//        Helper.printData(train);
//        Helper.printData(test);

        RandomForestLearner rfLearner = new RandomForestLearner(train, 10, 10);
        rfLearner.setTestData(test);
        RandomForest rfModel = (RandomForest) rfLearner.learn(true);

//        List<Integer> predictions = rfMode.classify(test, true);
//        System.out.println(predictions);
    }
}
