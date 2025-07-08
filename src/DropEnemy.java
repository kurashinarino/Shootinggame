
public class DropEnemy extends Enemy{
	public DropEnemy(double x, double y, double vx, double vy) {
		super(x,y,vx,vy);
		life=1;
	}
	public void move() {
		super.move();
		vy += 0.1 + GameWorld.stage * 0.05; // 加速度が増える
	}
	public void draw(MyFrame f) {
		f.setColor(0, 255, 0);//緑
		f.fillRect(x,y,30,10);
		f.fillRect(x+10,y+10,10,20);
	}
}
