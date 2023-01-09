package trabalho2;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {
	
	public int radius;
	public int posX, posY;
	public int speedX, speedY;
	
	public Particle(int iniX, int iniY, int radius, int speedX, int speedY) {
		posX = iniX;
		posY = iniY;
		this.radius = radius;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	void Move() {
		
		if(posX + (radius / 2) >= MainApp.screenWidth || posX - (radius / 2) <= 0) {
			speedX *= -1;
		}
		
		if(posY + (radius / 2) >= MainApp.screenHeight || posY - (radius / 2) <= 0) {
			speedY *= -1;
		}
			
		posX += speedX;
		posY += speedY;
	}
	
	void Draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(posX, posY, radius, radius);
	}
}
