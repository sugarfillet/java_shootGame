package div.song.fight;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Hero extends FlyingObj {

	private int life;
	private int doubleFire;
	private BufferedImage[] images;
	private int index;
	public Hero() {

		this.image = ShootGame.hero0;
		this.width = image.getWidth();
		this.height = image.getHeight();
	
		this.x =( ShootGame.WIDTH - this.width)/2;
		this.y = 400;
		
		this.life = 3;
		this.doubleFire = 20;
		this.images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		this.index = 0;
		
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		this.image = this.images[new Random().nextInt(2)];
	}
	
	public Bullet[] shoot(){
		int dx = this.width/4;
		
		if(doubleFire <= 0){
			
			return new Bullet[]{new Bullet(this.x + 2*dx,this.y)};
			
		}else{
			doubleFire --;
			return new Bullet[]{new Bullet(this.x + 1*dx,this.y),new Bullet(this.x + 3*dx,this.y)};
		}
		
		
	}
	
	public void moveTo(int x ,int y){
		this.x = x-this.width/2;
		this.y = y-this.height/2;
		
	}
	@Override
	public boolean outOfBound() {
		return false;
	}
	
	public void addLife(){
		this.life++;
//		System.out.println("mylifeis "+this.life);
	}
	public void addDoulbeFire(){

		this.doubleFire = 20;;
//		System.out.println(doubleFire);
	}
	
	public void subscribeLife(){
		this.life -- ;
//		System.out.println(this.life);
	}
	public void clearDoubleFire(){
	   this.doubleFire = 0;
	
	}
	public boolean hit(FlyingObj other){

		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.width;
		int y2 = this.y + this.height;
		return other.x >= x1 && other.x <=x2&&
				other.y  >=y1 && other.y <= y2;
				
	}
	
	
	public int getLife(){
		return this.life;
	}
	
	public int getDoubleFire(){
		return this.doubleFire;
	}
	
	
	
	
	
}
