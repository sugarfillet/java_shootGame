package div.song.fight;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 680;

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage airplaneboss;
	public static BufferedImage beeboss;


	static {
		try {
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			airplaneboss = ImageIO.read(ShootGame.class.getResource("airplane001.png"));
			beeboss = ImageIO.read(ShootGame.class.getResource("bee001.png"));

		} catch (Exception x) {
			x.printStackTrace();
		}

	}

	private Hero hero;
	private FlyingObj[] flyings;
	private Bullet[] bullets;

	public ShootGame() {

		hero = new Hero();
		flyings = new FlyingObj[] {};

		bullets = new Bullet[] { };

	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintHero(g);
		paintFlyingObj(g);
		paintBullet(g);
		paintScore(g);
		paintState(g);
	}

	
	public void paintHero(Graphics g) {

		g.drawImage(this.hero.image, hero.x, hero.y, null);

	}

	public void paintFlyingObj(Graphics g) {
		for (FlyingObj x : this.flyings) {
			g.drawImage(x.image, x.x, x.y, null);
		}
	}

	public void paintBullet(Graphics g) {
		for (Bullet x : this.bullets) {
			g.drawImage(x.image, x.x, x.y, null);
		}
	}

	public void paintState(Graphics g){
		switch(ShootGame.state){
		case ShootGame.START:
			g.drawImage(start, 0, 0, null);
			break;
		case ShootGame.PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case ShootGame.GAMEOVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		
		}
		
	}
	
	public void paintScore(Graphics g){
		g.drawString("LiFe:"+hero.getLife(), 20, 20);
		g.drawString("DoUbLeFiRe:"+hero.getDoubleFire(), 20, 40);
		g.drawString("ScOrE:"+ShootGame.score, 20, 60);
		
	}
	public FlyingObj nextOne() {
		Random rand = new Random();
		int x = rand.nextInt(20);
		if (x  >=0 && x <= 5) {
			return new Bee();
			
		} else if (x <=10){
			return new Airplane();
		}else if( x <= 15){
			return new BeeBoss();
		}else {
			return new AirplaneBoss();
		}
	}

	int enterCount = 0;
	public void enteredAction() {
		enterCount ++;
		if(enterCount % 40 ==0){
			
			FlyingObj one = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = one;
			
		}

	}

	public void stepAciton() {

		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		hero.step();
	}

	int btCount = 0;
	public void shootAction(){
		btCount++;
		if(btCount % 20 == 0){
			Bullet [] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
			
		}
		
	}
	public void outOfBoundsAction(){
		
		FlyingObj [] flyingsLive = new FlyingObj[flyings.length];
		int index1 = 0;
		for(int i = 0 ; i< flyings.length ; i++){
			FlyingObj f = flyings[i];
			if(!f.outOfBound()){
				flyingsLive[index1++] = f ;
			}
		}
		flyings = Arrays.copyOf(flyingsLive, index1);
		
		Bullet[] bulletsLive = new Bullet[bullets.length];
		int index2 = 0;
		for( Bullet x : bullets){
			if(!x.outOfBound()){
				bulletsLive[index2++] = x;
			}
		}
		bullets = Arrays.copyOf(bulletsLive, index2);
		
		
		
	}
	
	public void bangAciton(){
		for(Bullet x : bullets){
			bang(x);
		}
		
	}
	public static int score = 0;
	public void bang(Bullet b){
		for(int i = 0 ;i < flyings.length ; i++){
			FlyingObj f = flyings[i];
			if(f.shootBy(b)){
				if(f instanceof Bee){
					Bee a = (Bee) f;
					int type = a.getType();
					switch(type){
					case Award.DOUBLE_FIRE:
						hero.addDoulbeFire();break;
					case Award.LIFE:
						hero.addLife();break;
						
					}
					if(a instanceof BeeBoss){

					BeeBoss bbb = (BeeBoss) a;
					score += bbb.getScore();
					bbb.subLife();
					if(bbb.getLife()<=0){
						FlyingObj t = f;
						f = flyings[flyings.length-1];
						flyings[flyings.length-1] = t;
						flyings = Arrays.copyOf(flyings, flyings.length-1);
						
					}
					
					
					}else {
						
						FlyingObj t = f;
						f = flyings[flyings.length-1];
						flyings[flyings.length-1] = t;
						flyings = Arrays.copyOf(flyings, flyings.length-1);
						
					}
				}
				
				
				if(f instanceof Airplane){
					Airplane e = (Airplane) f;
					if(e instanceof AirplaneBoss){
						AirplaneBoss aaa = (AirplaneBoss) e;
						score += aaa.getScore();
						aaa.subLife();
						if(aaa.getLife() <= 0 ){
							
							FlyingObj t = f;
							f = flyings[flyings.length-1];
							flyings[flyings.length-1] = t;
							flyings = Arrays.copyOf(flyings, flyings.length-1);
						}
						
					}else{
						
						FlyingObj t = f;
						f = flyings[flyings.length-1];
						flyings[flyings.length-1] = t;
						flyings = Arrays.copyOf(flyings, flyings.length-1);
						
					}
					
					
					
				} 
			
					
					
				
			
				Bullet bb = b;
				b = bullets[bullets.length-1];
				bullets[bullets.length-1] = bb;
				bullets = Arrays.copyOf(bullets, bullets.length-1);
				
				
			}
		}
		
		
		
	}
	
	
	public void checkGameOverAciton(){
		if(isGameOver()){
			state = GAMEOVER;
		}
	}
	
	
	public boolean isGameOver(){
		
		for(int i = 0 ;i < flyings.length ; i++){
			FlyingObj f = flyings[i];
			if(hero.hit(f) && f instanceof Enemy){
				
				hero.subscribeLife();
				
				hero.clearDoubleFire();
				
				FlyingObj t = f;
				f = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length-1);
			}
			
		}
		
		return hero.getLife() <= 0;
		
		
	}
	
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	
	public static int state = 0;
	
	public void action() {
		
		MouseAdapter l = new MouseAdapter() {
		
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING){
					hero.moveTo(e.getX(), e.getY());
					
				}
			}
			
			public void mouseClicked(MouseEvent e){
				if(state == START){
					state = RUNNING;
				}
				if(state == GAMEOVER){
					hero = new Hero();
					flyings = new FlyingObj[]{};
					bullets = new Bullet[]{};
					state = START;
					score = 0;
				}
			}
			
			
			public void mouseExited(MouseEvent e){
				if(state == RUNNING){
					state = PAUSE;
				}
			}
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			
			
			
			
		};
		this.addMouseMotionListener(l);
		this.addMouseListener(l);
		
		
		
		Timer time = new Timer();
		int intervel = 10;
		time.schedule(new TimerTask() {

			@Override
			public void run() {

				if(state == RUNNING){
					
					enteredAction();
					stepAciton();
					shootAction();
					outOfBoundsAction();
					bangAciton();
					checkGameOverAciton();
				}
				repaint();

			}
		}, intervel, intervel);

	}

	public static void main(String args[]) {

		JFrame frame = new JFrame("SHOOTGAME");
		ShootGame game = new ShootGame();
		frame.add(game);

		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.action();

	}

}
