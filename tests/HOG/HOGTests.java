package HOG;

import org.junit.Before;
import org.junit.Test;
import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static RandomForestHOG.HOG.HOGParam.BlockType.RADIAL;
import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class HOGTests {

    private TemporaryFolder tmpFolder;
    private File tmpFile;
    private HOG exampleHOG;

    @Before
    public void setup() {
        tmpFolder = new TemporaryFolder();
        try {
            tmpFile = tmpFolder.newFile("input.txt");
            exampleHOG = new HOG(new HOGParam(RECTANGULAR, 10, 2, 4, 10, 5, 1), tmpFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreation() {
        HOG hog = null;
        try {
            hog = new HOG(new HOGParam(RADIAL, 10, 2, 4, 10, 5, 1), tmpFile);
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
            hog = new HOG(new HOGParam(RECTANGULAR, 10, 2, 4, 10, 5, 1), tmpFile);
            assertThat("blockType should be rectangular", hog.getBlockType(), equalTo(RECTANGULAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

