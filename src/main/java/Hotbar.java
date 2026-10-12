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
import java.awt.Color;
import java.awt.Font;

public class Hotbar implements ImageObserver {
    
    // Side and top UI models.
    private final static Image SIDE_UI_MODEL = Tools.getNewImageIcon("SIDE_UI_MODEL.gif");
    private final static Image NULL_UI_MODEL = Tools.getNewImageIcon("NULL_UI_MODEL.png");
    public final static Image POTION_MODEL = Tools.getNewImageIcon("revised-b-to-buy-potions-pixilart (1).png");
    
    // Dedicated potion icon for hotbar slot 2 with correct spelling
    private final static Image POTION_ICON = Tools.getNewImageIcon("rejuvenating-spirit-pixilart.png");
    
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
    
    // Dedicated Hotbar array (3 slots: Weapon, Potion, Cloak)
    private static HotbarSlot[] hotbar = new HotbarSlot[3];

    // Static initialization block with perfected coordinates
    static {
        hotbar[0] = new HotbarSlot(820, 255); // Slot 1: Left (Weapon)
        hotbar[1] = new HotbarSlot(915, 275); // Slot 2: Center (Potion)
        hotbar[2] = new HotbarSlot(960, 255); // Slot 3: Right (Cloak)
    }
    
    // Legacy equipment references
    private static Weapon weapon;
    private static Armor helmet;
    private static Armor chestplate;
    private static Armor leggings; 
    private static Armor boots;
    private static Armor robes;

    public static void updateHotbar(Item i) {
        if (i == null) return;
        
        if (i instanceof Weapon) {
            hotbar[0].updateItem(i);
            setWeapon((Weapon) i);
            return;
        }
        if (i instanceof Cloak) {
            hotbar[2].updateItem(i);
            return;
        }
        if (i instanceof Key) {
            Keybar.addKey(i);
            return;
        }
        
        String type = i.getType();
        
        if (i instanceof Armor) {
            Armor armor = (Armor) i;
            if (type != null && type.equalsIgnoreCase("Helmet")) {
                setHelmet(armor);
                Inventory.setSlot(0, i);
            } 
            else if (type != null && type.equalsIgnoreCase("Chestplate")) {
                setChestplate(armor);
                Inventory.setSlot(1, i);
            } 
            else if (type != null && type.equalsIgnoreCase("Leggings")) {
                setLeggings(armor);
                Inventory.setSlot(2, i);
            } 
            else if (type != null && type.equalsIgnoreCase("Boots")) {
                setBoots(armor);
                Inventory.setSlot(3, i);
            } 
            else if (type != null && type.equalsIgnoreCase("Robes")) {
                setRobes(armor);
            }
            return;
        }

        if (type == null) return;
        
        if (type.equalsIgnoreCase("Weapon")) {
            hotbar[0].updateItem(i);
            if (i instanceof Weapon) setWeapon((Weapon) i);
        } 
        else if (type.equalsIgnoreCase("Potion")) {
            hotbar[1].updateItem(i);
        } 
        else if (type.equalsIgnoreCase("Cloak")) {
            hotbar[2].updateItem(i);
        } 
        else if (type.equalsIgnoreCase("Key")) {
            Keybar.addKey(i);
        }
    }
    
    public static int getCoins() { return coins; }
    
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
    
    public static Image getSideUIModel() { return SIDE_UI_MODEL; }
    public static Image getNullUIModel() { return NULL_UI_MODEL; }
    
    public static Image getHealthModel(int health, int maxHealth, String type) {
        double ratio = (double)health / (double)maxHealth;
        int healthPercent = (int)(ratio * 100);
        int roundedHealthPercent = ((int)(healthPercent/10)) * 10;
        return (new ImageIcon(type + roundedHealthPercent + "_PERCENT.png")).getImage();
    } 
    
    public static int getPotionCount() { return potionCount; }
    public static Image getPotionModel() { return POTION_MODEL; }
    
    public static void usePotion() {
        if (potionCount >= 1) {
            Player.setHealth(Player.getHealth() + ((Player.getMaxHealth() - Player.getHealth()) * 0.5));
            potionCount--;
            if (potionCount <= 0 && hotbar[1] != null) {
                hotbar[1].updateItem(null);
            }
        }
    }
    
    public static void increasePotionCount() { 
        potionCount++; 
    }
    
    public static void addCoins(int increase) {
        if (coins + increase > 99) { coins = 99; }
        else if (coins + increase < 0) { coins = 0; }
        else { coins += increase; }
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
        // 1. Draw "B TO BUY POTIONS" banner in top header
        if (POTION_MODEL != null) {
            g.drawImage(POTION_MODEL, 260, 40, null);
        }

        // 2. Draw standard hotbar slot items (slots 0 and 2)
        for (int i = 0; i < hotbar.length; i++) {
            if (i == 1) continue; // Skip slot 1 so we render our potion icon & count cleanly
            HotbarSlot slot = hotbar[i];
            if (slot != null && slot.getItem() != null && slot.getItem().getImage() != null) {
                g.drawImage(slot.getItem().getImage(), slot.getX(), slot.getY(), null);
            }
        }

        // 3. Draw Potion Icon & `x#` count text in Slot 2 when potionCount > 0
        if (potionCount > 0 && hotbar[1] != null) {
            int slotX = hotbar[1].getX();
            int slotY = hotbar[1].getY();
            
            if (POTION_ICON != null) {
                g.drawImage(POTION_ICON, slotX, slotY, null);
            } else {
                // Fallback red square if asset fails to load
                g.setColor(Color.RED);
                g.fillRect(slotX, slotY, 32, 32);
                g.setColor(Color.BLACK);
                g.drawRect(slotX, slotY, 32, 32);
            }
            
            // Draw tiny count text beside the icon formatted as x#
            g.setColor(Color.BLACK);
            g.setFont(new Font("Press Start 2P", Font.BOLD, 10));
            g.drawString("x" + potionCount, slotX + 34, slotY + 22);
        }

        // 4. Draw Inventory items & Keybar items
        Inventory.draw(g);
        Keybar.draw(g);
    }
    
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

    // --- Inner HotbarSlot Class to guarantee resolution ---
    public static class HotbarSlot {
        private int x;
        private int y;
        private Item item;

        public HotbarSlot(int x, int y) {
            this.x = x;
            this.y = y;
            this.item = null;
        }

        public void updateItem(Item i) {
            this.item = i;
            if (i != null && i.getImage() != null) {
                // Keep item image tracked if needed
            }
        }

        public Item getItem() {
            return item;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}