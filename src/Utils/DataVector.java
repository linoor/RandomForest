package Utils;

/**
 * Created by ericchiu on 1/23/15.
 */
public class DataVector {

    /* using public variable here for this class serves just like struct in c++
     * getter/setter could be added later on
     */
    public double[] feature;
    public int cls;

    public DataVector(int cls, double[] feature) {
        this.cls = cls;
        this.feature = feature;
    }
}
