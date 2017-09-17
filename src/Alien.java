import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	int speed;
	int startingX;
	boolean movingRight = true;
	int maxX;		//holds the max x-position for each alien
	int minX;		//holds the min x-position for each alien 
	Alien(int x, int y, int width, int height){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
		this.speed = 5;  
		this.maxX = x+20;
		this.minX = x-20;
	}
	
	void update() {
		super.update();
		if(this.movingRight == true && this.x==maxX) {
			this.movingRight = false;
		}
		else if(movingRight==true){
			y+=1;
			x+=1;
		}
		else if(this.movingRight == false && this.x==minX) {
			movingRight = true;
		}
		else {
			y+=1;
			x-=1;
		}
		
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.alienImg, x, y, width, height, null);
	}
}
