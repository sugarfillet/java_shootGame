package div.song.fight;

import java.util.Random;

public class Airplane extends FlyingObj implements Enemy {

	protected int speed = 2;
	public int getScore() {
		return 5;
	}
	public Airplane() {

		this.image = ShootGame.airplane;
		this.width = image.getWidth();
		this.height = image.getHeight();
		Random rand = new Random();
		this.x = rand.nextInt(ShootGame.WIDTH - this.width);
		this.y = -this.height;
	}
	public void step() {
		this.y += this.speed;
	}
	@Override
	public boolean outOfBound() {
		
		
		return this.y >= ShootGame.HEIGHT;
	}

	
	
}
