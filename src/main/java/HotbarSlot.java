//  Name: Siddarth Iyer
// Date:  10/11/2026
// Rev:01
// Notes: This class manages the player's inventory slots and rendering of stored inventory items.

public class HotbarSlot {

    private int x;
    private int y;
    private Item item;

    public HotbarSlot(int x, int y){
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

}
