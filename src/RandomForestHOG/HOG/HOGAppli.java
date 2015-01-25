package RandomForestHOG.HOG;

import RandomForestHOG.HOG.HOG;
import RandomForestHOG.HOG.HOGParam;
import Utils.DataVector;
import static RandomForestHOG.HOG.HOGParam.BlockType.RADIAL;
import static RandomForestHOG.HOG.HOGParam.BlockType.RECTANGULAR;
import static org.junit.Assert.fail;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.swt.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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
		this.hogParam = new HOGParam(RECTANGULAR,  9, 6, 6, 3, 3, 1, 20, 20);
		dataVectors = new ArrayList<DataVector>();
		// resize to 10pixel x 10pixel 
		// 9 (bins/histogram) x 4 (cells(4pixel x 4pixel)/block) x block(2cell x 2cell)
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
			//System.out.println("Loading Image from : " + fileName.getName());
			HOGAppli hogAppli = new HOGAppli();
			BufferedImage originalImage = ImageIO.read(new File(fileName.getAbsolutePath()));
			image = ImageUtils.resize(originalImage, hogAppli.hogParam.getWidth(), hogAppli.hogParam.getHeight());
			drawImage(image);
			hog = new HOG(this.hogParam, image);
		
			dataVectors.add(new DataVector(cls, hog.getFeatureVect()));			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void drawImage(BufferedImage im) {
		// TODO Auto-generated method stub
		 //ImagePane imagePane = ;
		 JFrame frame = new JFrame();
         frame.setVisible(true);
         frame.setAlwaysOnTop(true);
         frame.setLayout(new BorderLayout());
         frame.add(new ImagePane(im));
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         frame.pack();
         frame.setLocationRelativeTo(null);

	}
	
	public class ImagePane extends JPanel {
        public ImagePane(BufferedImage image) {
            setLayout(new BorderLayout());
            ImageIcon icon = new ImageIcon(image);
            add(new JLabel(icon));
        }
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
