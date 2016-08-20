package div.song.fight;

import java.awt.image.BufferedImage;

public abstract class FlyingObj {

	protected BufferedImage image ;
	protected int width;
	protected int height;
	protected int x;
	protected int y;

	
	public abstract void step();
	public abstract boolean outOfBound();
	public boolean shootBy( Bullet b){
		int x1 = this.x;
		int x2 = this.x + this.width;
		int y1 = this.y;
		int y2 = this.y + this.height;
		return b.x >= x1 && b.x <= x2 && b.y >= y1 && b.y <= y2;
	}
	

	
	
}
