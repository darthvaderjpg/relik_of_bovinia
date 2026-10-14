/*
Author : Jake Jiang SUBSCRIBE TO @SCRAPNEL on youtube!
Date   : 05/14/26
Notes  : This creates an elite guard enemy
- Movement: Runs in a box around player. 
 */

public class EliteGuard extends Enemy {
    private static final int LEFT = 1, BOTTOM = 2, RIGHT = 3, TOP = 4;
    private static final int BOX_WIDTH = 100;
    private int boxProgress = 0;
    private int currentSide = LEFT;
    private int tx, ty;
    
    //Constructors
    public EliteGuard(int x, int y, int roomID) {
        super(x, y, roomID, "eliteguard");
    }
    
    //runs in a box around the player
    public void trackPlayer(int px, int py, int pRoomID) {
        if(getTargetAvailability(pRoomID) && super.getMoveToken()) {
            super.isMoving(true);
            super.release();
            super.randomDash();
            super.checkIfAttacked();
            super.addOneToDashToken();
            super.proximityHit(px, py);
            boxPlayer(px, py);
            
            int x = super.getX(), y = super.getY(), speed = getSpeed();
            
            if(tx > x) {    
                if((x + speed) > tx) {
                    super.setCoordinates(tx, y);
                } else {
                    super.moveRight();
                    super.setDirection(EAST);
                }
            } 
            if(tx < x) {
                if((x - speed) < tx) {
                    super.setCoordinates(tx, y);
                } else {
                    super.moveLeft();
                    super.setDirection(WEST);
                }               
            } 
            if(ty < y) {
                if((y - speed) < ty) {
                    super.setCoordinates(x, ty);
                } else {
                    super.moveUp();
                    super.setDirection(NORTH);
                }           
            }
            if(ty > y) {
                if((y + speed) > ty) {
                    super.setCoordinates(x, ty);
                } else {
                    super.moveDown();
                    super.setDirection(SOUTH);
                }
            }
                
            if(tx == x && ty == y) super.isMoving(false);   
            
        } else {
            super.hold(); 
        }
        
        checkIfAttacked();
    }
    
    private void boxPlayer(int px, int py) {
        if(currentSide == LEFT) {
            ty = py + boxProgress;
            if(boxProgress == 100) ty = py + BOX_WIDTH;
        } else if(currentSide == BOTTOM) {
            tx = px + boxProgress;
            if(boxProgress == 100) tx = px + BOX_WIDTH;
        } else if(currentSide == RIGHT) {
            ty = py - boxProgress;
            if(boxProgress == 100) ty = py;
        } else if(currentSide == TOP) {
            tx = px - boxProgress;
            if(boxProgress == 100) tx = px;
        }
        
        boxProgress += super.getSpeed();
        
        if(boxProgress >= BOX_WIDTH && currentSide != TOP) {
            boxProgress = 0;
            currentSide++;
        } else if(boxProgress >= BOX_WIDTH && currentSide == TOP) {
            currentSide = LEFT;
            boxProgress = 0;
        }
    }
    
    public int getTargetX() {
        return tx;
    }
    
    public int getTargetY() {
        return ty;
    }
}