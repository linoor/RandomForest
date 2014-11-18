package HOG;

import org.junit.Test;
import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Linoor on 2014-11-18.
 */
public class HOGTests {

    @Test
    public void testCreation() {
        try {
            HOG hog = new HOG(new HOGParam(HOGParam.BlockType.RADIAL, 10, 2, 4, 10, 5, 1));
            assertNotNull(hog);
            assertThat("blockType should be radial", hog.getBlockType(), equalTo(HOGParam.BlockType.RADIAL));
            assertEquals(hog.getBinNumber(), 10);
            assertEquals(hog.getCellWidth(), 2);
            assertEquals(hog.getCellHeight(), 4);
            assertEquals(hog.getBlockWidth(), 10);
            assertEquals(hog.getBlockHeight(), 10);
            assertEquals(hog.getMaskType(), 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
