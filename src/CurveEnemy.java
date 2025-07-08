
public class CurveEnemy extends Enemy{
	public CurveEnemy(double x, double y, double vx, double vy) {
		super(x,y,vx,vy);
		life=3;
	}
	public void move() {
		super.move();
		double speed = 1 + GameWorld.stage * 0.2;
		if (x < GameWorld.player.x) {
			x += speed;
		}
		if (x > GameWorld.player.x) {
			x -= speed;
		}
	}
	public void draw(MyFrame f) {
		f.setColor(0, 0, 0);//黒
		f.fillOval(x,y,30,30);

		f.setColor(255, 255, 255); //白
		f.fillOval(x+5,y,20,30);
	}

}
