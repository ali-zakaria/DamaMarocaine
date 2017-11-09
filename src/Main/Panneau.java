package Main;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * @author Ali_ZAKARIA
 * @ecole ENSEA
 * @version 1.1
 */

@SuppressWarnings("serial")
public class Panneau extends JPanel {
	public void paintComponent(Graphics graph) {
		Image img = null;
		try {
			img = ImageIO.read(new File("img/apropos.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		graph.drawImage(img, 0, 0, 398,344 , this);

	}

}
