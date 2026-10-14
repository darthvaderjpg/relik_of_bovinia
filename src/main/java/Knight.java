/*
Author : Jake Jiang SUBSCRIBE TO @SCRAPNEL on youtube!
Date   : 05/14/26
Notes  : This creates a Knight enemy
- Movement: Charges at player at random intervals
 */

public class Knight extends Enemy {
    private int dashCooldown = 150, dashPeriod = 200, dashDuration = 25, dashTime = 0, dashSpeed = 43;
    private double dashAngle;
    
    public Knight(int x, int y, int roomID) {
        super(x, y, roomID, "knight");
    }
    
    //moves 360 degrees, dashes at player. When not dashing, only moves up and left.
    public void trackPlayer(int px, int py, int pRoomID) {
        if(getTargetAvailability(pRoomID)) super.proximityHit(px, py);
        
        double angle = Math.atan2(py - getY(), px - getX());
        if(angle < 0) angle += 2 * Math.PI;

        if(getTargetAvailability(pRoomID) && getMoveToken()) {
            super.release();
            dashCooldown--;
            
            if(dashCooldown < dashTime) {
                dashAngle = angle;
                dashLine(dashAngle, dashSpeed);
                dashCooldown = dashPeriod;
            } else if(dashCooldown > dashPeriod - dashDuration){
                dashLine(dashAngle, dashSpeed);
            }
            
            moveRadians360(angle);
            super.checkIfAttacked();
        } else {
            hold();
        }
    }
    
    public void moveRadians360(double angle) {
        double xMod = super.getX() + (0.5 * getSpeed() * Math.cos(angle));
        double yMod = super.getY() + (0.5 * getSpeed() * Math.sin(angle)); 

        super.setCoordinates((int)xMod, (int)yMod);
    }
    
    public void dashLine(double angle, int speed) {
        double xMod = super.getX() + (0.5 * speed * Math.cos(angle));
        double yMod = super.getY() + (0.5 * speed * Math.sin(angle)); 

        super.setCoordinates((int)xMod, (int)yMod);
    }

    public void setDashTime(int dashTime) {
        this.dashTime = dashTime;
    }
}