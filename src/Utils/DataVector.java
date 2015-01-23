package Utils;

/**
 * Created by ericchiu on 1/23/15.
 */
public class DataVector {

    public double[] feature;
    public int cls;

    DataVector(int cls, double[] feature) {
        this.cls = cls;
        this.feature = feature;
    }
}
