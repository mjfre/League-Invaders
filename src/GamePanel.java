import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	GameObject GameObject;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font subtitleFont;
	Font gameOverFont;
	Font scoreFont;
	Rocketship rocketship = new Rocketship(250,700,50,50);
	ObjectManager objectManager = new ObjectManager();
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spaceImg;

	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial",Font.BOLD,38);
		subtitleFont = new Font("Arial",Font.PLAIN,28);
		gameOverFont = new Font("Arial",Font.BOLD,44);
		scoreFont = new Font("Arial",Font.BOLD,18);
		objectManager.addObject(rocketship); 

		//add images 
		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spaceImg =  ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//GameObject = new GameObject();
	}
	
	void startGame() {
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//GameObject.update();
		if(currentState == MENU_STATE){
			updateMenuState();
		}
		else if(currentState == GAME_STATE){
			updateGameState();
		}
		else if(currentState == END_STATE){
			updateEndState();
		}

		repaint();
	}
	
	public void paintComponent(Graphics g){
		//GameObject.draw(g);
		if(currentState == MENU_STATE){
			drawMenuState(g);
		}else if(currentState == GAME_STATE){
			drawGameState(g);
		}else if(currentState == END_STATE){
			drawEndState(g);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keyTyped");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keyPressed");
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			currentState=currentState+1;
			if(currentState > END_STATE){
				currentState = MENU_STATE;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			objectManager.addObject(new Projectile(rocketship.x+20, rocketship.y, 10, 10));
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			rocketship.y -= rocketship.speed; 
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocketship.y += rocketship.speed;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocketship.x -= rocketship.speed;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocketship.x += rocketship.speed;
		}
		


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keyReleased");
	}
	
	void updateMenuState() {
		
	}

	void updateGameState() {
		objectManager.update();
		objectManager.manageEnemies();
		objectManager.checkCollision();
		if(rocketship.isAlive == false) {
			currentState = END_STATE;
			objectManager.reset();
			rocketship = new Rocketship(250,700,50,50);
			objectManager.addObject(rocketship);
		}
		objectManager.getScore();
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT); 
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("LEAGUE INVADERS", 55, 200);
		g.setColor(Color.BLACK);
		g.setFont(subtitleFont);
		g.drawString("Press Enter to Start", 110, 250);
		objectManager.setScore(0);
		
	}
	
	void drawGameState(Graphics g){
		/*
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT); 
		*/
		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		objectManager.draw(g);
		g.setColor(Color.YELLOW);
		g.setFont(scoreFont);
		g.drawString("Score: "+objectManager.getScore(), 10, 20);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);   
		g.setColor(Color.BLACK);
		g.setFont(gameOverFont);
		g.drawString("GAME OVER", 110, 250);
		g.setColor(Color.BLACK);
		g.setFont(scoreFont);
		g.drawString("Final Score: "+objectManager.getScore(), 190, 270);
		
	}
	
}
