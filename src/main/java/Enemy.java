// Author : Jake Jiang / Siddarth Iyer / Ofer Koren
// Notes  : Abstract base class for all enemies in Relik of Bovinia. Updated with exact HP and damage stats.

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Enemy implements ImageObserver {
    
    private final static Image INFARCTUS_NORTH = Tools.getNewImageIcon("INFARCTUS_NORTH.png");
    private final static Image INFARCTUS_EAST = Tools.getNewImageIcon("INFARCTUS_EAST.png");
    private final static Image INFARCTUS_SOUTH = Tools.getNewImageIcon("INFARCTUS_SOUTH.png");
    private final static Image INFARCTUS_WEST = Tools.getNewImageIcon("INFARCTUS_WEST.png");
    
    private final static Image BOVISFIGHT_NORTH = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
    private final static Image BOVISFIGHT_EAST = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
    private final static Image BOVISFIGHT_SOUTH = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
    private final static Image BOVISFIGHT_WEST = Tools.getNewImageIcon("BOVISFIGHT_WEST.png");
    
    private final static Image ELITEGUARD_NORTH = Tools.getNewImageIcon("ELITE_GUARD_RIGHT.gif");
    private final static Image ELITEGUARD_EAST = Tools.getNewImageIcon("ELITE_GUARD_RIGHT.gif");
    private final static Image ELITEGUARD_SOUTH = Tools.getNewImageIcon("ELITE_GUARD_LEFT.gif");
    private final static Image ELITEGUARD_WEST = Tools.getNewImageIcon("ELITE_GUARD_LEFT.gif");
    
    private final static Image KNIGHT_NORTH = Tools.getNewImageIcon("SOLDIER_RIGHT.gif");
    private final static Image KNIGHT_EAST = Tools.getNewImageIcon("SOLDIER_RIGHT.gif");
    private final static Image KNIGHT_SOUTH = Tools.getNewImageIcon("SOLDIER_LEFT.gif");
    private final static Image KNIGHT_WEST = Tools.getNewImageIcon("SOLDIER_LEFT.gif");
    
    public final static Image ERROR = (new ImageIcon("ERROR.png")).getImage();
    public static final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
    private static int deathCounter;

    private int hp, dmg, x, y, speed, direction, roomID, range, dashDuration, releaseToken = 0, dashToken = 0, attackCooldown, currentAttackCooldown, dropCount = 1;
    private int x2, y2, speed2, hp2, dmg2;
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

    public Enemy(int x, int y, int roomID, String enemyType) {
        enemyType = enemyType.toLowerCase().trim();
        setRoomID(roomID);
        setCoordinates(x, y);
        
        switch (enemyType) {
            case "infarctus": assignEnemy(1); break;
            case "bovisfight": assignEnemy(2); break;
            case "eliteguard": assignEnemy(3); break;
            case "knight": assignEnemy(4); break;
            default: System.out.println(">" + enemyType + "< did not match"); break;
        }
    }
    
    public static ArrayList<Enemy> spawnEnemiesForRoom(int roomID) {
        Map.SpawnConfig config = Map.getRoomSpawnConfig(roomID);
        ArrayList<Enemy> roomEnemies = new ArrayList<>();
        
        if (config.minCount == 0 && config.maxCount == 0) {
            return roomEnemies;
        }
        
        int count;
        if (config.minCount == config.maxCount) {
            count = config.minCount;
        } else {
            count = config.minCount + (int)(Math.random() * (config.maxCount - config.minCount + 1));
        }
        
        for (int i = 0; i < count; i++) {
            String enemyType = "infarctus";
            double roll = Math.random();
            double sum = 0;
            
            sum += config.infarctusWeight;
            if (roll < sum) {
                enemyType = "infarctus";
            } else {
                sum += config.soldierWeight;
                if (roll < sum) {
                    enemyType = "knight";
                } else {
                    enemyType = "eliteguard";
                }
            }
            
            int spawnX = 250 + (int)(Math.random() * 500);
            int spawnY = 250 + (int)(Math.random() * 350);
            
            roomEnemies.add(new Enemy(spawnX, spawnY, roomID, enemyType) {
                @Override
                public void trackPlayer(int px, int py, int pRoomID) {
                    if (getTargetAvailability(pRoomID)) {
                        if (getX() < px) moveRight();
                        else if (getX() > px) moveLeft();
                        if (getY() < py) moveDown();
                        else if (getY() > py) moveUp();
                    }
                }
            });
        }
        return roomEnemies;
    }
    
    private void assignEnemy(int type) {
        switch(type) {
            case 1: // Infarctus (5 HP, 2 DMG)
                this.speed = 1; this.speed2 = speed; this.dmg = 2; this.dmg2 = dmg;
                this.hp = 5; this.hp2 = hp; this.range = 35; this.direction = SOUTH;
                this.dashDuration = (int)(100.0 * (1 + Math.random()) * (1 + Math.random()) * (1 + Math.random())); 
                this.attackCooldown = 10; this.currentAttackCooldown = attackCooldown;
                this.body = INFARCTUS_SOUTH; enemyType = "infarctus"; enemyType2 = enemyType;
                break;
            case 2: // Bovis / Boss (2000 HP, 45 DMG)
                this.speed = 2; this.speed2 = speed; this.dmg = 45; this.dmg2 = dmg;
                this.hp = 2000; this.hp2 = hp; this.range = 45; this.direction = SOUTH;
                this.attackCooldown = 37; this.currentAttackCooldown = attackCooldown;
                this.body = BOVISFIGHT_SOUTH; enemyType = "bovisfight"; enemyType2 = enemyType;
                break;
            case 3: // Elite Guard (50 HP, 15 DMG)
                this.speed = 3; this.speed2 = speed; this.dmg = 15; this.dmg2 = dmg;
                this.hp = 50; this.hp2 = hp; this.range = 60; this.direction = SOUTH;
                this.attackCooldown = 25; this.currentAttackCooldown = 35;
                this.body = ELITEGUARD_SOUTH; enemyType = "eliteguard"; enemyType2 = enemyType;
                break;
            case 4: // Soldier / Knight (25 HP, 5 DMG)
                this.speed = 3; this.speed2 = speed; this.dmg = 5; this.dmg2 = dmg;
                this.hp = 25; this.hp2 = hp; this.range = 75; this.direction = SOUTH;
                this.attackCooldown = 8; this.currentAttackCooldown = attackCooldown;
                this.body = KNIGHT_SOUTH; this.body2 = body; enemyType = "knight"; enemyType2 = enemyType;
                break;
        }
    }
    
    public int getHealth() { return hp; }
    public int getHealth2() { return hp2; }
    public void setHealth(int hp) { this.hp = hp; }
    public void setCoordinates(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public void addX(int dx) { x += dx; }
    public int getY() { return y; }
    public void addY(int dy) { y += dy; }
    public void setRange(int range) { this.range = range; }
    public int getRange() { return range; }
    public void setDMG(int dmg) { this.dmg = dmg; }
    public int attack() { return dmg; }
    public void setSpeed(int speed) { this.speed = speed; }
    public int getSpeed() { return speed; }
    public void setWeaponImage(Image weapon) { this.weapon = weapon; }
    public Image getWeaponImage() { return weapon; }
    public void setBodyImage(Image body) { this.body = body; }
    public Image getBodyImage() { return body; }
    public int getroomID() { return roomID; }
    
    public void die() {
        if (dropCount > 0) {
            dropCount = 0;
            dropLoot(enemyType2);
        }
        hp = -1;
        dmg = 0;
        body = null;
        weapon = null;
        hold();
        deathCounter++;
        setEnemyType("dead");
    }
    
    public void respawn() {
        hp = hp2;
        dmg = dmg2;
        body = body2;
        weapon = weapon2;
        setEnemyType(enemyType2);
        dropCount = 1;
        hold();
    }
    
    public int getDeathCount() { return deathCounter; }
    
    private static void dropLoot(String enemyType) {
        switch (enemyType) {
            case "infarctus": Item.getDroppedInfarctus(); break;
            case "eliteguard": Item.getDroppedEliteGuard(); break;
            case "knight": Item.getDroppedSoldier(); break;
            default: System.out.println("Could not find >" + enemyType + "<"); break;
        }
    }
    
    public void stop() { moveToken = false; }
    public void allowMove() { moveToken = true; }
    
    public int takeDMG(int dmg) {
        hp -= dmg;
        if(hp <= 0) { die(); }
        return hp;
    }
    
    public void setEnemyType(String enemyType) { this.enemyType = enemyType; }
    public String getEnemyType() { return enemyType; }
    public void setRoomID(int ID) { roomID = ID; }
    public int getRoomID() { return roomID; }
    
    public boolean getTargetAvailability(int targetID) {
        if(targetID == roomID) {
            release();
            moveToken = true;
            return true;
        } else {
            hold();
            moveToken = false;
            return false;
        }
    }
    
    public void moveLeft() { x = x - speed; setDirection(WEST); }
    public void moveRight() { x = x + speed; setDirection(EAST); }
    public void moveUp() { y = y - speed; setDirection(NORTH); }
    public void moveDown() { y = y + speed; setDirection(SOUTH); }
    
    public void isMoving(boolean isMoving) { this.isMoving = isMoving; }
    public boolean getIsMoving() { return isMoving; }
    public boolean getMoveToken() { return moveToken; }
    
    public abstract void trackPlayer(int px, int py, int pRoomID);
    
    public void randomDash() {  
        if(dashToken == 0) {
            speed2 = speed;
            setSpeed((int)((double)speed * (1 + Math.random()) * (0.9 + Math.random())));
        } else if(dashToken > 30.0 * (1.5 + Math.random())) {
            setSpeed(speed2);
            dashToken -= dashDuration;
        }
    }
    
    public void addOneToDashToken() { dashToken++; }

    public void setDirection(int direction) {
        this.direction = direction;
        switch(getEnemyType()) {
            case "infarctus":
                switch(direction) {
                    case NORTH: setBodyImage(INFARCTUS_NORTH); break;
                    case EAST: setBodyImage(INFARCTUS_EAST); break;
                    case SOUTH: setBodyImage(INFARCTUS_SOUTH); break;
                    case WEST: setBodyImage(INFARCTUS_WEST); break;
                }
            break;
            case "bovisfight":
                switch(direction) {
                    case NORTH: setBodyImage(BOVISFIGHT_NORTH); break;
                    case EAST: setBodyImage(BOVISFIGHT_EAST); break;
                    case SOUTH: setBodyImage(BOVISFIGHT_SOUTH); break;
                    case WEST: setBodyImage(BOVISFIGHT_WEST); break;
                }
            break;
            case "eliteguard":
                switch(direction) {
                    case NORTH: setBodyImage(ELITEGUARD_NORTH); break;
                    case EAST: setBodyImage(ELITEGUARD_EAST); break;
                    case SOUTH: setBodyImage(ELITEGUARD_SOUTH); break;
                    case WEST: setBodyImage(ELITEGUARD_WEST); break;
                }
            break;
            case "knight":
                switch(direction) {
                    case NORTH: setBodyImage(KNIGHT_NORTH); break;
                    case EAST: setBodyImage(KNIGHT_EAST); break;
                    case SOUTH: setBodyImage(KNIGHT_SOUTH); break;
                    case WEST: setBodyImage(KNIGHT_WEST); break;
                }
            break;
            default: setBodyImage(ERROR); break;
        }
    }
    
    public int getDirection() {
        switch (direction){
            case NORTH: return NORTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return SOUTH;
        }
    }
    
    public void hold() {
        if(releaseToken == 0) { x2 = x; y2 = y; }
        releaseToken = 1;
        setCoordinates(-10000,-10000);
        stop();
    }
    
    public void release() {
        if(releaseToken == 1) {
            x = x2; y = y2;
            releaseToken = 0;
            setCoordinates(x, y);
            allowMove();
        }
    }
    
    private boolean attackAllowed() {
        if(currentAttackCooldown <= 0) { currentAttackCooldown = attackCooldown; return true; }
        return false;
    }
    
    private void runAttackCooldown() { currentAttackCooldown--; }
    
    public void proximityHit(int px, int py) {
        int hitDmg;
        runAttackCooldown();
        if(checkProximityBox(px, py) && attackAllowed()) {
            hitDmg = attack();
            if (hitDmg > 0) {
                Player.takeDamage(hitDmg);
            }
        }
    }
    
    public boolean checkProximityBox(int tx, int ty) {
        int x = getX(), y = getY();
        return (tx > x - range && tx < x + range && ty > y - range && ty < y + range);
    }
    
    public void getProximityBox() {}
    
    public void checkIfAttacked() {
        if (Player.getAttackingState()) {
            switch (Main.getLastButtonPressed()) {
                case "W":
                    if (y <= Player.getY() && Player.getY() - Player.getAttackingRange() - 40 <= y && x <= Player.getX() + Player.getAttackingRange() && x >= Player.getX() - Player.getAttackingRange()) {
                        takeDMG(Player.calculateDamage(getEnemyType()));
                    }
                    break;
                case "A":
                    if (x <= Player.getX() && Player.getX() - Player.getAttackingRange() - 40 <= x && y >= Player.getY() - Player.getAttackingRange() && y <= Player.getY() + Player.getAttackingRange()) {
                        takeDMG(Player.calculateDamage(getEnemyType())); 
                    }
                    break;
                case "S":
                    if (y >= Player.getY() && Player.getY() + Player.getAttackingRange() + 40 >= y && x <= Player.getX() + Player.getAttackingRange() && x >= Player.getX() - Player.getAttackingRange()) {                   
                        takeDMG(Player.calculateDamage(getEnemyType()));
                    }
                    break;
                case "D":
                    if (x >= Player.getX() && Player.getX() + Player.getAttackingRange() + 40 >= x && y >= Player.getY() - Player.getAttackingRange() && y <= Player.getY() + Player.getAttackingRange()) {
                        takeDMG(Player.calculateDamage(getEnemyType())); 
                    }
                    break;
            }
        }
        if(hp <= 0) die();
    }
    
    public void draw(Graphics g) {
        switch (enemyType) {
            case "infarctus": g.drawImage(getBodyImage(), getX() + INFARCTUS_OFFSET_X, getY() + INFARCTUS_OFFSET_Y, null); break;
            case "knight": g.drawImage(getBodyImage(), getX() + KNIGHT_OFFSET_X, getY() + KNIGHT_OFFSET_Y, null); break;
            case "eliteguard": g.drawImage(getBodyImage(), getX() + ELITEGUARD_OFFSET_X, getY() + ELITEGUARD_OFFSET_Y, null); break;
            default: g.drawImage(getBodyImage(), getX(), getY(), null); break;
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}