package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class EquipeB {

	static List<Img> equB = new ArrayList<Img>();

	public EquipeB() {

		for (int j = 5; j < 8; j++) {
			for (int i = 1; i < 8; i += 2) {
				if (j == 6)
					i--;
				try {
					equB.add(new Img(
							ImageIO.read(new File("img/pionBlanc.png")),Fonctions.pion * i, Fonctions.pion * j, false));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (j == 6)
					i++;
			}

		}

	}

	public static String str() {
		String str = "";
		for (int i = 0; i < EquipeB.equB.size(); i++) {
			str += "element " + i + " : " + EquipeB.equB.get(i) + "  ";
		}
		str += "size " + EquipeB.equB.size();
		return str;
	}

}
