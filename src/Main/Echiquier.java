package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Echiquier  implements ActionListener{
	
	private JPanel panneau = new JPanel();
	private JPanel echiquier = new JPanel();
	private JPanel bouton = new JPanel();
	
	static JFrame frame = new JFrame();

	public static JButton passer = new JButton("Passer");
	public static JButton nfoukh = new JButton("Nfoukh");

	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("Fichier");
	JMenu Edit = new JMenu("Edition");
	JMenu help = new JMenu("Aide");

	JMenuItem save = new JMenuItem("Enregistrer");
	JMenuItem close = new JMenuItem("Quitter");
	JMenuItem acceuil = new JMenuItem("Revenir à l'Acceuil");
	JMenuItem about = new JMenuItem("A propos");
	JMenuItem onlineDoc = new JMenuItem("Règles du Jeu");

	
	
	public Echiquier() {
		super();
		
		frame.setLocation(120,120);
		frame.setSize(610, 610);
		frame.setTitle("Jeu Dames 2A");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panneau.setLayout(new BoxLayout(panneau,BoxLayout.PAGE_AXIS));
		panneau.add(Box.createVerticalStrut(40));
	
		echiquier.setLayout(new BoxLayout(echiquier,BoxLayout.LINE_AXIS));		
		echiquier.add(Box.createHorizontalStrut(70));
		echiquier.add(new Partie());
		echiquier.add(Box.createHorizontalStrut(80));
		
		panneau.add(echiquier);
		panneau.add(Box.createVerticalStrut(42));
		
		bouton.setLayout(new BoxLayout(bouton,BoxLayout.LINE_AXIS));
		panneau.add(Box.createVerticalStrut(20));

		save.setEnabled(false);
		Edit.setEnabled(true);
		close.setEnabled(true);
		about.setEnabled(true);
		onlineDoc.setEnabled(true);

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
		panneau.setBackground(new Color(97,97,97));
		echiquier.setBackground(new Color(97,97,97));
		bouton.setBackground(new Color(97,97,97));
		
		save.addActionListener(this);
		close.addActionListener(this);
		acceuil.addActionListener(this);
		onlineDoc.addActionListener(this);
		about.addActionListener(this);
		
		passer.addActionListener(this);
		nfoukh.addActionListener(this);

		frame.getRootPane().setDefaultButton(passer);
		passer.setMnemonic(KeyEvent.VK_ENTER);

		contentPane.add(panneau);
		//pack();
		frame.setResizable(false);
		
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
		} else if (ae.getSource() == this.onlineDoc) {
			onlineDoc_actionPerformed();
		} else if (ae.getSource() == this.acceuil) {
			new Acceuil();
			frame.dispose();
		}
		
		
	}

}
