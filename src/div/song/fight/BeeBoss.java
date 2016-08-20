package div.song.fight;

public class BeeBoss extends Bee {

	private int life;

	public BeeBoss(){
		this.image = ShootGame.beeboss;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.life = 5;
		this.xSpeed = 1;
		this.ySpeed = 1;
		
	}

	public int getScore(){
		return 60;
	}
	public void subLife(){
		this.life --;
	}
	public int getLife(){
		return this.life;
	}

}
