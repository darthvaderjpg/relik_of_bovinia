// Name: Siddarth Iyer
// Date: 10/13/2026
// Rev: 03
// Notes: Keys

import java.awt.Image;

public class Key extends Item {
    public static final Image SPIRIT_KEY = Tools.getNewImageIcon("Spirit Key.png");
    public static final Image TEMPLE_KEY = Tools.getNewImageIcon("Temple Key.png");

    private int keyValue;

    public Key(Image keyType, int keyValue) {
        super("Key", keyType);
        this.keyValue = keyValue;
    }

    public int getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }

    public static Key templeKey = new Key(TEMPLE_KEY, 1);
    public static Key spiritKey = new Key(SPIRIT_KEY, 2);
}