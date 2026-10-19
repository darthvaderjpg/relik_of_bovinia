// Author : Ofer Koren & Siddarth Iyer
// Editor : Jake Jiang -- Subscribe to Scrapnel on YT
// Date   : 5/14/2026
// Rev    : 10
// Notes  : Added damage cooldown / invincibility frames to prevent instant random deaths.

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Player implements ImageObserver
{
    // Non-animated player models (png). Art is resized to 400% in aseprite.
    private final static Image PLAYER_STANDING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_SOUTH_MODEL.png");
    private final static Image PLAYER_STANDING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_NORTH_MODEL.png");
    private final static Image PLAYER_STANDING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_WEST_MODEL.png");
    private final static Image PLAYER_STANDING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_EAST_MODEL.png");
    
    // Animated player walking models (gif).
    private final static Image PLAYER_WALKING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_SOUTH_MODEL.gif");
    private final static Image PLAYER_WALKING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_NORTH_MODEL.gif");
    private final static Image PLAYER_WALKING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_WEST_MODEL.gif");
    private final static Image PLAYER_WALKING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_EAST_MODEL.gif");
    
    // Animated player attacking models (gif).
    private final static Image PLAYER_ATTACKING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_SOUTH_MODEL.gif");
    private final static Image PLAYER_ATTACKING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_NORTH_MODEL.gif");
    private final static Image PLAYER_ATTACKING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_WEST_MODEL.gif");
    private final static Image PLAYER_ATTACKING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_EAST_MODEL.gif");
    
    // Other player fields.
    private static int currency = 0;
    private static int health = 100;
    private static int maxHealth = 100;
    private static int baseDamage = 1;
    private static int currentDamage = baseDamage;
    private static int x = 708;
    private static int y = 289;
    private static int speed = 7;
    private static int attackingRange = 75;
    private static int room = 0;
    private static boolean canMove = true;
    private static boolean isAttacking = false;
    private static boolean hasDealtDamage = false; // Prevents multi-frame hit melting
    private final static boolean DEBUG = false;
    private final static int OFFSET_X = -64;
    private final static int OFFSET_Y = -54;
    public static GamePanel gamePanel;
    private static boolean isDead = false;
    public static BoviniaMusic musicManager;

    // Damage Cooldown / Invincibility Fields
    private static long lastDamageTime = 0;
    private static final long DAMAGE_COOLDOWN_MS = 500; // 500ms immunity window

    // Methods.
    
    // Handles collision, repainting, movement, and the debug menu.
    public static void run()
    {
        if (canMove)
        {
            if (Main.getIsPressingW())
            {
                moveNorth();
            }
            else if (Main.getIsPressingA())
            {
                moveWest();
            }
            else if (Main.getIsPressingS())
            {
                moveSouth();
            }
            else if (Main.getIsPressingD())
            {
                moveEast();
            }
        }
        
        loopAround();
        gamePanel.repaint();

        if (DEBUG)
        {
            System.out.println("\r");
            System.out.println("playerX: " + x + "   playerY: " + y);
            System.out.println("W: " + Main.getIsPressingW());
            System.out.println("A: " + Main.getIsPressingA());
            System.out.println("S: " + Main.getIsPressingS());
            System.out.println("D: " + Main.getIsPressingD());
            System.out.println("P: " + Main.getIsPressingP());
            System.out.println("E: " + Main.getIsPressingE());
            System.out.println("last: " + Main.getLastButtonPressed());
            System.out.println("Room: " + room);
        }
    }
    
    public static void loopAround()
    {
        if (x > 790)
        {
            x = -30;
            changeRoom("East");
        }
        else if (x < -30)
        {
            x = 790;
            changeRoom("West");
        }
        
        if (y > 711)
        {
            y = 111;
            changeRoom("South");
        }
        else if (y < 111)
        {
            y = 711;
            changeRoom("North");
        }
    }
    
    public static void changeRoom(String Direction)
    {
        if (Direction.equals("North"))
        {
            switch (room)
            {
                case 2:
                    room = 3;
                    break;
                case 5:
                    room = 4;
                    break;
                case 6:
                    room = 7;
                    break;
            }
        }
        else if (Direction.equals("South"))
        {
            switch (room)
            {
                case 3:
                    room = 2;
                    break;
                case 4:
                    room = 5;
                    break;
                case 7:
                    room = 6;
                    break;
            }
        }
        else if (Direction.equals("West"))
        {
            switch (room)
            {
                case 2:
                    room = 1;
                    break;
                case 4:
                    room = 2;
                    break;
                case 6:
                    room = 4;
                    break;
            }
        }
        else if (Direction.equals("East"))
        {
            switch (room)
            {
                case 1:
                    room = 2;
                    break;
                case 2:
                    room = 4;
                    break;
                case 4:
                    room = 6;
                    break;
            }
        }
    }
    
    public static void doWallCollision()
    {
        while (x > 730 && (y < 396 || y > 451))
        {
            moveWest();
        }
        while (x < 20 && (y < 396 || y > 451))
        {
            moveEast();
        }
        while (y < 171 && (x < 352 || x > 412))
        {
            moveSouth();
        }
        while (y > 641 && (x < 352 || x > 412))
        {
            moveNorth();
        }
    }
    
    public static void moveToRoom(int room)
    {
        switch (room)
        {
            case 0:
                x = 728;
                y = 346;
                Player.room = 0;
                break;
            case 1:
                x = 134;
                y = 565;
                Player.room = 1;
                break;
            case 8:
                x = 42;
                y = 427;
                Player.room = 8;
                break;
        }
    }
    
    public static boolean isDead(){
        return isDead;
    }

    public static void die(){
        Hotbar.addCoins(-2);

        // Wipe inventory, hotbar, and keybar upon death
        Inventory.clear();
        Hotbar.clear();
        Keybar.clear();

        isDead = true;
        canMove = false; // Freeze player movement during death screen
        if (musicManager != null) {
            musicManager.pauseMusic();
        }
    }

    public static void respawn() {
        moveToRoom(0);
        setHealth(getMaxHealth());
        isDead = false;
        canMove = true; // Restore movement
        if (musicManager != null) {
            musicManager.changeRoom("overworld");
            musicManager.resumeMusic();
        }
    }

    public static Image getModel() {
        if (canMove){
            if (Main.getIsPressingW()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_WALKING_NORTH_MODEL;
            }
            else if (Main.getIsPressingA()) {
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_WALKING_WEST_MODEL;
            }
            else if (Main.getIsPressingS()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_WALKING_SOUTH_MODEL;
            }
            else if (Main.getIsPressingD()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_WALKING_EAST_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("W") && !Main.getIsPressingSpace()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_STANDING_NORTH_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("A") && !Main.getIsPressingSpace()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_STANDING_WEST_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("S") && !Main.getIsPressingSpace()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_STANDING_SOUTH_MODEL;
            }   
            else if (Main.getLastButtonPressed().equals("D") && !Main.getIsPressingSpace()){
                isAttacking = false;
                hasDealtDamage = false;
                return PLAYER_STANDING_EAST_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("W") && Main.getIsPressingSpace()){
                if (!isAttacking)
                {
                    hasDealtDamage = false;
                }
                isAttacking = true;
                return PLAYER_ATTACKING_NORTH_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("A") && Main.getIsPressingSpace()){
                if (!isAttacking)
                {
                    hasDealtDamage = false;
                }
                isAttacking = true;
                return PLAYER_ATTACKING_WEST_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("S") && Main.getIsPressingSpace()){
                if (!isAttacking)
                {
                    hasDealtDamage = false;
                }
                isAttacking = true;
                return PLAYER_ATTACKING_SOUTH_MODEL;
            }
            else if (Main.getLastButtonPressed().equals("D") && Main.getIsPressingSpace()){
                if (!isAttacking)
                {
                    hasDealtDamage = false;
                }
                isAttacking = true;
                return PLAYER_ATTACKING_EAST_MODEL;
            }
        }

        return PLAYER_STANDING_SOUTH_MODEL;
    }
    
    public static int getHealth()
    {
        return health;
    }
    
    public static int getMaxHealth(){   
        int baseMaxHealth = 1; // Base player HP is 1
        int armorBonus = 0;
        
        if (Hotbar.getHelmet() != null)
        {
            armorBonus += Hotbar.getHelmet().getFactor();
        }
        if (Hotbar.getChestplate() != null)
        {
            armorBonus += Hotbar.getChestplate().getFactor();
        }
        if (Hotbar.getLeggings() != null)
        {
            armorBonus += Hotbar.getLeggings().getFactor();
        }
        if (Hotbar.getBoots() != null)
        {
            armorBonus += Hotbar.getBoots().getFactor();
        }
        if (Hotbar.getCloaks() != null)
        {
            armorBonus += Hotbar.getCloaks().getFactor();
        }
        
        return baseMaxHealth + armorBonus;
    }
    
    public static int getTotalArmorDefense()
    {
        int defense = 0;
        if (Hotbar.getHelmet() != null)
        {
            defense += Hotbar.getHelmet().getFactor();
        }
        if (Hotbar.getChestplate() != null)
        {
            defense += Hotbar.getChestplate().getFactor();
        }
        if (Hotbar.getLeggings() != null)
        {
            defense += Hotbar.getLeggings().getFactor();
        }
        if (Hotbar.getBoots() != null)
        {
            defense += Hotbar.getBoots().getFactor();
        }
        if (Hotbar.getCloaks() != null)
        {
            defense += Hotbar.getCloaks().getFactor();
        }
        return defense;
    }
    
    public static int getX() 
    { 
        return x; 
    }
    
    public static int getY() 
    { 
        return y; 
    }
    
    public static int getSpeed() 
    { 
        return speed; 
    }
    
    public static int getAttackingRange() 
    { 
        return attackingRange; 
    }
    
    public static int getRoom() 
    { 
        return room; 
    }
    
    public static int getDamage() 
    {
        if (Hotbar.getWeapon() != null) 
        {
            return Hotbar.getWeapon().getFactor();
        }
        return baseDamage; // Base unarmed damage = 1
    }
    
    public static boolean getHasDealtDamage() 
    { 
        return hasDealtDamage; 
    }
    
    public static void setHasDealtDamage(boolean state) 
    { 
        hasDealtDamage = state; 
    }
    
    public static int calculateDamage(String enemyType) 
    {
        Object weapon = Hotbar.getWeapon();
        if (weapon != null && weapon.toString().toLowerCase().contains("mosquito")) 
        {
            healMosquito();
        }
        return getDamage();
    }
    
    private static void healMosquito() 
    {
        int healAmount = (int)(getMaxHealth() * 0.10);
        setHealth(health + healAmount);
    }
    
    public static boolean getMoveState() 
    { 
        return canMove; 
    }
    
    public static boolean getAttackingState() 
    { 
        return isAttacking; 
    }
    
    public static int getCurrency() 
    { 
        return currency; 
    }
    
    public static void setHealth(double playerHealth)
    {
        Player.health = Math.min((int) playerHealth, getMaxHealth());
        if (Player.health <= 0 && !isDead) 
        {
            Player.die();
        }
    }
    
    public static void setMaxHealth(int maxPlayerHealth)
    {
        Player.maxHealth = maxPlayerHealth;
    }
    
    public static void takeDamage(int rawDamage) 
    {
        if (rawDamage <= 0 || isDead) 
        {
            return;
        }

        long currentTime = System.currentTimeMillis();
        // Ignore damage if within the 500ms immunity window
        if (currentTime - lastDamageTime < DAMAGE_COOLDOWN_MS) {
            return;
        }
        lastDamageTime = currentTime;

        int mitigatedDamage = Math.max(1, rawDamage - getTotalArmorDefense());
        Player.health -= mitigatedDamage;
        if (Player.health <= 0) 
        {
            Player.die();
        }
    }
    
    public static void moveNorth() 
    { 
        y -= speed; 
        doWallCollision(); 
    }
    
    public static void moveSouth() 
    { 
        y += speed; 
        doWallCollision(); 
    }
    
    public static void moveWest() 
    { 
        x -= speed; 
        doWallCollision(); 
    }
    
    public static void moveEast() 
    { 
        x += speed; 
        doWallCollision(); 
    }
    
    public static void setRoom(int room) 
    { 
        Player.room = room; 
    }
    
    public static void setMoveState(boolean canMove) 
    { 
        Player.canMove = canMove; 
    }
    
    public static void draw(Graphics g)
    {
        g.drawImage(getModel(), getX() + OFFSET_X, getY() + OFFSET_Y, null);
    }

    public static void setX(int newX)
    {
        x = newX;
    }

    public static void setY(int newY)
    {
        y = newY;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
    {
        return false;
    }
}