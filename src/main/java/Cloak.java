//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:02
//Notes: This is the class that holds all the armor/cloaks

import java.awt.Image;

public class Cloak extends Item {

    public final static Image BOVINE_HELMET = Tools.getNewImageIcon("animal-helmet-pixilart (1).png");
    public final static Image AZURE_SHROUD = Tools.getNewImageIcon("Azure Shroud.png");

    private int hp;

    public Cloak(String cloakName) {
        super("Cloak", Tools.getNewImageIcon(cloakName.toUpperCase().replace(" ", "_") + ".png"));
        this.hp = 10;
    }

    public Cloak(Image image, int hp, String type) {
        super(type, image);
        this.hp = hp;
    }

    @Override
    public int getFactor() {
        return hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    static Cloak azureShroud = new Cloak(AZURE_SHROUD, 40, "Robes");
}