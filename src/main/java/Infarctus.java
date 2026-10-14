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
    public void trackPlayer(int px, int py, int pRoomID) {
        if(getTargetAvailability(pRoomID) && getMoveToken()) {
            super.proximityHit(px, py);
            super.isMoving(true);
            super.release();
            super.randomDash();
            super.checkProximityBox(px, py);
            super.addOneToDashToken();
            
            int x = getX(), y = getY(), speed = getSpeed();
            
            if(px > x) {    
                if((x + speed) > px) {
                    setCoordinates(px, y);
                } else {
                    super.moveRight();
                    setDirection(EAST);
                }
            } 
            if(px < x) {
                if((x - speed) < px) {
                    setCoordinates(px, y);
                } else {
                    super.moveLeft();
                    setDirection(WEST);
                }               
            } 
            if(py < y) {
                if((y - speed) < py) {
                    setCoordinates(x, py);
                } else {
                    super.moveUp();
                    setDirection(NORTH);
                }           
            }
            if(py > y) {
                if((y + speed) > py) {
                    setCoordinates(x, py);
                } else {
                    super.moveDown();
                    setDirection(SOUTH);
                }
            }
                
            if(px == x && py == y) {    
                isMoving(false);
            }   
            
        } else {
            super.hold(); 
        }
        
        super.checkIfAttacked();
    }
}