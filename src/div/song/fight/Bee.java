package div.song.fight;

import java.util.Random;

public class Bee extends FlyingObj implements Award {

	protected int xSpeed = 1;
	protected int ySpeed = 2;
	private int awardType;
	
	
	public int getType() {
		return awardType;
	}

	public Bee(){
		this.image = ShootGame.bee;
		this.width = image.getWidth();
		this.height = image.getHeight();
		Random rand = new Random();
		this.x = rand.nextInt(ShootGame.WIDTH - this.width);
		this.y = -this.height;
		this.awardType = rand.nextInt(2);
//		this.awardType = 1;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		if(this.x + this.width >= ShootGame.WIDTH){
			this.xSpeed = -xSpeed;
		}
		if(this.x <= 0){
			this.xSpeed = this.xSpeed;
		}
		this.x += this.xSpeed;
		
		
		
		
		this.y += this.ySpeed;
		
	}

	@Override
	public boolean outOfBound() {
		
		return this.y >= ShootGame.HEIGHT;
	}
}
