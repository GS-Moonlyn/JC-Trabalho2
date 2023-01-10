package trabalho2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {
	
	private int posX, posY;
	private int qtCapacity;
	private int qtSize;
	private boolean isDivided;
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Particle> containedParticles = new ArrayList<Particle>();
	QuadTree NW, NE, SW, SE;

	private int level;
	private int maxLevel;
	
	public QuadTree(int posX, int posY, int qtSize, int qtCapacity, int level, int maxLevel) {
		this.posX = posX;
		this.posY = posY;
		this.qtSize = qtSize;
		this.qtCapacity = qtCapacity;
		this.level = level;
		this.maxLevel = maxLevel;
		
		isDivided = false;
		
		if(level == 0) {
			containedParticles = particles;
		}
	}
	
	void Subdivide(ArrayList<Particle> particles) {
		NW = new QuadTree(posX, posY, qtSize / 2, qtCapacity, level + 1, maxLevel);
		NE = new QuadTree(posX + qtSize /2, posY, qtSize / 2, qtCapacity, level + 1, maxLevel);
		SW = new QuadTree(posX, posY + qtSize / 2, qtSize / 2, qtCapacity, level + 1, maxLevel);
		SE = new QuadTree(posX + qtSize / 2, posY + qtSize / 2, qtSize / 2, qtCapacity, level + 1, maxLevel);
		isDivided = true;
		System.out.println("Subdividiu para o nível: " + (level + 1));
		
		for(Particle p : particles) {
			if(NW.ContainsParticle(p)) {
				NW.containedParticles.add(p);
			}
			else if(NE.ContainsParticle(p)) {
				NE.containedParticles.add(p);
			}
			else if(SW.ContainsParticle(p)) {
				SW.containedParticles.add(p);
			} else {
				SE.containedParticles.add(p);
			}
		}

	}
	
	void Update() {

		if(containedParticles.size() > qtCapacity && isDivided == false && level < maxLevel) {
			Subdivide(containedParticles);
		} 
		
		if(isDivided) {NW.Update(); NE.Update(); SW.Update(); SE.Update();}
	
		
		System.out.println("Level:" + level + " Particles: " + containedParticles.size());
	}
	
	void InsertParticle(ArrayList<Particle> particles){
		if(particles.size() < qtCapacity) {
			containedParticles = particles;
		} else {
			if(!isDivided) {
				Subdivide(containedParticles);
			}
		}		
	}
	
	void RemoveParticle(Particle particle) {
		containedParticles.remove(particle);
	}
	
	boolean ContainsParticle(Particle particle) {
		if(particle.posX + particle.radius > posX && particle.posX < posX + qtSize && 
		   particle.posY + particle.radius > posY && particle.posY < posY + qtSize) {
			return true;
		} else { return false;}
	}
	
	void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(posX, posY, qtSize, qtSize);
		
		if(isDivided) {
			NW.Draw(g);
			NE.Draw(g);
			SW.Draw(g);
			SE.Draw(g);
		}
	}
}
