package launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Let the panel displaying image background
	 * 
	 * @param Graphic g
	 */
	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("TNCYTY.PNG"));
			g.drawImage(img,  0,  0,  this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
