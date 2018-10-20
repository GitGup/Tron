package tron;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Tron implements ActionListener, KeyListener {

	public JFrame frame;
	public Dimension dim;
	public static Tron tron;
	public Render render;
	public Timer timer = new Timer(20, this);
	
	public boolean gameover = false;
	
	public ArrayList<Point> lightBlocks = new ArrayList<Point>();
	
	public Point head;
	
	public int ticks = 0, direction = DOWN, traillength;
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 15;
	
	public static void main(String[] args) {
		tron = new Tron();

	}
	
	public Tron() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("BM Tron");
		frame.setVisible(true);
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(dim.width/4, dim.height/12);
		frame.add(render = new Render());
		frame.addKeyListener(this);
		head = new Point(0,0);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		render.repaint();
		ticks++;
		
		if(ticks % 5 == 0 && head != null && !gameover && !collided(0, 10)) {
			if(direction == UP) {
				if(head.y - 1 > 0) {
					head = new Point(head.x, head.y - 1);
				} else {
					gameover = true;
				}
			}
			if(direction == DOWN) {
				if(head.y + 1 < dim.height / SCALE) {
					head = new Point(head.x, head.y + 1);
				} else {
					gameover = true;
				}
			}
			if(direction == LEFT) {
				if(head.x - 1 > 0) {
					head = new Point(head.x - 1, head.y);
				} else {
					gameover = true;
				}
			}
			if(direction == RIGHT) {
				if(head.x + 1 < dim.width / SCALE) {
					head = new Point(head.x + 1, head.y);
				} else {
					gameover = true;
				}
			}
			lightBlocks.add(new Point(head.x, head.y));
			//lightBlocks.remove(0);
			traillength++;
		}
	}
	
	//FIX THIS ====================================
	public boolean collided(int x, int y) {
		
		for (Point point : lightBlocks) {
			System.out.println("Point: " + point);
			System.out.println("h loc: " + head.getLocation());
			if (point == head.getLocation()) {
				System.out.println("point = head location!");
				return true;
			}
		}
		
		for(int i = 0; i < traillength; i++) {
			if(head.equals(new Point(x, y))) {
				System.out.println("collided at " + head.getX() + " " +  head.getY());
				return true;
			}
		} 
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W && direction != DOWN) {
			direction = UP;
		}
		if (e.getKeyCode() == KeyEvent.VK_A && direction != RIGHT) {
			direction = LEFT;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && direction != UP) {
			direction = DOWN;
		}
		if (e.getKeyCode() == KeyEvent.VK_D && direction != LEFT) {
			direction = RIGHT;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


