package Utils;

import org.junit.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Linoor on 2014-11-29.
 */
public class Helper {
    public static Path getAssetsFolder() {
        return Paths.get("assets");
    }
}
