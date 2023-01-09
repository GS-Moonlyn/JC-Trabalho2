package trabalho2;

import java.util.Random;

import javax.swing.JFrame;

public class MainApp {

private static int nParticles = 10;
	
	private static Particle[] particles;
	private static int qtMaxLevel = 3;
	private static int qtMaxCapacity = 2;
	private static QuadTree qtRoot;
	private static int particleRadius = 10;
	public static int screenWidth = 800;
	public static int screenHeight = 800;
	private static boolean running = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		particles = new Particle[nParticles];
		qtRoot = new QuadTree(1, 1, screenWidth - 5, screenHeight - 5, qtMaxCapacity, 0, qtMaxLevel);
		
		for(int i = 0; i < nParticles; i++) {
			Random random = new Random();
			int iniX = random.nextInt(screenWidth - particleRadius);
			int iniY = random.nextInt(screenHeight - particleRadius);
			particles[i] = new Particle(iniX, iniY, particleRadius, 10, 10);
		}
		
		JFrame frame = new JFrame("Trabalho 2");
		Board board = new Board(particles, qtRoot);
		frame.setSize(screenWidth, screenHeight);
		frame.setTitle("Trabalho 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.setResizable(false);
		frame.setVisible(true);
		
		while(running) {
			qtRoot.Update();
			
			for(int i = 0; i < particles.length; i++) {
				particles[i].Move();
			}
		}
	}
}
