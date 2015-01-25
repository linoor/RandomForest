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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import fr.ensmp.caor.levis.classifier.*;
import fr.ensmp.caor.levis.learner.*;
import fr.ensmp.caor.levis.sample.*;
import fr.ensmp.caor.levis.ui.*;

public class HOGAppli {

	private List<DataVector> dataVectors;
	private int cls = 0;
	private HOG hog;
	private HOGParam hogParam;
	private BufferedImage image;

	public HOGAppli() {
		this.hogParam = new HOGParam(RECTANGULAR,  9, 4, 4, 2, 2, 1, 10, 10);
		dataVectors = new ArrayList<DataVector>();
		// 10pixel x 10pixel => cell(4pixel x 4pixel) x block(2cell x 2cell)
	}
	
	public HOGAppli(File[] files, HOGParam initHogParam) throws Exception {
		this();
		this.hogParam = initHogParam;
		LoadFiles(files);
	}

	public void LoadFiles(File[] files) throws Exception {
		try {
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("Loading Directory : " + file.getName());
					cls++;					
					LoadFiles(file.listFiles()); // Calls same method again.
				} else {
					System.out.println("Loading File : " + file.getName());
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
			//System.out.println("Loading Image from : " + fileName.getName());
			BufferedImage originalImage = ImageIO.read(new File(fileName.getAbsolutePath()));
			image = ImageUtils.resize(originalImage, this.hogParam.getWidth(), this.hogParam.getHeight());
			hog = new HOG(this.hogParam, image);
			
			dataVectors.add(new DataVector(cls, hog.getFeatureVect()));			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}


	
	public void drawImage() {
		Graphics g = image.getGraphics();
		g.drawImage(image, 0, 0, null);
	}
	
	public List<DataVector> getDataVectors(){
	    return dataVectors;
	};
	
	public int getImageHeight() {
		return image.getHeight();
	}

	public int getImageWidth() {
		return image.getWidth();
	}

}
