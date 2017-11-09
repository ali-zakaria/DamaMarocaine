package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Acceuil implements ActionListener  {
	
	JFrame frame = new JFrame();
	
	private JPanel panneau = new JPanel();
	
	private JLabel modeDeJeu_label = new JLabel("Mode de Jeu");

	private JLabel niveau_label = new JLabel("Niveau");

	private JLabel titre_label = new JLabel("Jeu de Dames 2A");

	static Font fontTitre = new Font("Verdana", Font.BOLD + Font.PLAIN, 30);
	static Font fontSousTitre = new Font("Verdana", Font.BOLD + Font.PLAIN, 25);
	
	private JPanel titrePanel = new JPanel();
	private JPanel centrePanel = new JPanel();
	
	private JPanel niveauPanel = new JPanel();
	private JPanel modeDeJeuPanel = new JPanel();
	
	private JPanel sousPanelNiveau[] = new JPanel[4];
	private JPanel sousPanelModeDeJeu[] = new JPanel[3];
	
	private JButton boutonModeDeJeu[] = new JButton[2]; 
	private JButton boutonNiveau[] = new JButton[3]; 
	
	private int couleur = 191; 
	private int couleur2 = 97;
	private int count =0;
	
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("Fichier");
	JMenu Edit = new JMenu("Edition");
	JMenu help = new JMenu("Aide");

	JMenuItem save = new JMenuItem("Enregistrer");
	JMenuItem close = new JMenuItem("Quitter");
	JMenuItem acceuil;
	JMenuItem about = new JMenuItem("A propos");
	JMenuItem onlineDoc = new JMenuItem("Règles du Jeu");
	
	
	public Acceuil() {
		
		
		frame.setLocation(120,120);
		frame.setSize(600, 480);
		frame.setTitle("Jeu Dames 2A");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		titre_label.setForeground(new Color(250,250,250));
		niveau_label.setForeground(new Color(70,70,70));
		modeDeJeu_label.setForeground(new Color(70,70,70));
		
		titre_label.setFont(fontTitre);
		niveau_label.setFont(fontSousTitre);
		modeDeJeu_label.setFont(fontSousTitre);
		
		
		titrePanel.setLayout(new BoxLayout(titrePanel,BoxLayout.LINE_AXIS));
		titrePanel.add(titre_label);
		
		niveauPanel.setLayout(new BoxLayout(niveauPanel,BoxLayout.PAGE_AXIS));
		
		for (int i = 0; i < 4; i++) {
			sousPanelNiveau[i] = new JPanel();
			sousPanelNiveau[i].setLayout(new BoxLayout(sousPanelNiveau[i], BoxLayout.LINE_AXIS));
			sousPanelNiveau[i].setBackground(new Color(couleur,couleur,couleur));
		}
		boutonNiveau[0] = new JButton("Facile");
		boutonNiveau[1] = new JButton("Moyen");
		boutonNiveau[2] = new JButton("Difficile");
		
		for (int i = 0; i < 3; i++) {
			boutonNiveau[i].setFont(new Font("Verdana", Font.PLAIN, 16));
			boutonNiveau[i].addActionListener(this);
			boutonNiveau[i].setBackground(Color.white);

		}
		
		
		
		sousPanelNiveau[0].add(niveau_label);
		sousPanelNiveau[1].add(boutonNiveau[0]);
		sousPanelNiveau[2].add(boutonNiveau[1]);
		sousPanelNiveau[3].add(boutonNiveau[2]);
		
		niveauPanel.add(Box.createVerticalStrut(20));
		for (int i = 0; i < 4; i++) {
			niveauPanel.add(sousPanelNiveau[i]);
			niveauPanel.add(Box.createVerticalStrut(20));
		}
		
		modeDeJeuPanel.setLayout(new BoxLayout(modeDeJeuPanel,BoxLayout.PAGE_AXIS));
		
		for (int i = 0; i < 3; i++) {
			sousPanelModeDeJeu[i] = new JPanel();
			sousPanelModeDeJeu[i].setLayout(new BoxLayout(sousPanelModeDeJeu[i], BoxLayout.LINE_AXIS));
			sousPanelModeDeJeu[i].setBackground(new Color(couleur,couleur,couleur));

		}
		
		
		boutonModeDeJeu[0] = new JButton("1 Joueur");
		boutonModeDeJeu[1] = new JButton("2 Joueurs");
		
		for (int i = 0; i < 2; i++) {
			boutonModeDeJeu[i].setFont(new Font("Verdana", Font.PLAIN, 16));
			boutonModeDeJeu[i].addActionListener(this);
			boutonModeDeJeu[i].setBackground(Color.white);
		}
		
		sousPanelModeDeJeu[0].add(modeDeJeu_label);
		sousPanelModeDeJeu[1].add(boutonModeDeJeu[0]);
		sousPanelModeDeJeu[2].add(boutonModeDeJeu[1]);

		modeDeJeuPanel.add(Box.createVerticalStrut(20));
		for (int i = 0; i < 3; i++) {
			modeDeJeuPanel.add(sousPanelModeDeJeu[i]);
			modeDeJeuPanel.add(Box.createVerticalStrut(30));
		}
		modeDeJeuPanel.add(Box.createVerticalStrut(18));
		
		Border cadre = BorderFactory.createRaisedBevelBorder();
		niveauPanel.setBorder(cadre);
		modeDeJeuPanel.setBorder(cadre);
		
		centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.LINE_AXIS));
		centrePanel.add(Box.createHorizontalStrut(40));
		centrePanel.add(niveauPanel);
		centrePanel.add(Box.createHorizontalStrut(50));
		centrePanel.add(modeDeJeuPanel);
		centrePanel.add(Box.createHorizontalStrut(40));

		
		panneau.setLayout(new BoxLayout(panneau,BoxLayout.PAGE_AXIS));
		panneau.add(Box.createVerticalStrut(50));
		panneau.add(titrePanel);
		panneau.add(Box.createVerticalStrut(40));
		panneau.add(centrePanel);	

		acceuil = new JMenuItem();
		acceuil.setText("Revenir à l'Acceuil");

		save.setEnabled(false);
		Edit.setEnabled(true);
		close.setEnabled(true);
		about.setEnabled(true);
		onlineDoc.setEnabled(true);
		boutonNiveau[2].setEnabled(false);

		file.add(save);
		file.addSeparator();
		file.add(close);

		Edit.add(acceuil);

		help.add(onlineDoc);
		help.add(about);

		menu.add(file);
		menu.add(Edit);
		menu.add(help);
		frame.setJMenuBar(menu);

		//frame.setIconImage(new ImageIcon(this.getClass().getResource("/img/photo.gif")).getImage());
		
		Image img = null;
		try {
			img = ImageIO.read(new File("img/photo.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(img);
		
		Container contentPane = frame.getContentPane();
		panneau.setBackground(new Color(couleur2,couleur2,couleur2));
		titrePanel.setBackground(new Color(couleur2,couleur2,couleur2));
		centrePanel.setBackground(new Color(couleur2,couleur2,couleur2));
		modeDeJeuPanel.setBackground(new Color(couleur,couleur,couleur));
		niveauPanel.setBackground(new Color(couleur,couleur,couleur));
		
		save.addActionListener(this);
		close.addActionListener(this);
		acceuil.addActionListener(this);
		onlineDoc.addActionListener(this);
		about.addActionListener(this);

		contentPane.add(panneau);
		frame.setResizable(false);
		//frame.pack();
		frame.setVisible(true);
		
	}

	
	void apropos_actionPerformed() {
		new apropos();
	}

	void onlineDoc_actionPerformed() {
		URI uri = URI
				.create("http://www.ffjd.fr/Web/index.php?page=reglesdujeu");
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void quitter_actionPerformed() {
		int result = JOptionPane.showConfirmDialog(null,
				"Voulez vous vraiment quitter ?", "Jeu Dames 2A",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == 0) {// reponse oui

			System.exit(0);
		}
	}


	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.close) {
			quitter_actionPerformed();
		} else if (ae.getSource() == this.about) {
			apropos_actionPerformed();
		} else if (ae.getSource() == this.acceuil) {
			new Acceuil();
			frame.dispose();
		} else if (ae.getSource() == this.onlineDoc) {
			onlineDoc_actionPerformed();
		} else if(ae.getSource() == this.boutonNiveau[0]) {
			count++;
			boutonNiveau[0].setEnabled(false);
			boutonNiveau[1].setEnabled(false);
		} else if(ae.getSource() == this.boutonNiveau[1]) {
			count++;
			boutonNiveau[0].setEnabled(false);
			boutonNiveau[1].setEnabled(false);
		} else if(ae.getSource() == this.boutonNiveau[2]) {
			
		} else if(ae.getSource() == this.boutonModeDeJeu[0]) {
			Fonctions.UnJoueur= true;
			Fonctions.Hjoue = true;
			boutonModeDeJeu[0].setEnabled(false);
			boutonModeDeJeu[1].setEnabled(false);
			count++;
		} else if(ae.getSource() == this.boutonModeDeJeu[1]) {
			Fonctions.UnJoueur= false;
			Fonctions.Hjoue = true;
			boutonModeDeJeu[1].setEnabled(false);
			boutonModeDeJeu[0].setEnabled(false);
			count++;
		}
		System.out.println(count);

		
		if(count==2) {
			frame.dispose();
			EquipeB.equB.clear();
			EquipeH.equH.clear();		
			Fonctions.Case = false;
			new Echiquier();
		}
		
		
		
	}



}
