// Name: Siddarth Iyer
// Editor: Ofer Koren
// Date: 10/26/2026
// Rev: 36
// Notes: None

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Keybar {
    
    
    private static KeySlot[] keySlots = {
        new KeySlot(915, 577),
        new KeySlot(998, 577)  
    };
    
    private static ArrayList<Item> keys = new ArrayList<Item>();

    public static ArrayList<Item> getKeys() {
        return keys;
    }

    public static void addKey(Item i) {
        if (i == null) return;
        if (keys.contains(i)) return;
        
        keys.add(i);
        
        for (KeySlot slot : keySlots) {
            if (slot.getItem() == null) {
                slot.setItem(i);
                break;
            }
        }
    }

    public static boolean hasKey(String name) {
        if (name == null) return false;
        for (Item k : keys) {
            if (k != null) {
                String str = k.toString().toLowerCase();
                String simpleName = k.getClass().getSimpleName().toLowerCase();
                
                if (str.contains(name.toLowerCase()) || simpleName.contains(name.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasKey(Item item) {
        return keys.contains(item);
    }

    public static Item getSlot(int index) {
        if (index >= 0 && index < keySlots.length) {
            return keySlots[index].getItem();
        }
        return null;
    }

    public static void setBar(int index, Item item) {
        if (index >= 0 && index < keySlots.length) {
            keySlots[index].setItem(item);
            if (item == null && index < keys.size()) {
                keys.remove(index);
            }
        }
    }

    public static void clear() {
        keys.clear();
        for (KeySlot slot : keySlots) {
            slot.setItem(null);
        }
    }

    public static void draw(Graphics g) {
        for (KeySlot slot : keySlots) {
            Item keyItem = slot.getItem();
            if (keyItem != null) {
                Image keyImg = keyItem.getImage();
                if (keyImg != null) {
                    g.drawImage(keyImg, slot.getX(), slot.getY(), 30, 30, null);
                } else {
                    g.setColor(java.awt.Color.YELLOW);
                    g.fillRect(slot.getX(), slot.getY(), 30, 30);
                    g.setColor(java.awt.Color.BLACK);
                    g.drawRect(slot.getX(), slot.getY(), 30, 30);
                }
            }
        }
    }

    public static class KeySlot {
        private int x;
        private int y;
        private Item item;

        public KeySlot(int x, int y) {
            this.x = x;
            this.y = y;
            this.item = null;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public Item getItem() { return item; }
        public void setItem(Item item) { this.item = item; }
    }
}