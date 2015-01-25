package HOG;

import static RandomForestHOG.HOG.HOGParam.BlockType.*;
import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import RandomForestHOG.HOG.HOGAppli;
import RandomForestHOG.HOG.HOGParam;

public class HOGAppliTests {
	
	private final File pngfile = Paths.get("C:\\git\\RandomForest\\assets\\Test\\vectorGradientTest.png").toFile();
	private final File file = Paths.get("C:\\git\\RandomForest\\assets\\Test\\square_n11013278.bmp").toFile();
	private final File[] files = new File("C:\\git\\RandomForest\\assets\\DB_panneaux").listFiles();

	@Test
	public void testLoadImage() {
		try {
            HOGAppli hogAppli = new HOGAppli();			
			hogAppli.LoadImage(file);
			hogAppli.drawImage();
		} catch (Exception e) {
			System.out.print("\n\tError in testLoadImage()\n");
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testLoadFile() {
		try {
            HOGAppli hogAppli = new HOGAppli();			
			hogAppli.LoadFiles(files);
		} catch (Exception e) {
			System.out.print("\n\tError in testLoadFile()\n");
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreation() {
		HOGAppli hogAppli = null;
		try {
			hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR,  9, 4, 4, 2, 2, 1, 10, 10));
			assertNotNull(hogAppli);
			assertEquals(hogAppli.getImageHeight(), 10);
			assertEquals(hogAppli.getImageWidth(), 10);
			hogAppli.drawImage();

		} catch (Exception e) {
			System.out.print("\n\tError in testCreation()\n");
			e.printStackTrace();
			fail();
		}
	}

}
