package Main;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * @author Ali_ZAKARIA
 * @ecole ENSEA
 * @version 1.1
 */

@SuppressWarnings("serial")
public class apropos extends JFrame {

	public apropos() {

		this.setTitle("À Propos");
		this.setSize(404, 373);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setIconImage(new ImageIcon(this.getClass().getResource("/img/photo.gif")).getImage());
		
		Image img = null;
		try {
			img = ImageIO.read(new File("img/photo.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(img);

		
		this.setContentPane(new Panneau());
		this.setVisible(true);

	}

}
