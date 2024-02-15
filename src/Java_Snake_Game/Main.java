package Java_Snake_Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JPanel implements KeyListener{
	
	public static final int Cell_Size = 20;
	public static int width = 400;
	public static int height = 400;
	public static int row = height / Cell_Size;
	public static int colum = width / Cell_Size;
	private Snake snake;
	private Fruit fruit;
	private Timer t;
	private int speed = 100;
	private static String direction;
	private boolean allowKeyPress;
	private int score = 0;
	
	public Main() {
		
		reset();
		
		addKeyListener(this);
		
	}
	
	private void setTimer() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					repaint();
				}
			},0,speed
		);
	}
	
	private void reset() {
		score = 0;
		if(snake != null) {
			snake.getSnakeBody().clear();
		}
		allowKeyPress = true;
		direction = "Right";
		snake = new Snake();
		fruit = new Fruit();
		setTimer();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		//check if the snake bites itself
		ArrayList<Node> snake_body = snake.getSnakeBody();
		Node head = snake.getSnakeBody().get(0);
		for(int i=1;i<snake_body.size();i++) {
			if(snake_body.get(i).x == head.x && snake_body.get(i).y == head.y) {
				allowKeyPress = false;
				t.cancel();
				t.purge();
				int response = JOptionPane.showOptionDialog(this, "GameOver!!! Score is " + score+ " Restart?", "GameOver", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE , null,null,JOptionPane.YES_OPTION);
				
				switch(response) {
					case JOptionPane.CLOSED_OPTION:
						System.exit(0);
						break;
					case JOptionPane.NO_OPTION:
						System.exit(0);
						break;
					case JOptionPane.YES_OPTION:
						reset();
						return;
				}
			}
		}
		
		//draw background
		g.fillRect(0, 0, width, height);
		fruit.drawFruit(g);
		snake.drawSnake(g);
		
		//remove snake tail and put in the head
		int snakeX = snake.getSnakeBody().get(0).x;
		int snakeY = snake.getSnakeBody().get(0).y;//0 是頭所在的位置
		//right = x += Cell_Size
		//left = x -= Cell_Size
		//down y += Cell_Size
		//up = y -= Cell_Size
		if(direction.equals("Right")) {
			snakeX += Cell_Size;
		}else if(direction.equals("Left")) {
			snakeX -= Cell_Size;
		}else if(direction.equals("Down")) {
			snakeY += Cell_Size;
		}else if(direction.equals("Up")) {
			snakeY -= Cell_Size;
		}
		Node newHead = new Node(snakeX,snakeY);
		
		//check if the snake eats the fruit
		if(snake.getSnakeBody().get(0).x==fruit.getX() && snake.getSnakeBody().get(0).y == fruit.getY()) {
			
			//1.set fruit to new location
			fruit.setNewLocation(snake);
			//2.draw Fruit
			fruit.drawFruit(g);
			//3.score++
			score++;
			
		}else {
			//取下Snake ArrayList的最後一個
			snake.getSnakeBody().remove(snake.getSnakeBody().size() - 1);
		}
		
		
		//加入新座標
		snake.getSnakeBody().add(0,newHead);
		
		allowKeyPress = true;
		requestFocusInWindow();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("Snake Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new Main());
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getKeyCode());
		
		if(allowKeyPress == true) {
			if(e.getKeyCode() == 68 && !direction.equals("Left")) {
				direction = "Right";
			}else if(e.getKeyCode() == 65 && !direction.equals("Right")) {
				direction = "Left";
			}else if(e.getKeyCode() ==  87 && !direction.equals("Down")) {
				direction = "Up";
			}else if(e.getKeyCode() == 83 && !direction.equals("Up")) {
				direction = "Down";
			}
			
			allowKeyPress = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
