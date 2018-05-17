package photoshop;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PictureFrame extends Panel {

	private static final long serialVersionUID = 1L;
	private static BufferedImage image;

	public PictureFrame() {

	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	static public void main(String args[]) throws Exception {

		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("Pictures"));
		int result = fc.showOpenDialog(null);
		fc.setVisible(true);
		
		File selectedFile = null;
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fc.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
	        String[] options = {"Heat Map", "Heat Map Mk. II", "Swap Colour Values", "Black and White", "Black and White Mk. II", "Photo Negatives", "Monochrome Negative", 
	        		"Acid Trip", "Sketch Outline"};

	        ImageIcon icon = new ImageIcon("Resources/Question.png");
	        String choice = (String)JOptionPane.showInputDialog(null, "Choose the photo filter", 
	                "Paintshop Pro Edition", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
	        
			AlterImage alterImage = new AlterImage();
			image = alterImage.photoshopPicture(selectedFile.getAbsoluteFile(), choice);

			JFrame frame = new JFrame("Picture Frame");
			Panel panel = new PictureFrame();
			frame.getContentPane().add(panel);
			frame.setSize(image.getWidth(), image.getHeight());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		else {
			System.exit(0);
		}
		
		
	}
}