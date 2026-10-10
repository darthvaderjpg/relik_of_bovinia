/*
Author: Jake Jiang (@Scrapnel on Youtube and Minecraft or spectr.x on discord)
Date  : 05/05/26
Notes : This class creates a entity
 */

import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.*;

public abstract class Enemy implements ImageObserver {
	
	//infarctus images
	private final static Image INFARCTUS_NORTH = Tools.getNewImageIcon("INFARCTUS_NORTH.png");
	private final static Image INFARCTUS_EAST = Tools.getNewImageIcon("INFARCTUS_EAST.png");
	private final static Image INFARCTUS_SOUTH = Tools.getNewImageIcon("INFARCTUS_SOUTH.png");
	private final static Image INFARCTUS_WEST = Tools.getNewImageIcon("INFARCTUS_WEST.png");
	// Bovis fight images.
	private final static Image BOVISFIGHT_NORTH = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
	private final static Image BOVISFIGHT_EAST = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
	private final static Image BOVISFIGHT_SOUTH = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
	private final static Image BOVISFIGHT_WEST = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
	// Elite guard images.
	private final static Image ELITEGUARD_NORTH = Tools.getNewImageIcon("ELITE_GUARD_RIGHT.gif");
	private final static Image ELITEGUARD_EAST = Tools.getNewImageIcon("ELITE_GUARD_RIGHT.gif");
	private final static Image ELITEGUARD_SOUTH = Tools.getNewImageIcon("ELITE_GUARD_LEFT.gif");
	private final static Image ELITEGUARD_WEST = Tools.getNewImageIcon("ELITE_GUARD_LEFT.gif");
	// Knight images.
	private final static Image KNIGHT_NORTH = Tools.getNewImageIcon("SOLDIER_RIGHT.gif");
	private final static Image KNIGHT_EAST = Tools.getNewImageIcon("SOLDIER_RIGHT.gif");
	private final static Image KNIGHT_SOUTH = Tools.getNewImageIcon("SOLDIER_LEFT.gif");
	private final static Image KNIGHT_WEST = Tools.getNewImageIcon("SOLDIER_LEFT.gif");
	
	// Counts the number of times an enemy has been killed by a player.
	public final static Image ERROR = (new ImageIcon("ERROR.png")).getImage();
	public static final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
	private static int deathCounter;

	private int hp, dmg, x, y, speed, direction, roomID, range, dashDuration, releaseToken = 0, dashToken = 0, attackCooldown, currentAttackCooldown, dropCount = 1; // current data
	private int  x2, y2, speed2, hp2, dmg2; // saved copy data
	private Image weapon, body;
	private Image weapon2, body2;
	private boolean isMoving, moveToken = true;
	private String enemyType = "[undefined]";
	private String enemyType2 = enemyType;
	private static final int INFARCTUS_OFFSET_X = -25;
	private static final int INFARCTUS_OFFSET_Y = -25;
	private static final int KNIGHT_OFFSET_X = -25;
	private static final int KNIGHT_OFFSET_Y = -25;
	private static final int ELITEGUARD_OFFSET_X = -45;
	private static final int ELITEGUARD_OFFSET_Y = -45;

	// Constructor for enemy location and type.
	public Enemy(int x, int y, int roomID, String enemyType) {
		enemyType = enemyType.toLowerCase().trim();
		
		setRoomID(roomID);
		setCoordinates(x, y);
		
		switch (enemyType) {
			case "infarctus":
				assignEnemy(1);
			break;
			case "bovisfight":
				assignEnemy(2);
			break;
			case "eliteguard":
				assignEnemy(3);
			break;
			case "knight":
				assignEnemy(4);
			break;
			default:
				System.out.println(">" + enemyType + "< did not match: infarctus/bovisfight/eliteguard/knight");
			break;
		}
	}
	
	//This class will assign the enemy to their respective images
	//and stats based off of the variable enemyType
	//this method will assign the enemy to their respective type and stats
	private void assignEnemy(int type) {
		System.out.print("Enemy assigning to... ");
		
		switch(type) {
			case 1:
				//INFARCTUS
				this.speed = 1;
				this.speed2 = speed;
				this.dmg = 3;
				this.dmg2 = dmg;
				this.hp = 20;
				this.hp2 = hp;
				this.range = 35;
				this.direction = SOUTH;
				this.dashDuration = (int)(100.0 * (1 + Math.random()) * (1 + Math.random()) * (1 + Math.random())); 
				this.attackCooldown = 10;
				this.currentAttackCooldown = attackCooldown;
				this.weapon2 = weapon;
				this.body =  INFARCTUS_SOUTH;
				enemyType = "infarctus";
				enemyType2 = enemyType;
				System.out.println(enemyType);
				
			break;
			case 2:
				//BOVISFIGHT - BOSS
				this.speed = 2;
				this.speed2 = speed;
				this.dmg = 30;
				this.dmg2 = dmg;
				this.hp = 200;
				this.hp2 = hp;
				this.range = 70;
				this.direction = SOUTH;
				this.attackCooldown = 37; //most random number
				this.currentAttackCooldown = attackCooldown;
				this.weapon2 = weapon;
				this.body = BOVISFIGHT_SOUTH;
				enemyType = "bovisfight";
				enemyType2 = enemyType;
				System.out.println(enemyType);
			break;
			case 3:
				//ELITEGUARD
				this.speed = 3;
				this.speed2 = speed;
				this.dmg = 10;
				this.dmg2 = dmg;
				this.hp = 100;
				this.hp2 = hp;
				this.range = 60;
				this.direction = SOUTH;
				this.attackCooldown = 25;
				this.currentAttackCooldown = 35;
				this.weapon2 = weapon;
				this.body = ELITEGUARD_SOUTH;		
				enemyType = "eliteguard";
				enemyType2 = enemyType;
				System.out.println(enemyType);
			break;
			case 4:
				//KNIGHT
				this.speed = 3;
				this.speed2 = speed;
				this.dmg = 25;
				this.dmg2 = dmg;
				this.hp = 50;
				this.hp2 = hp;
				this.range = 27;
				this.direction = SOUTH;
				this.attackCooldown = 8;
				this.currentAttackCooldown = attackCooldown;
				this.weapon2 = weapon;
				this.body = KNIGHT_SOUTH;
				this.body2 = body;
				enemyType = "knight";
				enemyType2 = enemyType;
				System.out.println(enemyType);
			break;
			default:
				System.out.println("No Enemy Found!");
			break;
			
		}
	}
	
	//gets health
	public int getHealth() {
		return hp;
	}
	
	public int getHealth2() {
		return hp2;
	}
	
	//set health
	public void setHealth(int hp) {
		this.hp = hp;
	}
	
	//sets coordinates
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//gets x coordinate
	public int getX() {
		return x;
	}
	
	//adds the x coordinate to a integer input coordinate
	public void addX(int dx) {
		x += dx;
	}
	
	//gets the y coordinate
	public int getY() {
		return y;
	}
	
	//modifies y coordinate by adding an integer input parameter
	public void addY(int dy) {
		y += dy;
	}
	
	//sets the enemy range of attack
	public void setRange(int range) {
		this.range = range;
	}
	
	//gets the enemy range of attack
	public int getRange() {
		return range;
	}
	
	//sets the damage of the enemy
	public void setDMG(int dmg) {
		this.dmg = dmg;
	}
	
	//returns the damage of the enemy
	public int attack() {
		return dmg;
	}
	
	//sets the enemies' speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//gets the enemies' speed
	public int getSpeed() {
		return speed;
	}
	
	//sets the weapon image
	public void setWeaponImage(Image weapon) {
		this.weapon = weapon;
	}
	
	//gets the weapon image
	public Image getWeaponImage() {
		return weapon;
	}
	
	//sets the image of the enemy
	public void setBodyImage(Image body) {
		this.body = body;
	}
	
	//gets the image of the enemy
	public Image getBodyImage() {
		return body;
	}
	
	//gets the room the enemy is in
	public int getroomID() {
		return roomID;
	}
	
	//sets the enemy to a state that cannot modify anything else
	public void die() {
		if (dropCount > 0)		
		{
			dropCount = 0;
			dropLoot(enemyType2);
//			System.out.println(enemyType2 + " died!");	
		}
		
			hp = -1;
			dmg = 0;
			body = null;
			weapon = null;
			hold();
			deathCounter++;
			setEnemyType("dead");
	}
	
	//sets the enemy to their original spawning state
	public void respawn() {
		hp = hp2;
		dmg = dmg2;
		body = body2;
		weapon = weapon2;
		setEnemyType(enemyType2);
		hold();
		System.out.println(enemyType2 + " respawned with stats [hp: " + hp + "] [dmg : " + dmg + "] [body : " + "--" + "]");
	}
	
	//gets the # of times ANY enemy has died
	public int getDeathCount() {
		return deathCounter;
	}
	
	//responsible for what they player gets according to enemy type
	private static void dropLoot(String enemyType) {
		
		switch (enemyType) {
		case "infarctus":
			Item.getDroppedInfarctus();
		break;
		case "eliteguard":
			Item.getDroppedEliteGuard();
		break;
		case "knight":
			Item.getDroppedSoldier();
		break;
		default:
			System.out.println("Could not find >" + enemyType + "<");
		break;
		}
	}
	
	//makes the enemy stop moving
	public void stop() {
		moveToken = false;
	}
	
	//ENABLES the enemy to move
	public void allowMove() {
		moveToken = true;
	}
	
	//makes the enemy take damage according to the inputted integer
	public int takeDMG(int dmg) {
		hp -= dmg;
		
		if(hp <= 0) {
			die();
		}
		
		return hp;
	}
	
	//sets the enemy type, advised not to touch
	public void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}
	
	//gets the enemy type
	public String getEnemyType() {
		return enemyType;
	}
	
	//sets the room the enemy will appear in
	public void setRoomID(int ID) {
		roomID = ID;
	}
	
	//gets the enemies' assigned room number
	public int getRoomID() {
		return roomID;
	}
	
	//finds if the target (player) is inside the room
	public boolean getTargetAvailability(int targetID) {
		if(targetID == roomID) {
			moveToken = true;
			return true;
		} else {
			moveToken = false;
			return false;
		}
	}
	
	//modifies the x coordinate to move left on screen at a rate based on speed
	public void moveLeft() {
		x = x - speed;
		setDirection(WEST);
//		System.out.println("Enemy speed: " + speed);
//		System.out.println("Enemy Coords: " + getX() + ", " + getY());
	}
	
	//modifies the x coordinate to move right on screen at a rate based on speed
	public void moveRight() {
		x = x + speed;
		setDirection(EAST);
	}
	
	//modifies the y coordinate to move up on screen at a rate based on speed
	public void moveUp() {
		y = y - speed;
		setDirection(NORTH);
	}
	
	//modifies the y coordinate to move down on screen at a rate based on speed
	public void moveDown() {
		y = y + speed;		
		setDirection(SOUTH);
	}
	
	//sets the isMoving variable 
	public void isMoving(boolean isMoving){
		this.isMoving = isMoving;
	}
	
	//finds if the enemy is moving or not
	public boolean getIsMoving() {
		return isMoving;
	}
	
	//gets the move token
	public boolean getMoveToken() {
		return moveToken;
	}
	
	//Dictates enemy behavior based off of target coordinates
	//Will not move if player coordinates are outside of room
	//NOTE: ALL ENEMIES INHERITING WILL BEHAVE DIFFERENTLY
	public abstract void trackPlayer(int px, int py,int pRoomID);
	
	//can (slightly variation) temporarily speed up an enemy
	public void randomDash() {	
		if(dashToken == 0) {
			speed2 = speed;
			setSpeed((int)((double)speed * (1 + Math.random()) * (0.9 + Math.random())));
		} else if(dashToken > 30.0 * (1.5 + Math.random())) {
			setSpeed(speed2);
			dashToken -= dashDuration;
		}
	}
	
	//adds 1 to the dashToken
	public void addOneToDashToken() {
		dashToken++;
	}

	//sets the direction of the enemy
	public void setDirection(int direction) {
		this.direction = direction;
		
		//Animation
		//String type = getEnemyType().toLowerCase();
		switch(getEnemyType()) {
			case "infarctus":
				
				switch(direction) {
					case NORTH:
						setBodyImage(INFARCTUS_NORTH);
					break;
					case EAST:
						setBodyImage(INFARCTUS_EAST);
					break;
					case SOUTH:
						setBodyImage(INFARCTUS_SOUTH);
					break;
					case WEST:
						setBodyImage(INFARCTUS_WEST);
					break;
				}
			break;
			case "bovisfight":
				
				switch(direction) {
					case NORTH:
						setBodyImage(BOVISFIGHT_NORTH);
					break;
					case EAST:
						setBodyImage(BOVISFIGHT_EAST);
					break;
					case SOUTH:
						setBodyImage(BOVISFIGHT_SOUTH);
					break;
					case WEST:
						setBodyImage(BOVISFIGHT_WEST);
					break;
				}
			break;
			case "eliteguard":
				
				switch(direction) {
					case NORTH:
						setBodyImage(ELITEGUARD_NORTH);
					break;
					case EAST:
						setBodyImage(ELITEGUARD_EAST);
					break;
					case SOUTH:
						setBodyImage(ELITEGUARD_SOUTH);
					break;
					case WEST:
						setBodyImage(ELITEGUARD_WEST);
					break;
				}
			break;
			case "knight":
				
				switch(direction) {
					case NORTH:
						setBodyImage(KNIGHT_NORTH);
					break;
					case EAST:
						setBodyImage(KNIGHT_EAST);
					break;
					case SOUTH:
						setBodyImage(KNIGHT_SOUTH);
					break;
					case WEST:
						setBodyImage(KNIGHT_WEST);
					break;
				}
			break;
			default:
				setBodyImage(ERROR);
			break;
				
		}
	}
	
	//gets the direction of the enemy
	public int getDirection() {
		switch (direction){
			case NORTH:
				return NORTH;
			case EAST:
				return EAST;
			case WEST:
				return WEST;
			default:
				return SOUTH;
		}
	}
	
	//This method will move the enemy offscreen until releasd.
	//Used when player is out of room.
	public void hold() {
		
		if(releaseToken == 0) {
			x2 = x;
			y2 = y;
		}
		
		releaseToken = 1;
		
		setCoordinates(-10000,-10000);
		stop();
		
		
//		System.out.println("Cached Enemy Coords: " + x2 + "," + y2);
	}
	
	//This will bring back the player after being 'hold', effectively teleporting it back to the same position as left.
	public void release() {
		if(releaseToken == 1) {
			x = x2;
			y = y2;
			
			releaseToken = 0;
			setCoordinates(x,y);
			allowMove();
		}
	}
	
	//finds if the enemies attack is allow based off the attackCooldown
	private boolean attackAllowed() {
		if(currentAttackCooldown <= 0) {
			currentAttackCooldown = attackCooldown;
			return true;
		} else {
			return false;
		}
	}
	
	//will modify the attackCooldown, otherwise the enemy attackAllowed method will behave weirdly
	private void runAttackCooldown() {
		currentAttackCooldown--;
	}
	
	//This will attack the player when they are too close
	public void proximityHit(int px, int py) {
		int dmg;
		runAttackCooldown();
//		System.out.println(currentAttackCooldown);
		if(checkProximityBox(px,py) && attackAllowed()) {
			dmg = attack();
			System.out.println("Enemy Dealt: " + dmg + " -- Player HP: " + Player.getHealth());
		}	else dmg = 0;
		
		Player.takeDamage(dmg);
	}
	
	//This will add a box to the enemy based on the range.
	//Keep in mind enemy coordinates are located at the corner of their image not the center
	//the letter T stands for target, just for convenience
	public boolean checkProximityBox(int tx, int ty) {
		//box offset right now because centered in image corner coords
		int x = getX(), y = getY();
		if(tx > x - range && tx < x + range && ty > y - range && ty < y + range) {
//		if(tx > x && tx < x + range && ty > y && ty < y + range) {
//			System.out.println("Target in range");
			return true;
		} else {
			return false;
		}
	}
	
	//This is a debugging tool. It may never see the light of day :C
	public void getProximityBox() {
		
	}
	
	// This method decreases the health of the enemy depending on the weapon the player holds when the player is attacking,
	// facing in the direction of the enemy, and within the bounds of the player's attack range.
	// Feel free to change this method any way you see fit!
	public void checkIfAttacked()
	{
		if (Player.getAttackingState())
		{
			switch (Main.getLastButtonPressed())
			{
				// Tests if the player faces up.
				case "W":
					if (y <= Player.getY() 
					    && Player.getY() - Player.getAttackingRange() - 40 <= y 
					    && x <= Player.getX() + Player.getAttackingRange() 
					    && x >= Player.getX() - Player.getAttackingRange())
					{
						takeDMG(Player.getDamage());
					}
					break;
					
				// Tests if the player faces left.
				case "A":
					if (x <= Player.getX() 
					    && Player.getX() - Player.getAttackingRange() - 40 <= x 
					    && y >= Player.getY() - Player.getAttackingRange() 
					    && y <= Player.getY() + Player.getAttackingRange())
					{
						takeDMG(Player.getDamage()); 
					}
					break;
				
				// Tests if the player faces down.
				case "S":
					if (y >= Player.getY() 
					    && Player.getY() + Player.getAttackingRange() + 40 >= y
					    && x <= Player.getX() + Player.getAttackingRange() 
					    && x >= Player.getX() - Player.getAttackingRange())
					{					
						takeDMG(Player.getDamage());
					}
					break;
				
				// Tests if the player faces right.
				case "D":
					if (x >= Player.getX() 
					    && Player.getX() + Player.getAttackingRange() + 40 >= x 
					    && y >= Player.getY() - Player.getAttackingRange() 
					    && y <= Player.getY() + Player.getAttackingRange())
					{
						takeDMG(Player.getDamage()); 
					}
					break;
			}
		}
		if(hp <= 0) die();
	}
	
	//draws enemy
	public void draw(Graphics g)
	{
		switch (enemyType)
		{
			case "infarctus":
				g.drawImage(getBodyImage(), getX() + INFARCTUS_OFFSET_X, getY() + INFARCTUS_OFFSET_Y, null);
				break;
		
			case "knight":
				g.drawImage(getBodyImage(), getX() + KNIGHT_OFFSET_X, getY() + KNIGHT_OFFSET_Y, null);
				break;
				
			case "eliteguard":
				g.drawImage(getBodyImage(), getX() + ELITEGUARD_OFFSET_X, getY() + ELITEGUARD_OFFSET_Y, null);
				break;
				
			default:
				g.drawImage(getBodyImage(), getX(), getY(), null);
		}
	}

	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}