package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Partie extends JPanel implements MouseListener {

	EquipeH H = new EquipeH();
	EquipeB B = new EquipeB();

	public Partie() {
		super();
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics graph) {

		Image img = null;
		try {
			img = ImageIO.read(new File("img/echiquier.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		graph.drawImage(img, 0, 0, Fonctions.Cote, Fonctions.Cote, this);

		for (int i = 0; i < EquipeH.equH.size(); i++) {
			graph.drawImage(EquipeH.equH.get(i).getImg(), EquipeH.equH.get(i)
					.getX(), EquipeH.equH.get(i).getY(), Fonctions.pion,
					Fonctions.pion, this);
		}
		for (int i = 0; i < EquipeB.equB.size(); i++) {
			graph.drawImage(EquipeB.equB.get(i).getImg(), EquipeB.equB.get(i)
					.getX(), EquipeB.equB.get(i).getY(), Fonctions.pion,
					Fonctions.pion, this);
		}

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		
		if(Fonctions.UnJoueur == true) {
			if (Fonctions.Hjoue == false) {
				Fonctions.CaseSelect(e.getX(), e.getY());
				if (Fonctions.Case == true) {
					Fonctions.obligerAPrendre(e.getX(), e.getY());

				}
				if (Fonctions.BougerPion(e.getX(), e.getY())) {
					repaint();
				}
			}
			
		}
		else if (Fonctions.UnJoueur == false) {
			if (Fonctions.Hjoue == false) {
				Fonctions.CaseSelect(e.getX(), e.getY());
				if (Fonctions.Case == true) {
					Fonctions.obligerAPrendre(e.getX(), e.getY());

				}
				if (Fonctions.BougerPion(e.getX(), e.getY())) {
					repaint();
				}
			}
		}
		repaint();
		finPartie();
	}

	public void mouseReleased(MouseEvent e) {
		
		if(Fonctions.UnJoueur == true) {
			if (Fonctions.Hjoue == true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Fonctions.joueurAuto();
				Fonctions.Hjoue = false;
			}
		}
		
		else if (Fonctions.UnJoueur == false) {
	
			
			if (Fonctions.Hjoue == true) {
				Fonctions.CaseSelect(e.getX(), e.getY());
				if (Fonctions.Case == true) {
					Fonctions.obligerAPrendre(e.getX(), e.getY());

				}
				if (Fonctions.BougerPion(e.getX(), e.getY())) {
					repaint();
				}
			}
		}
		repaint();
		finPartie();


	}
	
	public void finPartie() {
		if (EquipeB.equB.isEmpty()) {
			System.out.println("H a gagné");
			int result = JOptionPane.showConfirmDialog(null,
					"Noir a gagné, voulez vous rejouer",
					"Jeu Dames 2A",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {// reponse oui
				EquipeB.equB.clear();
				EquipeH.equH.clear();
				repaint();
				new Echiquier();
				Fonctions.Case = false;
			}
			else System.exit(0);
			
		} else if (EquipeH.equH.isEmpty()) {
			System.out.println("B a gagné");

			int result = JOptionPane.showConfirmDialog(null,
					"Blanc a gagné, voulez vous rejouer",
					"Jeu Dames 2A",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {// reponse oui
				EquipeB.equB.clear();
				EquipeH.equH.clear();
				repaint();
				new Echiquier();		
				Fonctions.Case = false;
			}
			else System.exit(0);
		} else if (Fonctions.aucunBnepeutBouger()) {
			System.out.println("H a gagné");
			
			int result = JOptionPane.showConfirmDialog(null,
					"Noir a gagné, voulez vous rejouer",
					"Jeu Dames 2A",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {// reponse oui
				EquipeB.equB.clear();
				EquipeH.equH.clear();
				repaint();
				new Echiquier();		
				Fonctions.Case = false;
			}
			else System.exit(0);

		} else if (Fonctions.aucunHnepeutBouger()) {
			System.out.println("B a gagné");
			
			int result = JOptionPane.showConfirmDialog(null,
					"Blanc a gagné, voulez vous rejouer",
					"Jeu Dames 2A",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {// reponse oui
				EquipeB.equB.clear();
				EquipeH.equH.clear();
				repaint();
				new Echiquier();	
				Fonctions.Case = false;
			}
			else System.exit(0);
		}
	}

}
