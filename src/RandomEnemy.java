
public class RandomEnemy extends Enemy{
	public RandomEnemy(double x, double y, double vx, double vy) {
		super(x,y,vx,vy);
		life=2;
	}
	public void move() {
		super.move();
		vx = Math.random() * (2 + GameWorld.stage * 0.5) - (1 + GameWorld.stage * 0.25);
	}
	public void draw(MyFrame f) {
		f.setColor(0, 255, 0);//緑
		f.fillRect(x+10,y+20,10,10);
		f.fillRect(x,y,10,20);
		f.fillRect(x+20,y,10,20);
	}
}
