//Name: Siddarth Iyer
//Editor: Ofer Koren
//Date: 5/14/2026
//Rev:28
//Notes: You lose your stuff upon death
//essentially a Rouguelike

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;

public class Hotbar implements ImageObserver {
    
    // Side and top UI models.
    private final static Image SIDE_UI_MODEL = Tools.getNewImageIcon("SIDE_UI_MODEL.gif");
    private final static Image NULL_UI_MODEL = Tools.getNewImageIcon("NULL_UI_MODEL.png");
    public final static Image POTION_MODEL = Tools.getNewImageIcon("revised-b-to-buy-potions-pixilart (1).png");
    
    // Potion Image
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
    
    // Player initial hotbar counts
    private static int coins = 0;
    private static int potionCount = 0;
    
    // Hotbar slots
    private static HotbarSlot[] hotbar = new HotbarSlot[3];

    static {
        hotbar[0] = new HotbarSlot(810, 262);  // Slot 1: Left (Weapon)
        hotbar[1] = new HotbarSlot(915, 275);  // Slot 2: Center (Potion)
        hotbar[2] = new HotbarSlot(989, 262); // Slot 3: Right (Cloak)
    }
    
    private static Weapon weapon;
    private static Armor helmet;
    private static Armor chestplate;
    private static Armor leggings; 
    private static Armor boots;
    private static Armor cloaks;

    public static void updateHotbar(Item i) {
        if (i == null) return;
        
        String type = (i.getType() != null) ? i.getType().toLowerCase() : "";
        String str = i.toString().toLowerCase();

        // --- ARMOR STAT COMPARISON ---
        if (i instanceof Armor || type.contains("helm") || type.contains("chest") || type.contains("pant") || type.contains("boot") || type.contains("armor")) {
            Armor armor = (i instanceof Armor) ? (Armor) i : null;
            int newFactor = (armor != null) ? armor.getFactor() : i.getFactor();
            boolean upgraded = false;

            if (type.contains("helm") || type.contains("head") || type.contains("cap") || str.contains("helm")) {
                if (helmet == null || newFactor > helmet.getFactor()) {
                    setHelmet(armor);
                    Inventory.setSlot(0, i);
                    upgraded = true;
                }
            } 
            else if (type.contains("chest") || type.contains("plate") || type.contains("cuirass") || str.contains("chest")) {
                if (chestplate == null || newFactor > chestplate.getFactor()) {
                    setChestplate(armor);
                    Inventory.setSlot(1, i);
                    upgraded = true;
                }
            } 
            else if (type.contains("pant") || type.contains("leg") || type.contains("bottom") || type.contains("explorer") || str.contains("pants")) {
                if (leggings == null || newFactor > leggings.getFactor()) {
                    setLeggings(armor);
                    Inventory.setSlot(2, i);
                    upgraded = true;
                }
            } 
            else if (type.contains("boot") || type.contains("shoe") || str.contains("boot")) {
                if (boots == null || newFactor > boots.getFactor()) {
                    setBoots(armor);
                    Inventory.setSlot(3, i);
                    upgraded = true;
                }
            } 
            else {
                if (chestplate == null || newFactor > chestplate.getFactor()) {
                    setChestplate(armor);
                    Inventory.setSlot(1, i); 
                    upgraded = true;
                }
            }
            return;
        }

        // --- WEAPON STAT COMPARISON ---
        if (i instanceof Weapon || type.contains("weapon") || type.contains("sword") || type.contains("club")) {
            Weapon newWeapon = (i instanceof Weapon) ? (Weapon) i : null;
            int newFactor = (newWeapon != null) ? newWeapon.getFactor() : i.getFactor();
            int currentFactor = (weapon != null) ? weapon.getFactor() : 0;

            if (weapon == null || newFactor > currentFactor) {
                hotbar[0].updateItem(i);
                if (newWeapon != null) setWeapon(newWeapon);
            }
        } 
        else if (i instanceof Cloak || type.contains("cloak") || type.contains("shroud")) {
            hotbar[2].updateItem(i);
            Armor cloakArmor = (i instanceof Armor) ? (Armor) i : null;
            setCloaks(cloakArmor);
            
        } 
        else if (i instanceof Key || type.contains("key")) {
            Keybar.addKey(i);
        }
        else if (type.contains("potion")) {
            hotbar[1].updateItem(i);
        }
    }
    
    public static int getCoins() { return coins; }
    
    //Minimap models
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
    
    public static Image getHealthModel(int health, int maxHealth, String prefix) {
        if (maxHealth <= 0) maxHealth = 100;
        double ratio = (double) health / (double) maxHealth;
        if (ratio < 0) ratio = 0;
        if (ratio > 1) ratio = 1;
        
        int healthPercent = (int)(ratio * 100);
        int roundedHealthPercent = ((int)(healthPercent / 10)) * 10;
        return Tools.getNewImageIcon(prefix + roundedHealthPercent + "_PERCENT.png");
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
    public static void setCloaks(Armor cloaks) { Hotbar.cloaks = cloaks; }
    
    public static Weapon getWeapon() { return weapon; }
    public static Armor getHelmet() { return helmet; }
    public static Armor getChestplate() { return chestplate; }
    public static Armor getLeggings() { return leggings; }
    public static Armor getBoots() { return boots; }
    public static Armor getCloaks() { return cloaks; }
    
    // Clear method for death penalty wipe
    public static void clear() {
        potionCount = 0;
        for (int i = 0; i < hotbar.length; i++) {
            if (hotbar[i] != null) {
                hotbar[i].updateItem(null);
            }
        }
        weapon = null;
        helmet = null;
        chestplate = null;
        leggings = null;
        boots = null;
        cloaks = null;
    }
    
    public static void draw(Graphics g) {
        //Gets the current area your in
        Image currentMap = getMapModel();
        if (currentMap != null) {
            g.drawImage(currentMap, 794, 60, 140, 140, null);
        }

        // --- DRAW HEALTH BAR ---
        Image healthImg = getHealthModel(Player.getHealth(), Player.getMaxHealth(), "PLAYER_HEALTH_");
        if (healthImg != null) {
            g.drawImage(healthImg, 808, 210, null);
        }

        if (POTION_MODEL != null) {
            g.drawImage(POTION_MODEL, 260, 40, null);
        }

        if (BovisFight.getBossToken() && Player.getRoom() == 8) {
            g.setColor(Color.BLACK);
            g.fillRect(260, 140, 276, 40);
        }

        // Draw hotbar slots (Weapon & Cloak)
        for (int i = 0; i < hotbar.length; i++) {
            if (i == 1) continue; // Potion handled separately below
            HotbarSlot slot = hotbar[i];
            if (slot != null && slot.getItem() != null && slot.getItem().getImage() != null) {
                g.drawImage(slot.getItem().getImage(), slot.getX(), slot.getY(), null);
            }
        }

        // Draw potion slot (Slot 2)
        if (potionCount > 0 && hotbar[1] != null) {
            int slotX = hotbar[1].getX();
            int slotY = hotbar[1].getY();
            
            if (POTION_ICON != null) {
                g.drawImage(POTION_ICON, slotX, slotY, null);
            } else {
                g.setColor(Color.RED);
                g.fillRect(slotX, slotY, 32, 32);
                g.setColor(Color.BLACK);
                g.drawRect(slotX, slotY, 32, 32);
            }

            if (potionCount > 99) { potionCount = 99; }
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Press Start 2P", Font.BOLD, 10));
            g.drawString("x" + potionCount, slotX + 34, slotY + 22);
        }

        // --- DRAW INVENTORY & KEYBAR ---
        Inventory.draw(g); 
        Keybar.draw(g);
    }
    
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

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