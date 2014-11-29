package UtilsTests;

import Utils.Helper;
import junit.framework.Assert;
import org.junit.Test;

import java.nio.file.Path;

/**
 * Created by Linoor on 2014-11-29.
 */
public class HelperTests {

    @Test
    public void testAssetsFolder() {
        String myPath = "E:\\Dev\\RandomForest\\assets";
        Assert.assertEquals(Helper.getAssetsFolder().toAbsolutePath().toString(), myPath);
    }
}

