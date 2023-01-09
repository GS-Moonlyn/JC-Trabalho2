package trabalho2;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {
	
	private Particle[] particles;
	private QuadTree qt;
	
	public Board(Particle[] particles, QuadTree qt) {
		this.particles = particles;
		this.qt = qt;
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		qt.Draw(g);
		
		for(int i = 0; i < particles.length; i++) {
			particles[i].Draw(g);
		}	
	}
}
