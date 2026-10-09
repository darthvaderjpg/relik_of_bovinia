/*
Author: Jake Jiang -- Subscribe to Scrapnel on YT!
Date  : 05/19/26
Notes : This is the boss fight class. 
		Behavior:
			Stage 1: Avoid player, spawn enemies. After all enemies die, bovis is hittable for some time
			Stage 2: 
 */
public class BovisFight extends Enemy {
	private final static int MINION_SPAWN_TIME_S3 = 67;
	private int stage = 0;
	private int speed = 1;
	private int tokenS2 = -1;
	private int minionSpawnS3 = 500;
	private int downTimer = 167;
	private static boolean bossToken = false; //static so that player does not have to redo dialogue to fight bovis
	
	
	//enemies spawned by bovis
	//stage 1 
	public Infarctus infarctus01 = new Infarctus(650, 200, 8); //above bovis
	public Infarctus infarctus02 = new Infarctus(500, 400, 8); //left of bovis
	public Infarctus infarctus03 = new Infarctus(650, 600, 8); //below bovis
	//stage 2 (and some 3 leftovers)
	public Knight knight01 = new Knight(200,230,8); //top leftish
	public Knight knight02 = new Knight(700,230,8); //top rightish
	public Knight knight03 = new Knight(200,700,8); //bottom leftish
	public Knight knight04 = new Knight(700,700,8); //bottom rightish
	//stage 3
	public Infarctus infarctus04 = new Infarctus(super.getX(), super.getY(), 8); 
	public Infarctus infarctus05 = new Infarctus(super.getX(), super.getY(), 8);
	public Infarctus infarctus06 = new Infarctus(super.getX(), super.getY(), 8); 
	public Infarctus infarctus07 = new Infarctus(super.getX(), super.getY(), 8); 
	public Infarctus infarctus08 = new Infarctus(super.getX(), super.getY(), 8); 

	//Constructor: 
	//As of now, spawns at (800,400) at room 8
	public BovisFight(int x, int y, int roomID) {
		super(x, y, 8, "bovisfight");
	}
	
	//Methods Below
	
	//Starts the boss accordingly.
	public static void startBoss() {
		bossToken = true;
		Map.enableBossMusic();
		System.out.println(bossToken);
	}
	
	//Behaves in stages. Loops. Refer to methods named "runStage#" to discover behavior
	public void trackPlayer(int px, int py, int pRoomID) {
		if(super.getTargetAvailability(pRoomID) && super.getMoveToken() && bossToken) {
			
			super.release();
			super.proximityHit(px, py);
			super.checkIfAttacked();
			holdMinions();
//			System.out.println("stage = " + stage);
			//This switch statement chronologically decides bovis' behavior during the game
			switch (stage) {
			//intro (walks in)
			case 0:
				runStage0();
			break;
			case 1:
				runStage1(px,py);
			break;
			case 2:
				runStage2(px,py);
			break;
			case 3:
				runStage3(px,py);
			break;
			case 4: 
				runStage4(px,py);
			break;
			case 5:
				runStage5();
			break;
			case 6:
				runStage6();
				stage = 1;
			}	
//		System.out.println("BOVIS HP: " + super.getHealth());
		}	else {
			super.hold();
			holdMinions();
		}
		
	} 
	
	//first stage, simply moves Bovis into the arena
	public void runStage0() {
		if(super.getX() != 600) {
			super.setSpeed(1);
			super.moveLeft();
		} else {
			stage = 1;
		}
	}
	
	//spawns 3 infarctus around bovis to attack the player. Stands still.
	public void runStage1(int px, int py) {
//		System.out.println("Stage 1!");
		infarctus01.release();
		infarctus02.release();
		infarctus03.release();
		
		infarctus01.trackPlayer(px, py, 8);
		infarctus02.trackPlayer(px, py, 8);
		infarctus03.trackPlayer(px, py, 8);
		
		if(infarctus01.getEnemyType().equals("dead") && infarctus02.getEnemyType().equals("dead") && infarctus03.getEnemyType().equals("dead")) {
			stage = 2;
		}
	}
	
	//Dashes forward, then walkes back. Knights will also spawn and dash at the player.
	public void runStage2(int px, int py) {
		if(tokenS2 == -1) {
			knight01.setCoordinates(200, 230);
			knight02.setCoordinates(700, 230);
			knight03.setCoordinates(200, 700);
			knight04.setCoordinates(700, 700);
			tokenS2 = 0;
		}
		if(super.getX() > 75 && tokenS2 == 0) {
			moveLeft();
			setSpeed(speed);
			speed++;
			
		} else if(super.getX() <= 75) tokenS2 = 1;
		
		if(super.getX() < 650 && tokenS2 == 1) {
			super.setSpeed(2);
			moveRight();
		}
		
		if(knight01.getEnemyType().equals("dead") && knight02.getEnemyType().equals("dead") && knight03.getEnemyType().equals("dead") && knight04.getEnemyType().equals("dead")) {
			stage = 3;
		}
		
		knight01.release();
		knight02.release();
		knight03.release();
		knight04.release();
		
		knight01.setDashTime(0);
		knight02.setDashTime(-15);
		knight03.setDashTime(-7);
		knight04.setDashTime(-24);
		
		knight01.trackPlayer(px, py, 8);
		knight02.trackPlayer(px, py, 8);
		knight03.trackPlayer(px, py, 8);
		knight04.trackPlayer(px, py, 8);
	}
	
	//chases player periodically spawning infarcti
	public void runStage3(int px, int py) {
		if(stage == 3) {
			setSpeed(5);
			minionSpawnS3--;

			moveRadians360(px,py);
			if(minionSpawnS3 <= 400) {
				infarctus04.release();
				infarctus04.trackPlayer(px, py, 8);
				if(minionSpawnS3 == 400) infarctus04.setCoordinates(super.getX(), super.getY());
			}
			if(minionSpawnS3 <= 300) {
				infarctus05.release();
				infarctus05.trackPlayer(px, py, 8);
				if(minionSpawnS3 == 300) infarctus05.setCoordinates(super.getX(), super.getY());
			}
			if(minionSpawnS3 <= 200) {
				infarctus06.release();
				infarctus06.trackPlayer(px, py, 8);
				if(minionSpawnS3 == 200) infarctus06.setCoordinates(super.getX(), super.getY());
			}
			if(minionSpawnS3 <= 100) {
				infarctus07.release();
				infarctus07.trackPlayer(px, py, 8);
				if(minionSpawnS3 == 100) infarctus07.setCoordinates(super.getX(), super.getY());
			}
			if(minionSpawnS3 <= 0) {
				infarctus08.release();
				infarctus08.trackPlayer(px, py, 8);
				if(minionSpawnS3 == 0) infarctus08.setCoordinates(super.getX(), super.getY());
			}
			if(minionSpawnS3 <= -100) {
				stage = 4;
			}
			
		}
		
//		System.out.println("4: " + infarctus04.getX() + " -- 5: " + infarctus05.getX());

		knight01.release();
		knight02.release();
		knight03.release();
		knight04.release();
		
		knight01.trackPlayer(px, py, 8);
		knight02.trackPlayer(px, py, 8);
		knight03.trackPlayer(px, py, 8);
		knight04.trackPlayer(px, py, 8);
		
//		System.out.println("minionSpawnS3: " + minionSpawnS3);
	}
	
	//moves to middle of the screen and doesnt do anything (DOWN)
	public void runStage4(int px, int py) {
		super.setSpeed(1);
		int midX = 323, midY = 387;
		if(stage == 4) moveRadians360(midX, midY);
		downTimer--;
		if(getX() == midX && getY() == midY && downTimer > 0) {
			downTimer--;
			super.setSpeed(0);
//			System.out.println("DOWN!");
		}
		if(downTimer <= 0) {
			downTimer--;
			super.allowMove();
			speed = 2;
			setSpeed(speed);
			stage = 5;
		}
//		System.out.println("downTimer: " + downTimer);
		infarctus04.release();
		infarctus04.trackPlayer(px, py, 8);
		infarctus05.release();
		infarctus05.trackPlayer(px, py, 8);
		infarctus06.release();
		infarctus06.trackPlayer(px, py, 8);
		infarctus07.release();
		infarctus07.trackPlayer(px, py, 8);
		infarctus08.release();
		infarctus08.trackPlayer(px, py, 8);
	}
	
	//moves back to starting position
	public void runStage5() {
		if(stage == 5) {
			if(getY() < 400) {
				setSpeed(1);
				super.moveDown();
			} else if(getY() > 400) {
				setSpeed(1);
				super.moveUp();
			} else if(getX() <= 600) {
				speed++;
				setSpeed(speed);
				moveRight();
			} else if(getX() > 600) {
				setCoordinates(600,400);
				stage = 6;
			}
		}
	}
	
	//loops back to 1
	public void runStage6() {
		if(stage == 6) {
			//s1
			infarctus01.respawn();
			infarctus02.respawn();
			infarctus03.respawn();
			//s2
			knight01.respawn();
			knight02.respawn();
			knight03.respawn();
			knight04.respawn();
			//s3
			infarctus04.respawn();
			infarctus05.respawn();
			infarctus06.respawn();
			infarctus07.respawn();
			infarctus08.respawn();
			//goes back to stage 1
			tokenS2 = 0;
			minionSpawnS3 = 500;
			downTimer = 167;
		}
	}
	
	//to hold the minions, so they dont all spawn at once
	public void holdMinions() {
		//s1
		infarctus01.hold();
		infarctus02.hold();
		infarctus03.hold();
		//s2
		knight01.hold();
		knight02.hold();
		knight03.hold();
		knight04.hold();
		//s3
		infarctus04.hold();
		infarctus05.hold();
		infarctus06.hold();
		infarctus07.hold();
		infarctus08.hold();
	}
	
	//allows for 360 degree movement, but its in radians so
	public void moveRadians360(int px, int py) {
		double angle = Math.atan2(py - getY(), px - getX());
		if(angle < 0) angle += 2 * Math.PI;
		
		//finds how much to add to coordinates based off of speed to move at angle
		double xMod = super.getX() + (0.5 * getSpeed() * Math.cos(angle));
		double yMod = super.getY() + (0.5 * getSpeed() * Math.sin(angle)); 

		//updates the coords
		super.setCoordinates((int)xMod,(int)yMod);
				
		// System.out.println("\r");
		// System.out.println("Angle: " + angle + " ");
		// System.out.println("Adding coords: (" + (int)(xMod + 0.5) + ", " + (int)(yMod) + ")");
		// System.out.println("Raw coords before add: (" + Math.cos(angle) + ", " + Math.sin(angle) + ")");
		// System.out.println("Coords: (" + getX() + ", " + getY() + ")");
		// System.out.println("Player coods: (" + Player.getX() + ", " + Player.getY() + ")");
		// System.out.println("Test Math: " + Math.sin(1.2490457723982544));
	}
	
	public static boolean getBossToken()
	{
		return bossToken;
	}
	
	//THE BOSS MUSIC
	public void playBossMusic() {
		Map.enableBossMusic();
	}
	
	//no boss music :C
	public void stopBossMusic() {
		// Boss music stops automatically when the player leaves room 8 (see Map.updateRoomMusic).
	}
	
	//Gets the stage if anyones curious
	public int getStage() {
		return stage;
	}
	//slows program down, i think unused?
	public void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
