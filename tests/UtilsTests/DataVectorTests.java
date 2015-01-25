package UtilsTests;

import Utils.DataVector;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ericchiu on 1/24/15.
 */
public class DataVectorTests {

    @Test
    public void testDataVector() {
        int cls = 1;
        double[] feature = {0.22, 1.23, 2.99, 0.331};
        DataVector vec = new DataVector(cls, feature);
        Assert.assertEquals(cls, vec.cls);
        Assert.assertArrayEquals(feature, vec.feature, 0);
    }
}
