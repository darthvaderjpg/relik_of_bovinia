// Author : Ofer Koren 
// Editor : Jake Jiang -- Subscribe to Scrapnel on YT
// Editor2 :Siddarth Iyer
// Date   : 5/14/2026
// Rev    : 04
// Notes  : Restored full multi-line formatting and added calculateDamage for precise weapon/enemy hit-count balancing.

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
    private static int baseDamage = 3;
    private static int currentDamage = baseDamage;
    private static int x = 708;
    private static int y = 289;
    private static int speed = 7;
    private static int attackingRange = 150;
    private static int room = 0;
    private static boolean canMove = true;
    private static boolean isAttacking = false;
    private final static boolean DEBUG = false;
    private final static int OFFSET_X = -64;
    private final static int OFFSET_Y = -54;
    public static GamePanel gamePanel;
    
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
                case 2: room = 3; break;
                case 5: room = 4; break;
                case 6: room = 7; break;
            }
        }
        else if (Direction.equals("South"))
        {
            switch (room)
            {
                case 3: room = 2; break;
                case 4: room = 5; break;
                case 7: room = 6; break;
            }
        }
        else if (Direction.equals("West"))
        {
            switch (room)
            {
                case 2: room = 1; break;
                case 4: room = 2; break;
                case 6: room = 4; break;
            }
        }
        else if (Direction.equals("East"))
        {
            switch (room)
            {
                case 1: room = 2; break;
                case 2: room = 4; break;
                case 4: room = 6; break;
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
    
    public static void die() 
    {
        Hotbar.addCoins(-2);
        moveToRoom(0);
        setHealth(getMaxHealth());
    }
    
    public static Image getModel()
    {
        if (canMove)
        {
            if (Main.getIsPressingW())
            {
                isAttacking = false;
                return PLAYER_WALKING_NORTH_MODEL;
            }
            else if (Main.getIsPressingA())
            {
                isAttacking = false;
                return PLAYER_WALKING_WEST_MODEL;
            }
            else if (Main.getIsPressingS())
            {
                isAttacking = false;
                return PLAYER_WALKING_SOUTH_MODEL;
            }
            else if (Main.getIsPressingD())
            {
                isAttacking = false;
                return PLAYER_WALKING_EAST_MODEL;
            }
            else if (Main.getLastButtonPressed() == "W" && !Main.getIsPressingSpace())
            {
                isAttacking = false;
                return PLAYER_STANDING_NORTH_MODEL;
            }
            else if (Main.getLastButtonPressed() == "A" && !Main.getIsPressingSpace())
            {
                isAttacking = false;
                return PLAYER_STANDING_WEST_MODEL;
            }
            else if (Main.getLastButtonPressed() == "S" && !Main.getIsPressingSpace())
            {
                isAttacking = false;
                return PLAYER_STANDING_SOUTH_MODEL;
            }   
            else if (Main.getLastButtonPressed() == "D" && !Main.getIsPressingSpace())
            {
                isAttacking = false;
                return PLAYER_STANDING_EAST_MODEL;
            }
            else if (Main.getLastButtonPressed() == "W" && Main.getIsPressingSpace())
            {
                isAttacking = true;
                return PLAYER_ATTACKING_NORTH_MODEL;
            }
            else if (Main.getLastButtonPressed() == "A" && Main.getIsPressingSpace())
            {
                isAttacking = true;
                return PLAYER_ATTACKING_WEST_MODEL;
            }
            else if (Main.getLastButtonPressed() == "S" && Main.getIsPressingSpace())
            {
                isAttacking = true;
                return PLAYER_ATTACKING_SOUTH_MODEL;
            }
            else if (Main.getLastButtonPressed() == "D" && Main.getIsPressingSpace())
            {
                isAttacking = true;
                return PLAYER_ATTACKING_EAST_MODEL;
            }
        }

        return PLAYER_STANDING_SOUTH_MODEL;
    }
    
    public static int getHealth()
    {
        if(health <= 0) 
        {
            Player.die();
        }
        return health;
    }
    
    public static int getMaxHealth()
    {   
        int baseMaxHealth = 100;
        int armorBonus = 0;
        
        if (Hotbar.getHelmet() != null) armorBonus += Hotbar.getHelmet().getFactor();
        if (Hotbar.getChestplate() != null) armorBonus += Hotbar.getChestplate().getFactor();
        if (Hotbar.getLeggings() != null) armorBonus += Hotbar.getLeggings().getFactor();
        if (Hotbar.getBoots() != null) armorBonus += Hotbar.getBoots().getFactor();
        if (Hotbar.getRobes() != null) armorBonus += Hotbar.getRobes().getFactor();
        
        return baseMaxHealth + armorBonus;
    }
    
    public static int getTotalArmorDefense()
    {
        int defense = 0;
        if (Hotbar.getHelmet() != null) defense += Hotbar.getHelmet().getFactor();
        if (Hotbar.getChestplate() != null) defense += Hotbar.getChestplate().getFactor();
        if (Hotbar.getLeggings() != null) defense += Hotbar.getLeggings().getFactor();
        if (Hotbar.getBoots() != null) defense += Hotbar.getBoots().getFactor();
        if (Hotbar.getRobes() != null) defense += Hotbar.getRobes().getFactor();
        return defense;
    }
    
    public static int getX() { return x; }
    public static int getY() { return y; }
    public static int getSpeed() { return speed; }
    public static int getAttackingRange() { return attackingRange; }
    public static int getRoom() { return room; }
    
    public static int getDamage() 
    {
        int totalDamage = baseDamage;
        if (Hotbar.getWeapon() != null) {
            totalDamage += Hotbar.getWeapon().getFactor();
        }
        return totalDamage;
    }
    
    // Calculates precise damage per weapon vs. enemy type to meet exact hit-count requirements
    public static int calculateDamage(String enemyType) 
    {
        if (enemyType == null) enemyType = "";
        enemyType = enemyType.toLowerCase().trim();
        
        String weaponName = "";
        if (Hotbar.getWeapon() != null) {
            weaponName = Hotbar.getWeapon().getClass().getSimpleName().toLowerCase();
        }
        
        boolean hasNoSword = (Hotbar.getWeapon() == null);
        boolean isSoldierSword = weaponName.contains("soldier") || weaponName.contains("knight");
        boolean isMosquitoSword = weaponName.contains("mosquito");
        boolean isEliteSword = weaponName.contains("elite");
        boolean isClub = weaponName.contains("club");
        
        int damage = 4;
        
        //Essentially how many hits it takes with each weapon to kill an enemy type. 
        switch (enemyType) {
            case "infarctus": // 20 HP
                if (hasNoSword) damage = 4;          // 5 hits
                else if (isSoldierSword) damage = 10; // 2 hits
                else if (isMosquitoSword) { damage = 20; healMosquito(); } // 1 hit + heal
                else if (isEliteSword) damage = 20;   // 1 hit
                else if (isClub) damage = 20;         // 1 hit
                else damage = 10;
                break;
                
            case "knight": // Soldier (50 HP)
            case "soldier":
                if (hasNoSword) damage = 5;          // 10 hits
                else if (isSoldierSword) damage = 10; // 5 hits
                else if (isMosquitoSword) { damage = 10; healMosquito(); } // 5 hits + heal
                else if (isEliteSword) damage = 25;   // 2 hits
                else if (isClub) damage = 50;         // 1 hit
                else damage = 10;
                break;
                
            case "eliteguard": // Elite Guard (100 HP)
                if (hasNoSword) damage = 7;          // ~15 hits
                else if (isSoldierSword) damage = 10; // 10 hits
                else if (isMosquitoSword) { damage = 10; healMosquito(); } // 10 hits + heal
                else if (isEliteSword) damage = 20;   // 5 hits
                else if (isClub) damage = 50;         // 2 hits
                else damage = 15;
                break;
                
            case "bovisfight": // Boss (200 HP)
            case "boss":
                if (hasNoSword) damage = 1;          // 200 hits
                else if (isSoldierSword) damage = 2;  // 100 hits
                else if (isMosquitoSword) { damage = 2; healMosquito(); } // 100 hits + heal
                else if (isEliteSword) damage = 4;    // 50 hits
                else if (isClub) damage = 8;          // 25 hits
                else damage = 2;
                break;
                
            default:
                damage = getDamage();
                break;
        }
        
        return damage;
    }
    
    private static void healMosquito() {
        int healAmount = (int)(getMaxHealth() * 0.10);
        setHealth(health + healAmount);
    }
    
    public static boolean getMoveState() { return canMove; }
    public static boolean getAttackingState() { return isAttacking; }
    public static int getCurrency() { return currency; }
    
    public static void setHealth(double playerHealth)
    {
        Player.health = Math.min((int) playerHealth, getMaxHealth());
        if (Player.health <= 0) 
        {
            Player.die();
        }
    }
    
    public static void setMaxHealth(int maxPlayerHealth)
    {
        Player.maxHealth = maxPlayerHealth;
    }
    
    // Decreases player's health with base damage minus total armor defense (minimum 1 DMG).
    public static void takeDamage(int rawDamage) 
    {
        if (rawDamage <= 0) return;
        int mitigatedDamage = Math.max(1, rawDamage - getTotalArmorDefense());
        Player.health -= mitigatedDamage;
        if (Player.health <= 0) 
        {
            Player.die();
        }
    }
    //this is just the collision stuff
    public static void moveNorth() { y -= speed; doWallCollision(); }
    public static void moveSouth() { y += speed; doWallCollision(); }
    public static void moveWest() { x -= speed; doWallCollision(); }
    public static void moveEast() { x += speed; doWallCollision(); }
    
    public static void setRoom(int room) { Player.room = room; }
    public static void setMoveState(boolean canMove) { Player.canMove = canMove; }
    
    public static void draw(Graphics g)
    {
        g.drawImage(getModel(), getX() + OFFSET_X, getY() + OFFSET_Y, null);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
    {
        return false;
    }
}