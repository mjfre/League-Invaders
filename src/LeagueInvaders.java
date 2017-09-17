import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame; 
	GamePanel Panel;
	
	static final int WIDTH = 500;
	static final int HEIGHT = 800; 
	
	public LeagueInvaders() {
		frame = new JFrame();
		Panel = new GamePanel();
		setup();
	}
	
	public void setup() {
		frame.add(Panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.addKeyListener(Panel);
		Panel.startGame();
	}
	public static void main(String[] args) {
		LeagueInvaders myLeagueInvader = new LeagueInvaders();
	}
}
