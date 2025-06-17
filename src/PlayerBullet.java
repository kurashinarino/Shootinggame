
public class PlayerBullet extends Character{
	public void draw(MyFrame f) {
		f.setColor(190, 162, 202);
		f.fillRect(x+10, y, 10, 30);
	}
	public PlayerBullet(double x, double y,
			            double vx, double vy) {
		//Characterクラスのコンストラクタ呼び出し(8章)
		super(x,y,vx,vy);
	}
}
