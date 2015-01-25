package UtilsTests;

import Utils.DataVector;
import Utils.ExtDataParser;
import Utils.Helper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExtDataParserTest {

    private String folderPathPenDigits;

    @Before
    public void setUp() throws Exception {
        folderPathPenDigits = Helper.getAssetsFolderStr() + "/external/pendigits/";
    }

    @Test
    public void testParsePendigits() throws Exception {
        List<DataVector> train = ExtDataParser.parsePendigits(folderPathPenDigits + "pendigits.tra", 0);
        assertNotEquals(0, train.size());
        assertEquals(16, train.get(0).feature.length);
        assertThat("class value", train.get(0).cls, greaterThanOrEqualTo(0));
        assertThat("class value", train.get(0).cls, lessThanOrEqualTo(9));
//        Helper.printData(train);
    }
}