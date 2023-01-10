package trabalho2;

import java.util.Random;

import javax.swing.JFrame;

public class MainApp {

private static int nParticles = 80;
	
	private static Particle[] particles;
	private static int qtMaxLevel = 4;
	private static int qtMaxCapacity = 2;
	private static QuadTree qtRoot;
	private static int particleRadius = 5;
	public static int screenWidth = 800;
	public static int screenHeight = 800;
	private static boolean running = true;
	private static double delta = 0;
	private static long lastTime = System.nanoTime();
	private static double ns = 1000000000;
	private static double updateTicks = 60.0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		particles = new Particle[nParticles];
		qtRoot = new QuadTree(0, 0, screenWidth, qtMaxCapacity, 0, qtMaxLevel);
		
		for(int i = 0; i < nParticles; i++) {
			Random random = new Random();
			int iniX = random.nextInt(screenWidth - particleRadius);
			int iniY = random.nextInt(screenHeight - particleRadius);
			particles[i] = new Particle(iniX, iniY, particleRadius, 1, 1);
			qtRoot.particles.add(particles[i]);
		}
	
		JFrame frame = new JFrame("Trabalho 2");
		Board board = new Board(particles, qtRoot);
		frame.setSize(screenWidth, screenHeight);
		frame.setTitle("Trabalho 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		//Update
		/*while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns * updateTicks;
			lastTime = now;
			while(delta >= 1) {
				qtRoot.Update();
				
				for(int i = 0; i < particles.length; i++) {
					particles[i].Move();
				}
				
				frame.invalidate();
				frame.repaint();
				delta--;
			}
		}*/
	}
}
