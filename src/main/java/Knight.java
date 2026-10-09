/*
Author : Jake Jiang SUBSCRIBE TO @SCRAPNEL on youtube!
Date   : 05/14/26
Notes  : This creates an Knight enemy
		- Movement: Charges at player at random intervals
 */

public class Knight extends Enemy{
	private boolean dashToken = true;
	private int dashCooldown = 150, dashPeriod = 200, dashDuration = 25, dashTime = 0, dashSpeed = 43;
	private double dashAngle;
	
	
	public Knight(int x, int y, int roomID) {
		super(x, y, roomID, "knight");
	}
	
	//moves 360 degrees, dashes at player. When not dashing, only moves up and left.
	public void trackPlayer(int px, int py, int pRoomID) {
		//attack player
		if(getTargetAvailability(pRoomID)) super.proximityHit(px, py);
		
		//get the angle from enemy to player
		double angle = Math.atan2(py - getY(), px - getX());
		if(angle < 0) angle += 2 * Math.PI;
//		System.out.println(angle);

		if(getTargetAvailability(pRoomID) && getMoveToken()) {
			super.release();
			dashCooldown--;
			
			if(dashCooldown < dashTime) {
				dashAngle = angle;
				dashLine(dashAngle,dashSpeed);
				dashCooldown = dashPeriod;
			} else if(dashCooldown > dashPeriod - dashDuration){
				dashLine(dashAngle,dashSpeed);
			}
			
			
			moveRadians360(angle);
			
			super.checkIfAttacked();
//			if(px + super.getSpeed());
		} else {
			hold();
			
		}
		
	}
	
	//NOTE: ONLY INPUT RADIANS
	//Knight specialized movement to move at any angle
	public void moveRadians360(double angle) {
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
	
	
	public void dashLine(double angle,int speed) {
		double xMod = super.getX() + (0.5 * speed * Math.cos(angle));
		double yMod = super.getY() + (0.5 * speed * Math.sin(angle)); 

		//updates the coords
		super.setCoordinates((int)xMod,(int)yMod);
	}
	//sets when this guy should dash
	public void setDashTime(int dashTime) {
		this.dashTime = dashTime;
	}
	
}
