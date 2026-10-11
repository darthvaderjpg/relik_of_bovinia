public class Inventory {
    private static HotbarSlot[] slots = new HotbarSlot[4];

    static {
        slots[0] = new HotbarSlot(700, 200); // Slot 1: Helmet
        slots[1] = new HotbarSlot(700, 250); // Slot 2: Chestplate/Sweater
        slots[2] = new HotbarSlot(700, 300); // Slot 3: Leggings
        slots[3] = new HotbarSlot(700, 350); // Slot 4: Boots
    }

    public static void setSlot(int index, Item item) {
        if (index >= 0 && index < slots.length) {
            slots[index].updateItem(item);
        }
    }

    public static HotbarSlot[] getSlots() {
        return slots;
    }
}