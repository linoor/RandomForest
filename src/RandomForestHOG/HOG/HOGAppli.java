package RandomForestHOG.HOG;

import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import static RandomForestHOG.HOG.HOGParam.BlockType.RADIAL;
import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;
import static org.junit.Assert.fail;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import fr.ensmp.caor.levis.classifier.*;
import fr.ensmp.caor.levis.learner.*;
import fr.ensmp.caor.levis.sample.*;
import fr.ensmp.caor.levis.ui.*;

public class HOGAppli {

	private HOG hog;
	private HOGParam hogParam;
	private BufferedImage image;


	public HOGAppli() {
		this.hogParam = new HOGParam(RECTANGULAR,  9, 4, 4, 2, 2, 1, 10, 10);
		// TODO Auto-generated constructor stub
	}
	
	public HOGAppli(File[] files, final HOGParam initHogParam) throws Exception {
		LoadFiles(files);
		this.hogParam = initHogParam;
	}

	public void LoadFiles(File[] files) throws Exception {
		try {
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("Directory: " + file.getName());
					LoadFiles(file.listFiles()); // Calls same method again.
				} else {
					System.out.println("File: " + file.getName());
					LoadImage(file.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

	}

	public void LoadImage(String fileName) throws Exception {
		try {
			BufferedImage originalImage = ImageIO.read(new File(fileName));
			image = ImageUtils.resize(originalImage, hogParam.getWidth(), hogParam.getHeight());
			hog = new HOG(this.hogParam, image);
			// TODO
			//hog.getFeatureVect();
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void drawImage() {
		Graphics g = image.getGraphics();
		g.drawImage(image, 0, 0, null);
	}

	public int getImageHeight() {
		return image.getHeight();
	}

	public int getImageWidth() {
		return image.getWidth();
	}

}
