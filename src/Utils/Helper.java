package Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Linoor on 2014-11-29.
 */
public class Helper {

    public static Path getAssetsFolder() {
        return Paths.get("assets");
    }

    public static String getAssetsFolderStr() {
        return getAssetsFolder().toAbsolutePath().toString();
    }

    public static List<DataVector> setupTestingData(int dataSize, int attrSize, int classSize) {
        List<DataVector> data = new ArrayList<DataVector>();

        for (int i = 0; i < dataSize; i++) {
            int cls = (int) Math.floor(Math.random()*classSize);
            double[] feature = new double[attrSize];
            for (int j = 0; j < attrSize; j++) {
                feature[j] = (Math.floor(Math.random()*100));
            }
            DataVector record = new DataVector(cls, feature);
            data.add(record);
        }
        System.out.println("Test Data-------------------------");
        Helper.printData(data);
        System.out.println("Test Data end---------------------");

        return data;
    }

    public static void printData(List<DataVector> data) {
        for (int i = 0, len = data.size(); i < len; i++) {
            System.out.print(data.get(i).cls);
            System.out.print(" : ");
            for (int j = 0, len2 = data.get(i).feature.length; j < len2; j++) {
                System.out.print(data.get(i).feature[j]);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    public static int getModeInt(List<Integer> list) {
        if (0 >= list.size()) {
            return Integer.MIN_VALUE;
        }

        Collections.sort(list);

        int[] counter = new int[list.get(list.size()-1)+1];
        int maxCount = -1;
        int maxIdx = -1;
        for (int el : list) {
            counter[el]++;
            if (counter[el] > maxCount) {
                maxCount = counter[el];
                maxIdx = el;
            }
        }
        return maxIdx;
    }
}
