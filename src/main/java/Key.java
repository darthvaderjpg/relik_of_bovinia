//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:02
//Notes: This is the class that holds all the keys.
// The time has come.

import java.awt.Image;

public class Key extends Item {
    public static final Image SPIRIT_KEY = Tools.getNewImageIcon("spirit-key-pixilart.png");
    public static final Image TEMPLE_KEY = Tools.getNewImageIcon("special-key-pixilart.png");
    

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

     static Key templeKey = new Key(TEMPLE_KEY, 1);

    static Key spiritKey = new Key(SPIRIT_KEY, 2);
    
   
}