package HOG;

import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

import static RandomForestHOG.HOG.HOGParam.BlockType.RADIAL;
import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class HOGTests {

    private final File simpleImg = Paths.get("E:\\Dev\\RandomForest\\assets\\vectorGradientTest.png").toFile();

    @Before
    public void setup() {
    }

    @Test
    public void testCreation() {
        HOG hog = null;
        try {
            hog = new HOG(new HOGParam(RADIAL, 10, 2, 4, 10, 5, 1, 3, 3), simpleImg);
            assertNotNull(hog);
            assertThat("blockType should be radial", hog.getBlockType(), equalTo(RADIAL));
            assertEquals(hog.getBinNumber(), 10);
            assertEquals(hog.getCellWidth(), 2);
            assertEquals(hog.getCellHeight(), 4);
            assertEquals(hog.getBlockWidth(), 10);
            assertEquals(hog.getBlockHeight(), 5);
            assertEquals(hog.getMaskType(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreationRectangular() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 10, 2, 4, 10, 5, 1, 3, 3), simpleImg);
            assertThat("blockType should be rectangular", hog.getBlockType(), equalTo(RECTANGULAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetPixelArray() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 1, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            assertArrayEquals("testing getting array of pixels", new int[][] { {48, 148, 154}, {163, 252, 30}, {108, 216, 21} }, hog.getPixelArray());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testComputingGradientVector() {
        HOG hog;
        try {
          hog = new HOG(new HOGParam(RECTANGULAR, 1, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            assertArrayEquals("the vector gradient for the middle vector should be equal to the expected value",
                    new int[] {0, 68}, hog.getGradientVector(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void computingAGradientVectorForEdgePixel() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 1, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            assertArrayEquals("the vector gradient for the upper left vector should be equal to the expected value",
                    new int[] {148, 163}, hog.getGradientVector(0, 0));
            assertArrayEquals("the vector gradient for the lower right vector should be equal to the expected value",
                    new int[] {0, 0}, hog.getGradientVector(2, 2));
            assertArrayEquals("the vector gradient for the upper right vector should be equal to the expected value",
                    new int[]{0, 30}, hog.getGradientVector(0, 2));
            assertArrayEquals("the vector gradient for the upper middle vector should be equal to the expected value",
                    new int[]{106, 252}, hog.getGradientVector(0, 1));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGradientVectorMagnitude() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 1, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            Assert.assertEquals("Magnitude of a vector gradient should be equal the expected value",
                    53.74,
                    HOG.computeMagnitude(new int[]{38, 38}),
                    0.01);
            Assert.assertEquals("Magnitude of a vector gradient should be equal the expected value",
                    68.00,
                    HOG.computeMagnitude(hog.getGradientVector(1, 1)),
                    0.01);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testComputeAngle() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 1, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            assertEquals("Angle should be equal to the expected value",
                    0.785,
                    HOG.computeAngle(new int[]{38, 38}),
                    0.001);
            Assert.assertEquals("Angle should be equal to the expected value",
                    0.00,
                    HOG.computeAngle(hog.getGradientVector(1,1)),
                    0.001);
            Assert.assertEquals("Angle should be equal to 0 if one of the values is 0",
                    0.00,
                    HOG.computeAngle(new int[] { 0, 0 }),
                    0.001);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testHistogram() {
        HOG hog;
        try {
            hog = new HOG(new HOGParam(RECTANGULAR, 9, 1, 1, 1, 1, 2, 3, 3), simpleImg);
            Assert.assertEquals("Histogram at zero degrees (first part of the histogram) should be equal expected value",
                    412.237,
                    hog.getHistogram(0,0,3,3)[0],
                    0.001);
            Assert.assertEquals("Histogram at zero degrees (30 degrees) should be equal expected value",
                    260.585,
                    hog.getHistogram(0,0,3,3)[1],
                    0.01);
            // TODO needs more tests probably, especially edge cases
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVectorNormalization() {
        assertArrayEquals(new double[] {0.801784, 0.534522, 0.267261}, HOG.normalizeVector(new double[] {3, 2, 1}), 0.001);
    }
}

