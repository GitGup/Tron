package tron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Render extends JPanel {

	public int columns, rows;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 800, 700);
		
		Tron tron = Tron.tron;
		
		g.setColor(new Color(255, 0, 0));
		for(Point point : tron.lightBlocks) {
			g.fillRect(point.x * Tron.SCALE, point.y * Tron.SCALE, Tron.SCALE, Tron.SCALE);			
			
		}
		g.fillRect(tron.head.x * Tron.SCALE, tron.head.y * Tron.SCALE, Tron.SCALE, Tron.SCALE);	
		
		columns = 700/Tron.SCALE;
		rows = 800/Tron.SCALE;

		
		g.setColor(new Color(100, 100, 100));
		for (int y = 0; y < columns; y++) {
			for(int x = 0; x < rows; x++)  {
				g.drawRect(x * Tron.SCALE, y * Tron.SCALE, Tron.SCALE, Tron.SCALE);
			}
		}
		
	}
	
	
}