package trabalho2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	private Particle[] particles;
	private QuadTree qt;
	
	public Board(Particle[] particles, QuadTree qt) {
		this.particles = particles;
		this.qt = qt;
	}
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(MainApp.screenWidth, MainApp.screenHeight);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBorder(BorderFactory.createLineBorder((Color.BLACK), 2));
		qt.Draw(g);
		
		for(int i = 0; i < particles.length; i++) {
			particles[i].Draw(g);
		}	
	}
}
