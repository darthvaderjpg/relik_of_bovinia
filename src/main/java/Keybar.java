// Author : Siddarth Iyer
// Date   : 10/18/2026
// Rev    : 03
// Notes  : Keybar HUD inventory manager.

import java.awt.Graphics;

public class Keybar {

    private int x;
    private int y;
    private Item item;

    private static Keybar[] keybar = new Keybar[2];

    static {
        keybar[0] = new Keybar(900, 564);
        keybar[1] = new Keybar(989, 564);
    }

    public Keybar(int x, int y) {
        this.x = x;
        this.y = y;
        this.item = null;
    }

    public static void setBar(int index, Item item) {
        if (index >= 0 && index < keybar.length && keybar[index] != null) {
            keybar[index].updateItem(item);
        }
    }

    public static Item getSlot(int index) {
        if (index >= 0 && index < keybar.length && keybar[index] != null) {
            return keybar[index].getItem();
        }
        return null;
    }

    public void updateItem(Item item) {
        this.item = item;
    }

    public static void clear()
    {
    // Clear all key slots
    setBar(0, null);
    setBar(1, null);
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

    public boolean isEmpty() {
        return item == null;
    }

    public static void addKey(Item key) {
        if (key == null) return;
        if (keybar[0].getItem() == null) {
            keybar[0].updateItem(key);
        } else if (keybar[1].getItem() == null) {
            keybar[1].updateItem(key);
        }
    }

    public static Keybar[] getSlots() {
        return keybar;
    }

    public static void draw(Graphics g) {
        for (Keybar slot : keybar) {
            if (slot != null && slot.getItem() != null && slot.getItem().getImage() != null) {
                g.drawImage(slot.getItem().getImage(), slot.getX(), slot.getY(), null);
            }
        }
    }
}