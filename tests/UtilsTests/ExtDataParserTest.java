package UtilsTests;

import Utils.DataVector;
import Utils.ExtDataParser;
import Utils.Helper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ExtDataParserTest {

    private String folderPathPenDigits;

    @Before
    public void setUp() throws Exception {
        folderPathPenDigits = Helper.getAssetsFolderStr() + "/external/pendigits/";
    }

    @Test
    public void testParsePendigits() throws Exception {
        List<DataVector> train = ExtDataParser.parsePendigits(folderPathPenDigits + "pendigits.tra", 0);
        Assert.assertNotEquals(0, train.size());
        Assert.assertEquals(16, train.get(0).feature.length);
        MatcherAssert.assertThat("class value", train.get(0).cls, Matchers.greaterThanOrEqualTo(0));
        MatcherAssert.assertThat("class value", train.get(0).cls, Matchers.lessThanOrEqualTo(9));
    }
}