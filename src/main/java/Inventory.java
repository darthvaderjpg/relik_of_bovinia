// Author : Siddarth Iyer / Ofer Koren
//Date  : 10/13/2026
//Rev   : 01
// Notes: none

import java.awt.Graphics;
import java.awt.Image;

public class Inventory {
    
    // 4 distinct, standalone slot instances
    private static InventorySlot helmetSlot;
    private static InventorySlot chestplateSlot;
    private static InventorySlot leggingsSlot;
    private static InventorySlot bootsSlot;

    // Explicitly configure the exact (X, Y) pixel coordinates for each individual slot
    static {
        helmetSlot     = new InventorySlot(823, 410); // Slot 0: Helmet
        chestplateSlot = new InventorySlot(823, 488); // Slot 1: Chestplate
        leggingsSlot   = new InventorySlot(823, 577); // Slot 2: Leggings
        bootsSlot      = new InventorySlot(823, 664); // Slot 3: Boots
    }

    public static void setSlot(int index, Item item) {
        switch (index) {
            case 0:
                helmetSlot.updateItem(item);
                break;
            case 1:
                chestplateSlot.updateItem(item);
                break;
            case 2:
                leggingsSlot.updateItem(item);
                break;
            case 3:
                bootsSlot.updateItem(item);
                break;
        }
    }

 public static void clear() {
    // Loop through all inventory slots and set them to null
    for (int i = 0; i < 4; i++) { // Adjust 20 if your inventory has more or fewer slots
        setSlot(i, null);
    }
}
    public static Item getSlot(int index) {
        switch (index) {
            case 0: return helmetSlot.getItem();
            case 1: return chestplateSlot.getItem();
            case 2: return leggingsSlot.getItem();
            case 3: return bootsSlot.getItem();
            default: return null;
        }
    }
    // Draws all 4 items where they are supposed to be. 
    public static void draw(Graphics g) {
        helmetSlot.draw(g);
        chestplateSlot.draw(g);
        leggingsSlot.draw(g);
        bootsSlot.draw(g);
    }

    // --- Inner InventorySlot Class ---
    public static class InventorySlot {
        private int x;
        private int y;
        private Item item;
        private static final int SLOT_SIZE = 42;

        public InventorySlot(int x, int y) {
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

        public int getX() { return x; }
        public int getY() { return y; }

        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }

        public void draw(Graphics g) {
            if (item != null && item.getImage() != null) {
                Image img = item.getImage();
                
                int imgWidth = img.getWidth(null);
                int imgHeight = img.getHeight(null);
                
                if (imgWidth <= 0) imgWidth = 32;
                if (imgHeight <= 0) imgHeight = 32;
                
                // Centering math calculated specifically for this slot box
                int drawX = x + (SLOT_SIZE - imgWidth) / 2;
                int drawY = y + (SLOT_SIZE - imgHeight) / 2;
                
                g.drawImage(img, drawX, drawY, null);
            }
        }
    }
}