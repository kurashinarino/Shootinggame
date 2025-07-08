import java.util.Vector;

public class GameFrame extends MyFrame{
public void run() {
	GameWorld.player=new Player(100,300,0,0);
	addKeyListener(GameWorld.player);
	GameWorld.stage=1;
	GameWorld.score=0;
    while(true) {
	    GameWorld.player.x=100;
        GameWorld.player.y=300;
		GameWorld.playerBullets=new Vector<PlayerBullet>();
		GameWorld.enemies=new Vector<Enemy>();
		// ステージに応じて敵を追加（例として3体追加）
		for (int i = 0; i < GameWorld.stage + 2; i++) {
			int x = 50 + i * 50;
			int y = 50;

			// ステージと i に応じて種類を切り替える（パターンを出す）
			if (i % 3 == 0) {
				GameWorld.enemies.add(createRandomEnemy(x, y));
			} else if (i % 3 == 1) {
				GameWorld.enemies.add(createCurveEnemy(x, y));
			} else {
				GameWorld.enemies.add(createDropEnemy(x, y));
			}
		}
		GameWorld.enterPressed=false;
		while(true) {
			clear();
			drawString("Stage = "+GameWorld.stage,300,50,15);
			drawString("Score = "+GameWorld.score,300,80,15);
			GameWorld.player.draw(this);
			GameWorld.player.move();
			movePlayerBullets();
			moveEnemies();
			checkPlayerAndEnemies();
			checkPlayerBulletsAndEnemies();
			if(GameWorld.enemies.size()==0) {//★A敵が全滅した？
			   setColor(0,0,0);
			   drawString("クリア！",100,200,40);
			   if(GameWorld.enterPressed) {//★C Enterキーが押された？
				  GameWorld.stage++;
				  break;//★D
			   }
			}else if(GameWorld.player.y<0) {//★Bプレイヤーが消えた？
				setColor(0,0,0);
				drawString("ゲームオーバー！",50,200,40);
				if(GameWorld.enterPressed) {//★E Enterキーが押された？
				   GameWorld.stage=1;
				   GameWorld.score=0;
				   break;//★F
				}
			}
			sleep(0.03);
		}
	}
}
	public void movePlayerBullets() {
		int i=0;
		while(i<GameWorld.playerBullets.size()) {
			PlayerBullet b=GameWorld.playerBullets.get(i);
			b.draw(this);
			b.move();
			if(b.y<0) {
				GameWorld.playerBullets.remove(i);
			}else {
				i++;
			}
		}
	}
    public void checkPlayerAndEnemies() {
		for(int i=0;i<GameWorld.enemies.size();i++) {
			Enemy e=GameWorld.enemies.get(i);
			if(checkHit(GameWorld.player,e)) {
			   System.out.println("やられた！");
			   GameWorld.player.y=-1000;
			}
		}
    }
	public void moveEnemies() {
	    for(int i=0;i<GameWorld.enemies.size();i++) {
	    	Enemy e=GameWorld.enemies.get(i);
	    	e.draw(this);
			e.move();
	    }
	    int i=0;
	    while (i<GameWorld.enemies.size()) {
	    	Enemy e=GameWorld.enemies.get(i);
	    	if(e.y>400) {
	    		GameWorld.enemies.remove(i);
	    	}else {
	    		i++;
	    	}
	    }
    }
	public void checkPlayerBulletsAndEnemies() {
		int i=0;
		while(i<GameWorld.playerBullets.size()) {
			//プレイヤー弾一つ一つについて、変数bに入れて繰り返し実行する
			PlayerBullet b=GameWorld.playerBullets.get(i);
			int j=0;
			int hits=0;
			while(j<GameWorld.enemies.size()) {
				//敵一つ一つについて、変数aに入れて繰り返し実行する
				Enemy e=GameWorld.enemies.get(j);
				if(checkHit(e,b)) {
				   System.out.println("あたり");
				   hits++;
				   e.life--;
				}
				if(e.life<=0) {
					GameWorld.score+=e.score;
					GameWorld.enemies.remove(j);
				}else {
					j++;
				}
			}
			if(hits>0) {
				GameWorld.playerBullets.remove(i);
			}else {
				i++;
			}
		}
	}
	public boolean checkHit(Character a,Character b) {//★A
		if (Math.abs(a.x - b.x) <= 15 && Math.abs(a.y - b.y) <= 10) {
			return true;
		} else {
			return false;
		}
	}
	public RandomEnemy createRandomEnemy(int x, int y) {
		RandomEnemy e = new RandomEnemy(x, y, 0, 1);
		e.life = 2 + GameWorld.stage / 2; // ステージで耐久力UP
		return e;
	}

	public CurveEnemy createCurveEnemy(int x, int y) {
		CurveEnemy e = new CurveEnemy(x, y, 0, 1);
		e.life = 3 + GameWorld.stage / 2;
		return e;
	}

	public DropEnemy createDropEnemy(int x, int y) {
		DropEnemy e = new DropEnemy(x, y, 0, 0.5);
		e.life = 1 + GameWorld.stage / 2;
		return e;
	}

}

