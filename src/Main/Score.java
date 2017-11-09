package Main;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Score extends JPanel {

	public Score() {
		super();
	}

	public void paintComponent(Graphics graph) {

				//ImageIO.read(new File("D:/Workspace/Java/Jeu Dames 2A/bin/echiquier.jpg"));
		graph.drawImage(EquipeB.equB.get(0).getImg(), 0, 0, Fonctions.pion, Fonctions.pion, this);
		graph.drawImage(EquipeH.equH.get(0).getImg(), 0, 0, Fonctions.pion, Fonctions.pion, this);



	}
}
