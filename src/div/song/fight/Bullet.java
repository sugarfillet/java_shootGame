package div.song.fight;

import java.util.Random;

public class Bullet extends FlyingObj {

	private int speed = 3;

	public Bullet(int x ,int y) {

		this.image = ShootGame.bullet;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.x  = x;
		this.y = y;
	
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		this.y -=this.speed;
	}

	@Override
	public boolean outOfBound() {
		// TODO Auto-generated method stub
		return this.y <= 0 ;
	}
	
}
