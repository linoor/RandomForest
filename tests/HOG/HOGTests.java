package HOG;

import org.junit.Before;
import org.junit.Test;
import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Linoor on 2014-11-18.
 */
public class HOGTests {

    private TemporaryFolder tmpFolder;

    @Before
    public void setup() {
        tmpFolder = new TemporaryFolder();
    }

    @Test
    public void testCreation() {
        try {
            File tmpFile = tmpFolder.newFile("input.txt");
            HOG hog = new HOG(new HOGParam(HOGParam.BlockType.RADIAL, 10, 2, 4, 10, 5, 1), tmpFile);
            assertNotNull(hog);
            assertThat("blockType should be radial", hog.getBlockType(), equalTo(HOGParam.BlockType.RADIAL));
            assertEquals(hog.getBinNumber(), 10);
            assertEquals(hog.getCellWidth(), 2);
            assertEquals(hog.getCellHeight(), 4);
            assertEquals(hog.getBlockWidth(), 10);
            assertEquals(hog.getBlockHeight(), 5);
            assertEquals(hog.getMaskType(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
