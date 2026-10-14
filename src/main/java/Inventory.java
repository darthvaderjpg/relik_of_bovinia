//Name: Siddarth Iyer
//Date:  10/11/2026
//Rev:01
//Notes: This class manages the player's inventory slots and rendering of stored inventory items.
/*
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Inventory implements ImageObserver {
    
    private static HotbarSlot[] slots = new HotbarSlot[4];

    static {

        slots[0] = new HotbarSlot(820, 200); // Slot 1: Helmet
        slots[1] = new HotbarSlot(870, 200); // Slot 2: Chestplate
        slots[2] = new HotbarSlot(920, 200); // Slot 3: Leggings
        slots[3] = new HotbarSlot(970, 200); // Slot 4: Boots
    
    }

    public static void setSlot(int index, Item item) {
        if (index >= 0 && index < slots.length) {
            slots[index].updateItem(item);
        }
    }

    public static Item getSlot(int index) {
        if (index >= 0 && index < slots.length) {
            return slots[index].getItem();
        }
        return null;
    }

    public static void draw(Graphics g) {
        for (HotbarSlot slot : slots) {
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
*/
//Name: Siddarth Iyer
//Editor: Ofer Koren
//Date: 5/14/2026
//Rev:02
//Notes:
/*
- Manages inventory slots for armor and equipment items.
 */

import java.awt.Graphics;
import java.awt.Image;

public class Inventory {
    
    // Inventory slots array (e.g., 0: Helmet, 1: Chestplate, 2: Leggings, 3: Boots, 4: Robes, etc.)
    private static Item[] inventorySlots = new Item[5];

    public static void setSlot(int index, Item item) {
        if (index >= 0 && index < inventorySlots.length) {
            inventorySlots[index] = item;
        }
    }

    public static Item getSlot(int index) {
        if (index >= 0 && index < inventorySlots.length) {
            return inventorySlots[index];
        }
        return null;
    }

    public static void draw(Graphics g) {
        // Draw inventory items in their designated slots
        // Coordinates can be adjusted to match your inventory screen layout UI positions
        int startX = 100; // Example inventory X starting coordinate
        int startY = 100; // Example inventory Y starting coordinate
        int slotSize = 40;

        for (int i = 0; i < inventorySlots.length; i++) {
            Item item = inventorySlots[i];
            if (item != null && item.getImage() != null) {
                // Renders inventory items linearly or at custom slot coordinates
                g.drawImage(item.getImage(), startX + (i * (slotSize + 10)), startY, null);
            }
        }
    }
}