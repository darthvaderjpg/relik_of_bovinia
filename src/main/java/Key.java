//Name: Siddarth Iyer
//Date: 4/30/26
//Rev:01
//Notes: These are the weapon that the player can use.
// Base weapon damage is 2. Then damage scales with the weapon type.


//import javax.swing.*;

//import java.awt.Graphics;
import java.awt.Image;

public class Key extends Item {
	public static final Image SPIRIT_KEY = Tools.getNewImageIcon("spirit-key-pixilart.png");
	public static final Image TEMPLE_KEY = Tools.getNewImageIcon("special-key-pixilart.png");
	
	public Key(Image Keytype, int hitPoints, int type) {
		super(Keytype, hitPoints, "Key");
		
	}
	static Key spiritKey = new Key(SPIRIT_KEY, 2,7);
	
	static Key templeKey = new Key(TEMPLE_KEY, 2,6);
}
