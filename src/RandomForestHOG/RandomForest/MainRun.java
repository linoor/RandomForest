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
    public static void main(String[] args) {
        runTestPendigits();
    }

    public static void runHOG() {
        HOGAppli hogAppli;
        File[] files = new File(Helper.getAssetsFolderStr() + "/Test/small_patch").listFiles();
        try {
            hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR, 9, 4, 4, 2, 2, 1, 10, 10));

            Helper.printData(hogAppli.getDataVectors());
            RandomForestLearner rfLearner = new RandomForestLearner(hogAppli.getDataVectors(), 100, 2);
//            rfLearner.learn();

        } catch (Exception e) {
            System.err.print("\n\tError in testCreation()\n");
            e.printStackTrace();
        }
    }

    public static void runTestPendigits() {
        String folderPath = Helper.getAssetsFolderStr() + "/external/pendigits/";
        List<DataVector> train = ExtDataParser.parsePendigits(folderPath + "pendigits.tra", 0);
        List<DataVector> test = ExtDataParser.parsePendigits(folderPath + "pendigits.tes", 1);
        Helper.printData(train);
        Helper.printData(test);
    }
}
