package UtilsTests;

import Utils.Helper;
import org.junit.Test;
import org.junit.Assert;

import java.nio.file.Path;

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
}

