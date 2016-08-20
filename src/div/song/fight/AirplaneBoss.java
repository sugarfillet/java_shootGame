package div.song.fight;

public class AirplaneBoss extends Airplane {

	private int life;
	
	
	
	public AirplaneBoss(){
		this.image  = ShootGame.airplaneboss;

		this.life = 3;
		this.speed = 1;
	}
	
	public int getScore(){
		return 40;
	}
	public void subLife(){
		this.life --;
	}
	public int getLife(){
		return this.life;
	}
}
