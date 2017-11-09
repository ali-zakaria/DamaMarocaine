package Main;

import java.awt.Image;

public class Img {
	
	private Image img;
	private boolean dayem = false;
	private int X = 0;
	private int Y = 0;
	
	public Img(Image img, int X, int Y, boolean dayem) {
		this.img = img;
		this.X = X;
		this.Y = Y;
		this.dayem = dayem;
	}
	
	public int getX(){
		return this.X;
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public int getY(){
		return this.Y;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public boolean getDame() {
		return dayem;
	}

	public void setDame(boolean dayem) {
		this.dayem = dayem;
	}

	

}
