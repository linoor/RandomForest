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

	//File[] files = new File("C:/git/RandomForest/assets/DB_panneaux").listFiles();
	File[] files = new File("C:\\git\\RandomForest\\assets\\Test").listFiles();
	@Test
	public void testLoadImage() {
		try {
            HOGAppli hogAppli = new HOGAppli();			
			hogAppli.LoadImage(file.toString());
			hogAppli.drawImage();
		} catch (Exception e) {
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
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testsetIMG() {
		HOGAppli hogAppli = null;
		try {
			hogAppli = new HOGAppli(files, new HOGParam(RECTANGULAR,  9, 4, 4, 2, 2, 1, 10, 10));

			assertEquals(hogAppli.getImageHeight(), 20);
			assertEquals(hogAppli.getImageWidth(), 20);

			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
	}

}
