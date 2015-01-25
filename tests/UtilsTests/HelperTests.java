package UtilsTests;

import Utils.Helper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linoor on 2014-11-29.
 */
public class HelperTests {

    @Test
    public void testAssetsFolder() {
        String myPath = System.getProperty("user.dir") + "/assets";
        Assert.assertEquals(Helper.getAssetsFolder().toAbsolutePath().toString(), myPath);
    }

    @Test
    public void testAssetsFolderStr() {
        String myPath = System.getProperty("user.dir") + "/assets";
        Assert.assertEquals(Helper.getAssetsFolderStr(), myPath);
    }

    @Test
    public void testModeInt() {
        List<Integer> testList = new ArrayList<Integer>();
        Assert.assertEquals(Integer.MIN_VALUE, Helper.getModeInt(testList));

        testList.clear();
        int[] test1 = {1};
        for (int i : test1) {
            testList.add(i);
        }
        Assert.assertEquals(1, Helper.getModeInt(testList));

        testList.clear();
        int[] test2 = {0, 2, 3, 0, 2, 9, 0, 4, 4, 5, 6, 7, 8, 2, 5, 7, 4, 0, 9, 9, 3, 4, 6, 7, 8, 3, 9, 0, 4, 4, 4, 4, 4, 4};
        for (int i : test2) {
            testList.add(i);
        }
        Assert.assertEquals(4, Helper.getModeInt(testList));

        testList.clear();
        int[] test3 = {1, 2, 3, 4, 5, 6};
        for (int i : test3) {
            testList.add(i);
        }
        Assert.assertEquals(1, Helper.getModeInt(testList));
    }
}

