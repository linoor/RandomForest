package RandomForestHOG.HOG;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.sample.Sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

@objid("77b78d4d-6afc-4bc3-b438-316a59622fd8")
public class HOG extends Sample {

    private HOGParam hogParam;
    private BufferedImage img;
    private int[][] pixelArray;

    @objid("8c39ea43-b153-44c8-a0de-ff4ed52b57c0")
    public HOG() throws IOException {
        super(null, null, 0);
    }

    @objid("3a55e97d-ae30-433e-b3cd-9ccecda51f65")
    public HOG(final HOGParam initHogParam, File file) throws Exception {
        super(initHogParam.getWidth(), initHogParam.getHeight(), "pre", "C", false, 0);
        setSource(file);
        try {
            this.img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(file.toString());
        }
        this.hogParam = initHogParam;
    }

    public HOG(final HOGParam initHogParam, BufferedImage initImg) throws Exception {
        super(initHogParam.getWidth(), initHogParam.getHeight(), "pre", "C", false, 0);
        this.img = initImg;
        this.hogParam = initHogParam;
    }

    public static double[] intToDouble(int[] vec) {
        double[] result = new double[vec.length];
        for (int i = 0; i < vec.length; i++) {
            result[i] = (double) vec[i];
        }
        return result;
    }

    public static double computeMagnitude(int[] vec) {
        return computeMagnitude(intToDouble(vec));
    }

    public static double computeMagnitude(double[] vec) {
        double sum = DoubleStream.of(vec)
                .map(x -> Math.pow(x, 2))
                .sum();
        return Math.sqrt(sum);
    }

    public static double computeAngle(int[] vec) {
        if (vec[1] == 0) {
            return 0;
        }
        return Math.atan((double) vec[0] / (double) vec[1]);
    }

    public static double[] normalizeVector(double[] doubles) {
        double magnitude = HOG.computeMagnitude(doubles);

        double[] result = new double[doubles.length];

        for (int i = 0; i < doubles.length; i++) {
            result[i] = doubles[i] / magnitude;
        }

        return result;
    }

    public static Double[] toDoubleArray(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Double.valueOf(array[i]);
        }
        return result;
    }

    private static List<Double> concat(List<List<Double>> histograms) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < histograms.size(); i++) {
            result.addAll(histograms.get(i));
        }
        return result;
    }

    private static double[] toPrimitiveDoubleArray(List<Double> result) {
        return result.stream().mapToDouble(i -> i).toArray();
    }

    @objid("03255d6c-46ed-478f-b835-efa132d60bac")
    public double[] getHistogram(int i_start, int j_start, int end_i, int end_j) {
        double[] histogram = new double[getBinNumber()];

        for (int i = i_start; i < end_i; i++) {
            for (int j = j_start; j < end_j; j++) {
                final int[] vec = getGradientVector(i, j);

                final int degreesUpperLimit = 180;

                final double magnitude = HOG.computeMagnitude(vec);
                final double angle = Math.toDegrees(HOG.computeAngle(vec));
                final double step = degreesUpperLimit / getBinNumber();

                final double start = step / 2.0;

                final int rightContributionIndex = (int) (angle / step);
                final int leftContributionIndex = rightContributionIndex == 0 ? 0 : rightContributionIndex - 1;

                final double rightContributionAngle = (rightContributionIndex * step) + start;
                final double leftContributionAngle = rightContributionIndex == 0 ? start : ((rightContributionIndex - 1) * step) + start;

                final double leftContributionPercentage = Math.abs(angle - rightContributionAngle) / step;
                final double rightContributionPercentage = Math.abs(angle - leftContributionAngle) / step;

//              contributing to expected bins
                histogram[leftContributionIndex] += leftContributionPercentage * magnitude;
                histogram[rightContributionIndex] += rightContributionPercentage * magnitude;
            }
        }

        return histogram;
    }

    public HOGParam.BlockType getBlockType() {
        return hogParam.getBlockType();
    }

    public int getBinNumber() {
        return hogParam.getBinNumber();
    }

    public int getCellWidth() {
        return hogParam.getCellWidth();
    }

    public int getCellHeight() {
        return hogParam.getCellHeight();
    }

    public int getBlockWidth() {
        return hogParam.getBlockWidth();
    }

    public int getBlockHeight() {
        return hogParam.getBlockHeight();
    }

    public int getMaskType() {
        return hogParam.getMaskType();
    }

    public int getHeight() {
        return hogParam.getHeight();
    }

    public int getWidth() {
        return hogParam.getWidth();
    }

    public int[][] getPixelArray() {
        if (pixelArray != null) {
            return pixelArray;
        }

        int[][] pixels = new int[getHeight()][getWidth()];

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Color c = new Color(img.getRGB(j, i));
                int gray = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                pixels[i][j] = gray;
            }
        }

        pixelArray = pixels;

        return pixelArray;
    }

    @objid("09490b81-fd8d-4de9-83c8-633b011221dd")
    public int[] getGradientVector(int i, int j) {
        int[][] pixels = getPixelArray();

//        boolean inBounds = (index >= 0) && (index < array.length);
        int xdiff, ydiff, leftx, rightx, uppery, lowery;

        PixelHelper pixelHelper = new PixelHelper(pixels);
        leftx = pixelHelper.getLeft(i, j);
        rightx = pixelHelper.getRight(i, j);
        uppery = pixelHelper.getUp(i, j);
        lowery = pixelHelper.getBot(i, j);

        xdiff = rightx - leftx;
        ydiff = lowery - uppery;

        int[] result = new int[]{
                xdiff,
                ydiff
        };

//      if a gradient for x or y is < 0, then we set it to 0
        for (int k = 0; k < result.length; k++) {
            if (result[k] < 0) {
                result[k] = 0;
            }
        }

        return result;
    }

    @objid("73bca2a7-bea4-4a42-be6b-2bd8f5bde8f2")
    public double[] getBlock(int starti, int startj) {
        List<List<Double>> histograms = new ArrayList<>();

        // getting histograms from each cell in a block
        for (int i = starti; i <= starti + getBlockHeight() * getCellHeight() - getCellHeight(); i += getCellHeight()) {
            for (int j = startj; j <= startj + getBlockWidth() * getCellWidth() - getCellWidth(); j += getCellWidth()) {
                List<Double> histogram = Arrays.asList(toDoubleArray(getHistogram(i, j, i + getCellHeight(), j + getCellWidth())));
                histograms.add(histogram);
            }
        }

        // concatenating normalized blocks
        List<Double> result = concat(histograms);

        // returning vector after normalization
        return HOG.normalizeVector(toPrimitiveDoubleArray(result));
    }

    @objid("1102ccb7-f224-4643-856a-10438306eebc")
    public double[] getFeatureVect() {
        int[][] pixels = getPixelArray();
        List<List<Double>> results = new ArrayList<>();

        //avoid edge!!
        for (int i = 1; i < pixels.length-1; i += getBlockHeight()*getCellHeight()) {
            for (int j = 1; j < pixels[0].length-1; j += getBlockWidth()*getCellWidth()) {
                double[] block = getBlock(i,j);
                results.add(Arrays.asList(toDoubleArray(block)));
            }
        }

        return toPrimitiveDoubleArray(concat(results));
    }

    private class PixelHelper {
        int[][] pixels;

        public PixelHelper(int[][] pixels) {
            this.pixels = pixels;
        }

        public boolean checkBounds(int i, int j) {
            return (i >= 0) && (i < pixels.length) &&
                    (j >= 0) && (j < pixels[0].length);
        }

        public int getPixel(int i, int j) {
            if (checkBounds(i, j)) {
                return pixels[i][j];
            } else {
                return 0;
            }
        }

        public int getLeft(int i, int j) {
            return getPixel(i, j - 1);
        }

        public int getRight(int i, int j) {
            return getPixel(i, j + 1);
        }

        public int getUp(int i, int j) {
            return getPixel(i - 1, j);
        }

        public int getBot(int i, int j) {
            return getPixel(i + 1, j);
        }
    }
}
