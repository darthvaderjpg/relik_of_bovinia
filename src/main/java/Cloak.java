//Name: Siddarth Iyer
//Date: 4/30/26
//Rev:01
//Notes: This is the class that holds all the armor
//They're all objects because they're really similar

import java.awt.Image;

public class Cloak extends Item {

    public  final static Image    BOVINE_HELMET = Tools.getNewImageIcon("animal-helmet-pixilart.png");
	public  final static Image    AZURE_SHROUD = Tools.getNewImageIcon("new-cloak-pixilart.png");

    public Cloak(Image Armortype, int hitPoints, String type) {
		super(Armortype, hitPoints, type);
	}

    static Cloak AnimalHelmet = new Cloak(BOVINE_HELMET, 5, "Helmet");
	
	static Cloak azureShroud = new Cloak(AZURE_SHROUD, 40, "Robes");

}
