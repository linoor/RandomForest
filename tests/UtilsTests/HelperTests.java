package UtilsTests;

import Utils.Helper;
import org.junit.Test;
import org.junit.Assert;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linoor on 2014-11-29.
 */
public class HelperTests {

    @Test
    public void testAssetsFolder() {
//        String myPath = "E:\\Dev\\RandomForest\\assets";
        String myPath = System.getProperty("user.dir") + "/assets";
        Assert.assertEquals(Helper.getAssetsFolder().toAbsolutePath().toString(), myPath);
    }

    public void testAssetsFolderStr() {
//        String myPath = "E:\\Dev\\RandomForest\\assets";
        String myPath = System.getProperty("user.dir") + "/assets";
        Assert.assertEquals(Helper.getAssetsFolderStr(), myPath);
    }

    @Test
    public void testSetUpTestingData() {
    }

    @Test
    public void testPrintData() {
    }

    @Test
    public void testModeInt() {
        int[] test = {0,2,3,0,2,9,0,4,4,5,6,7,8,2,5,7,4,0,9,9,3,4,6,7,8,3,9,0,4,4,4,4,4,4};
        List<Integer> testList = new ArrayList<Integer>();
        for (int i : test) {
            testList.add(i);
        }
        Assert.assertEquals(4, Helper.getModeInt(testList));
    }
}

