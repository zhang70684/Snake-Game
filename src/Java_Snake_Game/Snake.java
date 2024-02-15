package Java_Snake_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	
	private ArrayList<Node> snakeBody;
	
	public Snake() {
		snakeBody = new ArrayList();
		snakeBody.add(new Node(80,0));
		snakeBody.add(new Node(60,0));
		snakeBody.add(new Node(40,0));
		snakeBody.add(new Node(20,0));
	}
	
	public ArrayList<Node> getSnakeBody(){
		return snakeBody;
	}
	
	public void drawSnake(Graphics g) {
		
		for(int i = 0;i<snakeBody.size();i++) {
			
			if(i==0) {
				g.setColor(Color.red);
			}else {
				g.setColor(Color.orange);
			}
			
			Node n = snakeBody.get(i);
			
			if(n.x >= Main.width) {
				n.x = 0;
			}
			if(n.x < 0) {
				n.x = Main.width - Main.Cell_Size;
			}
			if(n.y >= Main.height) {
				n.y = 0;
			}
			if(n.y <0) {
				n.y = Main.height - Main.Cell_Size;
			}
			
			g.fillOval(n.x, n.y, Main.Cell_Size, Main.Cell_Size);
		}
	}
}
