package RandomForestHOG.RandomForest;

import RandomForestHOG.HOG.HOGAppli;
import RandomForestHOG.HOG.HOGParam;
import Utils.Helper;

import java.io.File;

import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;

/**
 * Created by ericchiu on 1/24/15.
 */
public class MainRun {
    public static void main(String[] args) {

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
}
