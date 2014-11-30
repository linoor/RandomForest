package HOG;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;

import static RandomForestHOG.HOG.HOGParam.BlockType.RADIAL;
import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class HOGTests {

    private final File simpleImg = Paths.get("E:\\Dev\\RandomForest\\assets\\vectorGradientTest.png").toFile();
    private HOG exampleHOG;

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
                    new int[] {0, 68}, hog.getPixelGradient(1,1));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

