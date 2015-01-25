package RandomForestHOG.HOG;

import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import Utils.DataVector;
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

	private DataVector[] dataVectors = null;
	private HOG hog;
	private HOGParam hogParam;
	private BufferedImage image;


	public HOGAppli() {
		this.hogParam = new HOGParam(RECTANGULAR,  9, 4, 4, 2, 2, 1, 10, 10);
		// 10pixel x 10pixel => cell(4pixel x 4pixel) x block(2cell x 2cell)
	}
	
	public HOGAppli(File[] files, final HOGParam initHogParam) throws Exception {
		LoadFiles(files);
		this.hogParam = initHogParam;
	}

	public void LoadFiles(File[] files) throws Exception {
		try {
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("Loading Directory : " + file.getName());
					//dataVectors.add(new DataVector(file.getName(), null))
					LoadFiles(file.listFiles()); // Calls same method again.
				} else {
					//System.out.println("Loading File : " + file.getName());
					LoadImage(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

	}

	public void LoadImage(File fileName) throws Exception {
		try {
			//System.out.println("Loading Image from : " + fileName.getAbsolutePath());
			BufferedImage originalImage = ImageIO.read(new File(fileName.getAbsolutePath()));
			image = ImageUtils.resize(originalImage, hogParam.getWidth(), hogParam.getHeight());
			hog = new HOG(this.hogParam, image);
			
			getDataVector(hog);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void getDataVector(HOG dataHog){
	//	DataVector newVector = new DataVector(int cls, dataHog.getFeatureVect());
		
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
