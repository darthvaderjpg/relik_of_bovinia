/*
Author : Jake Jiang SUBSCRIBE TO @SCRAPNEL on youtube!
Date   : 05/14/26
Notes  : This creates an infarctus enemy
 */

public class Infarctus extends Enemy {

	//Constructors
	public Infarctus(int x, int y, int roomID) {
		super(x, y, roomID, "infarctus");
	}
	
	//chases player. Can only moves on diagonally or up/down.
	public void trackPlayer(int px, int py,int pRoomID) {
		//DEBUG
//		System.out.println("\r");
//		System.out.println("Enemy moving: " + getIsMoving());
//		System.out.println("Enemy speed: " + speed);
//		System.out.println("Target in proximity: " + checkProximityBox(px,py));
//		System.out.println("Enemy Coords: " + getX() + ", " + getY());
//		System.out.println("Player Coords: " + px + ", " + py + "");
		
		
		
		if(getTargetAvailability(pRoomID) && getMoveToken()) {
			super.proximityHit(px, py);
			super.isMoving(true);
			super.release();
			super.randomDash();
			super.checkProximityBox(px,py);
			super.addOneToDashToken();
			
			int x = getX(), y = getY(), speed = getSpeed();
			//checks for if the enemy skipped over player
			// Also moves the enemy
			if(px > x) {	
				if((x + speed) > px) {
					setCoordinates(px,y);
				} else {
					super.moveRight();
					setDirection(EAST);
				}
			} 
			if(px < x) {
				if((x - speed) < px) {
					setCoordinates(px,y);
				} else {
					super.moveLeft();
					setDirection(WEST);
				}				
			} 
			if(py < y) {
				if((y - speed) < py) {
					setCoordinates(x,py);
				} else {
					super.moveUp();
					setDirection(NORTH);
				}			
			}
			if(py > y) {
				if((y + speed) > py) {
					setCoordinates(x,py);
				} else {
					super.moveDown();
					setDirection(SOUTH);
				}
			}
				
			//checks for enemy reaching player
			if(px == x && py == y) {	
				isMoving(false);
			} 	
			
		} else {
			super.hold(); 
//			System.out.println("held!");
		}
		
		// Checks if the player is attacking the enemy.
		// Call this for all enemies!
		super.checkIfAttacked();
		
//		System.out.println("Enemy moving: " + getIsMoving());
//		System.out.println("Enemy Coords: " + getX() + ", " + getY());
//		System.out.println("Player Coords: " + px + ", " + py);
	}
}
