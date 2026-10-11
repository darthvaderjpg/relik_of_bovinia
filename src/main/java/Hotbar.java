//Name: Siddarth Iyer
//Editor: Ofer Koren
//Date: 5/14/2026
//Rev:01
//Notes:
/*
- This class Stores everything the hotbar needs: 
- 1. The Minimap
- 2. The Armor Display
- 3. The Key display
- 4. The Health Bar
- 5. The potion's display and functionality
 */

import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Hotbar implements ImageObserver {
    
    // Side and top UI models.
    private final static Image SIDE_UI_MODEL = Tools.getNewImageIcon("SIDE_UI_MODEL.gif");
    private final static Image NULL_UI_MODEL = Tools.getNewImageIcon("NULL_UI_MODEL.png");
    public final static Image POTION_MODEL = Tools.getNewImageIcon("revised-b-to-buy-potions-pixilart (1).png");
    
    // Mini-map models (gif).
    private final static Image MINI_MAP_ROOM_0 = Tools.getNewImageIcon("MAP_ROOM_0.gif");
    private final static Image MINI_MAP_ROOM_1 = Tools.getNewImageIcon("MAP_ROOM_1.gif");
    private final static Image MINI_MAP_ROOM_2 = Tools.getNewImageIcon("MAP_ROOM_2.gif");
    private final static Image MINI_MAP_ROOM_3 = Tools.getNewImageIcon("MAP_ROOM_3.gif");
    private final static Image MINI_MAP_ROOM_4 = Tools.getNewImageIcon("MAP_ROOM_4.gif");
    private final static Image MINI_MAP_ROOM_5 = Tools.getNewImageIcon("MAP_ROOM_5.gif");
    private final static Image MINI_MAP_ROOM_6 = Tools.getNewImageIcon("MAP_ROOM_6.gif");
    private final static Image MINI_MAP_ROOM_7 = Tools.getNewImageIcon("MAP_ROOM_7.gif");
    private final static Image MINI_MAP_ROOM_8 = Tools.getNewImageIcon("MAP_ROOM_8.gif");
    
    // Player stats
    private static int coins = 0;
    private static int potionCount = 0;
    
    // Dedicated Hotbar array (3 slots: Sword, Potion, Cloak)
    private static HotbarSlot[] hotbar = new HotbarSlot[3];

    // Static initialization block for the 3 hotbar slots
    static {
        hotbar[0] = new HotbarSlot(700, 100); // Slot 1: Sword
        hotbar[1] = new HotbarSlot(750, 100); // Slot 2: Potion
        hotbar[2] = new HotbarSlot(800, 100); // Slot 3: Cloak
    }
    
    // Legacy equipment references
    private static Weapon weapon;
    private static Armor helmet;
    private static Armor chestplate;
    private static Armor leggings; 
    private static Armor boots;
    private static Armor robes;

    // This updates the hotbar, inventory, or keys based on what item is collected
 public static void updateHotbar(Item i) {
        if (i instanceof Weapon) {
            hotbar[0].updateItem(i); // Sword always in hotbar slot 1
            setWeapon((Weapon) i);
        } 
        else if (i instanceof Armor) {
            Armor armor = (Armor) i;
            String armorType = armor.getType();
            
            if (armorType.equalsIgnoreCase("helmet")) {
                setHelmet(armor);
                Inventory.setSlot(0, armor); // Inventory slot 1: Helmet
            } 
            else if (armorType.equalsIgnoreCase("chestplate") || armorType.equalsIgnoreCase("sweater")) {
                setChestplate(armor);
                Inventory.setSlot(1, armor); // Inventory slot 2: Chestplate/Sweater
            } 
            else if (armorType.equalsIgnoreCase("leggings")) {
                setLeggings(armor);
                Inventory.setSlot(2, armor); // Inventory slot 3: Leggings
            } 
            else if (armorType.equalsIgnoreCase("boots")) {
                setBoots(armor);
                Inventory.setSlot(3, armor); // Inventory slot 4: Boots
            } 
            else if (armorType.equalsIgnoreCase("robes")) {
                setRobes(armor);
            }
        } 
       else if (i instanceof Key) {
            Keybar.addKey((Key) i);
        }
        else {
            // Check item type by string name for potions, cloaks, etc.
            String itemType = i.getType();
            if (itemType != null) {
                if (itemType.equalsIgnoreCase("potion")) {
                    hotbar[1].updateItem(i); // Potion always in hotbar slot 2
                } 
                else if (itemType.equalsIgnoreCase("cloak")) {
                    hotbar[2].updateItem(i); // Cloak always in hotbar slot 3
                }
            }
        }
    }
    
    public static int getCoins() {
        return coins;
    }
    
    public static Image getMapModel() {
        switch (Player.getRoom()) {
            case 0: return MINI_MAP_ROOM_0;
            case 1: return MINI_MAP_ROOM_1;
            case 2: return MINI_MAP_ROOM_2;
            case 3: return MINI_MAP_ROOM_3;
            case 4: return MINI_MAP_ROOM_4;
            case 5: return MINI_MAP_ROOM_5;
            case 6: return MINI_MAP_ROOM_6;
            case 7: return MINI_MAP_ROOM_7;
            case 8: return MINI_MAP_ROOM_8;
            default: return MINI_MAP_ROOM_0;
        }
    }
    
    public static Image getSideUIModel() {
        return SIDE_UI_MODEL;
    }
    
    public static Image getNullUIModel() {
        return NULL_UI_MODEL;
    }
    
    // Rounds health percentage and returns the appropriate health bar image.
    public static Image getHealthModel(int health, int maxHealth, String type) {
        double ratio = (double)health / (double)maxHealth;
        int healthPercent = (int)(ratio * 100);
        int roundedHealthPercent = ((int)(healthPercent/10)) * 10;
        
        Image healthbar = (new ImageIcon(type + roundedHealthPercent + "_PERCENT.png")).getImage();
        return healthbar;
    } 
    
    public static int getPotionCount() {
        return potionCount;
    }

    public static Image getPotionModel() {
        return POTION_MODEL;
    }
    
    public static void usePotion() {
        if(potionCount >= 1) {
            Player.setHealth(Player.getHealth() + ((Player.getMaxHealth() - Player.getHealth()) * 0.5));
            potionCount--;
        }
    }
    
    public static void increasePotionCount() {
        potionCount++;
    }
    
    public static void addCoins(int increase) {
        if (coins + increase > 99) {
            coins = 99;
        } else if (coins + increase < 0) {
            coins = 0;
        } else {
            coins += increase;
        }
    }
    
    public static void setWeapon(Weapon weapon) { Hotbar.weapon = weapon; }
    public static void setHelmet(Armor helmet) { Hotbar.helmet = helmet; }
    public static void setChestplate(Armor chestplate) { Hotbar.chestplate = chestplate; }
    public static void setLeggings(Armor leggings) { Hotbar.leggings = leggings; }
    public static void setBoots(Armor boots) { Hotbar.boots = boots; }
    public static void setRobes(Armor robes) { Hotbar.robes = robes; }
    
    public static Weapon getWeapon() { return weapon; }
    public static Armor getHelmet() { return helmet; }
    public static Armor getChestplate() { return chestplate; }
    public static Armor getLeggings() { return leggings; }
    public static Armor getBoots() { return boots; }
    public static Armor getRobes() { return robes; }
    
    public static void draw(Graphics g) {
        // ONLY DRAW ITEMS
        for (HotbarSlot slot : hotbar) {
            if (slot != null && slot.getItem() != null && slot.getItem().getImage() != null) {
                g.drawImage(slot.getItem().getImage(), slot.getX(), slot.getY(), null);
            }
        }
    }
    
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}