package trabalho2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {
	
	private int posX, posY;
	private int qtCapacity;
	private int qtHeight;
	private int qtWidth;
	private boolean isDivided;
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Particle> containedParticles = new ArrayList<Particle>();
	QuadTree NW, NE, SW, SE;

	private int level;
	private int maxLevel;
	
	public QuadTree(int posX, int posY, int qtHeight, int qtWidth, int qtCapacity, int level, int maxLevel) {
		this.posX = posX;
		this.posY = posY;
		this.qtHeight = qtHeight;
		this.qtWidth = qtWidth;
		this.qtCapacity = qtCapacity;
		this.level = level;
		this.maxLevel = maxLevel;
		
		isDivided = false;
		
		if(level == 0) {
			containedParticles = particles;
		}
	}
	
	void Subdivide() {
		NW = new QuadTree(posX, posY, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		NE = new QuadTree(qtWidth / 2, posY, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		SW = new QuadTree(posX, qtHeight /2, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		SE = new QuadTree(qtWidth / 2, qtHeight / 2, qtHeight / 2, qtWidth/ 2, qtCapacity, level + 1, maxLevel - 1);
		isDivided = true;
		System.out.println("Subdividiu para o nível: " + (level + 1));
		
		for(int i = 0; i < particles.size(); i++){
			InsertParticle(particles.get(i));
		}
	}
	
	void Update() {

		if(containedParticles.size() > qtCapacity && isDivided == false && level <= maxLevel) {
			Subdivide();
		} 
		
		if(this.isDivided) {
			System.out.println(this.NE.containedParticles.size());
		}
	}
	
	void InsertParticle(Particle particle){
		if(!isDivided) {
			containedParticles.add(particle);
		} else {
			if(NW.ContainsParticle(particle)) {
				NW.containedParticles.add(particle);
			} 
			else if(NE.ContainsParticle(particle)) {
				NE.containedParticles.add(particle);
			}
			else if(SW.ContainsParticle(particle)) {
				SW.containedParticles.add(particle);
			} else {
				SE.containedParticles.add(particle);
			}
		}
	}
	
	void RemoveParticle(Particle particle) {
		containedParticles.remove(particle);
	}
	
	boolean ContainsParticle(Particle particle) {
		if(particle.posX + particle.radius > posX && particle.posX < posX + qtWidth && 
		   particle.posY + particle.radius > posY && particle.posY < posY + qtHeight) {
			return true;
		} else { return false;}
	}
	
	void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(posX, posY, qtWidth, qtHeight);
		
		if(isDivided) {
			NW.Draw(g);
			NE.Draw(g);
			SW.Draw(g);
			SE.Draw(g);
		}
	}
}
