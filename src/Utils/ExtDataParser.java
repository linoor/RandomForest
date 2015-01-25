package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implement external data set parsers
 */
public class ExtDataParser {

    /**
     * @param filePath path to the parsing file
     * @param type     0: train 1: test
     */
    public static List<DataVector> parsePendigits(String filePath, int type) {
        List<DataVector> dataset = new ArrayList<DataVector>();
        try {
            List<String> lines = readFile(filePath);

            for (String line : lines) {
                String[] tokens = line.split(",");

                int cls = -1;
                double[] feature = new double[tokens.length - 1];
                for (int i = 0, len = tokens.length; i < len; i++) {
                    tokens[i] = tokens[i].trim();
                    if (len - 1 == i) {
                        cls = Integer.parseInt(tokens[i]);
                    } else {
                        feature[i] = Double.parseDouble(tokens[i]);
                    }
                }

                dataset.add(new DataVector(cls, feature));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private static List<String> readFile(String filePath) throws FileNotFoundException {
        List<String> lines = new ArrayList<String>();

        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }

        return lines;
    }
}
