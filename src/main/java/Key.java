//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:02
//Notes: This is the class that holds all the keys.
// I'll use it in a second but not yet.

import java.awt.Image;

public class Key extends Item {
    public static final Image SPIRIT_KEY = Tools.getNewImageIcon("spirit-key-pixilart.png");
    public static final Image TEMPLE_KEY = Tools.getNewImageIcon("special-key-pixilart.png");
    
    private int hitPoints;
    private int keyValue;

    public Key(Image keyType, int hitPoints, int keyValue) {
        super("Key", keyType);
        this.hitPoints = hitPoints;
        this.keyValue = keyValue;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }
    
    static Key spiritKey = new Key(SPIRIT_KEY, 2, 7);
    
    static Key templeKey = new Key(TEMPLE_KEY, 2, 6);
}