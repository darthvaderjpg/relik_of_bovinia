    //Siddarth Iyer
    // Date:  10/11/2026
    //Rev : 01
    //Notes : This class is responsible for creating the keybar objects.
    // The keybar is essentially the place for the keys
    
    import java.awt.Graphics;

    public class Keybar {
   
        private int x;
        private int y;
        private Item item;

    // Static array holding your two key slots
    private static Keybar[] keybar = new Keybar[2];

    static {
        // Initialize the two key slots with their coordinates under the KEYS text
        keybar[0] = new Keybar(750, 500);
        keybar[1] = new Keybar(800, 500);
    }

    public Keybar(int x, int y){
        this.x = x;
        this.y = y;
        this.item = null;
    }

    public void updateItem(Item item) {
        this.item = item;
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
        if (keybar[0].getItem() == null) {
            keybar[0].updateItem(key);
        } else {
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