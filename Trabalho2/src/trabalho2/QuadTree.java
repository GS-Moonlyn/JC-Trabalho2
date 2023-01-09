package trabalho2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {
	
	private int posX, posY;
	private int qtCapacity;
	private int qtHeight;
	private int qtWidth;
	private boolean isDivided = false;
	public ArrayList particles = new ArrayList<Particle>();
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
	}
	
	void Subdivide() {
		QuadTree NW = new QuadTree(posX, posY, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		QuadTree NE = new QuadTree(qtWidth / 2, posY, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		QuadTree SW = new QuadTree(posX, qtHeight /2, qtHeight / 2, qtWidth / 2, qtCapacity, level + 1, maxLevel - 1);
		QuadTree SE = new QuadTree(qtWidth / 2, qtHeight / 2, qtHeight / 2, qtWidth/ 2, qtCapacity, level + 1, maxLevel - 1);
		isDivided = true;
	}
	
	void Update() {
		if(particles.size() <= qtCapacity && isDivided == false && level <= maxLevel) {
			Subdivide();
		}
	}
	
	void Insert(Particle particle){
		if(!isDivided) {
			particles.add(particle);
		} else {
			
		}
	}
	
	void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(posX, posY, qtWidth, qtHeight);
	}
}
