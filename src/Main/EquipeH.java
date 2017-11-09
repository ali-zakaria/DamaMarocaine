package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class EquipeH {

	static List<Img> equH = new ArrayList<Img>();

	public EquipeH() {

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 8; i += 2) {
				if (j == 1)
					i++;

				try {
					equH.add(new Img(
							ImageIO.read(new File("img/pionNoir.png")),Fonctions.pion * i, Fonctions.pion * j,false));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (j == 1)
					i--;
			}

		}

	}
	
	public static String str() {
		String str="";
		for(int i=0;i<EquipeH.equH.size();i++){
			str += "element "+i+" : "+EquipeH.equH.get(i)+"  ";
		}
		str+= "size "+EquipeH.equH.size();
		return str;
	}

}
