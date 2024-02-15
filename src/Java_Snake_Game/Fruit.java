package Java_Snake_Game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Fruit {
	
	private int x;
	private int y;
	private ImageIcon img;
	
	public Fruit() {
		img = new ImageIcon("fruit.png");
		this.x = (int)(Math.floor(Math.random()*Main.colum)*Main.Cell_Size);
		this.y = (int)(Math.floor(Math.random()*Main.row)*Main.Cell_Size);
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void drawFruit(Graphics g) {
//		g.setColor(Color.green);
//		g.fillOval(this.x, this.y, Main.Cell_Size, Main.Cell_Size);
		
		img.paintIcon(null, g, this.x, this.y);
		
	}
	
	public void setNewLocation(Snake s) {
		
		int new_x;
		int new_y;
		boolean overlapping;
		
		do {
			new_x = (int)(Math.floor(Math.random()*Main.colum)*Main.Cell_Size);
			new_y = (int)(Math.floor(Math.random()*Main.row)*Main.Cell_Size);
			overlapping = checkOverlap(new_x,new_y,s);
		}while(overlapping);
		
		this.x = new_x;
		this.y = new_y;
	}
	
	private boolean checkOverlap(int x,int y,Snake s) {
		
		for(int i=0;i<s.getSnakeBody().size();i++) {
			if(x == s.getSnakeBody().get(i).x && y == s.getSnakeBody().get(i).y) {
				return true;
			}
		}
		return false;
	}
}
