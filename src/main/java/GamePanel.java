// Author : Ofer Koren
// Co Editor: Jake Jiang SUB TO SCRAPNEL!!!
// Date   : 5/19/2026
// Rev    : 01
// Notes  : This class is responsible for creating the background, player, and NPC objects.

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	// Fields.
	
	// I have no idea what this is, I just used it to get Eclipse to stop showing the disclaimer.
	private static final long serialVersionUID = 1L;
	
	// Background creation.
	Map background = new Map();
	
	// NPC creation.
	NPC dude = new Dude();
	NPC grandpa = new Grandpa();
	NPC shopkeeper = new Shop();
	NPC relik = new Relik();
	NPC bovisTalk = new BovisTalk();
	
	// Numbers (UI) creation.
	Numbers numbers1 = new Numbers(949, 16, "Ruby");
	Numbers numbers2 = new Numbers(590, 250, "Shop");
	
	
	// Enemy creation.
	// Room 2 below
	Infarctus infarctus1 = new Infarctus(0, 200, 2);
	Infarctus infarctus2 = new Infarctus(0, 467, 2);
	Infarctus infarctus3 = new Infarctus(300, 590, 2);
	Infarctus infarctus4 = new Infarctus(501, 200, 2);
	Infarctus infarctus5 = new Infarctus(767, 567, 2);
	// Room 3 below -- Room 2 above
	Infarctus infarctus6 = new Infarctus(150, 603, 3);
	Knight knight1 = new Knight(203, 200, 3);
	// room 4 (room 3 above)
	Knight knight2 = new Knight(200, 323, 4);	
	Knight knight3 = new Knight(268, 398, 4);
	Knight knight4 = new Knight(268, 548, 4);	
	Infarctus infarctus7 = new Infarctus(0, 323, 4);
	EliteGuard eliteGuard1 = new EliteGuard(500, 200, 4);
	// room 6 (room 4 above) (room 5 is artifact room)
	Infarctus infarctus8 = new Infarctus(200, 425, 6);
	Infarctus infarctus9 = new Infarctus(280, 375,6);
	Infarctus infarctus10 = new Infarctus(280, 475, 6);
	Infarctus infarctus11 = new Infarctus(360, 325, 6);
	Infarctus infarctus12 = new Infarctus(360, 425, 6);
	Infarctus infarctus13 = new Infarctus(360, 525, 6);
	EliteGuard eliteGuard2 = new EliteGuard(700, 425, 6);
	EliteGuard eliteGuard3 = new EliteGuard(800, 425, 6);
	Knight knight5 = new Knight(-100, 800, 6);
	Knight knight6 = new Knight(-100, 700, 6);
	//room 7 (room 6 above)
	EliteGuard eliteGuard4 = new EliteGuard(200, 200, 7); 
	EliteGuard eliteGuard5 = new EliteGuard(400, 200, 7);
	EliteGuard eliteGuard6 = new EliteGuard(600, 200, 7); 
	EliteGuard eliteGuard7 = new EliteGuard(800, 200, 7); 
	Knight knight7 = new Knight(600, 320, 7);
	Knight knight8 = new Knight(580, 300, 7);

	
	// Bovis creation.
	static BovisFight bovis = new BovisFight(800, 400, 8);
	
	// Methods.
	
	// Controls painting/repainting and object behavior.
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
//		System.out.println("(" + Player.getX() + ", " + Player.getY() + ")");
		// Background drawing.
		background.draw(g);
		
		// NPC drawing.
		dude.draw(g);
		grandpa.draw(g);
		shopkeeper.draw(g);
		relik.draw(g);
		bovisTalk.draw(g);
		
		// Enemy drawing.
		infarctus1.draw(g);
		infarctus2.draw(g);
		infarctus3.draw(g);
		infarctus4.draw(g);
		infarctus5.draw(g);
		infarctus6.draw(g);
		infarctus7.draw(g);
		infarctus8.draw(g);
		infarctus9.draw(g);
		infarctus10.draw(g);
		infarctus11.draw(g);
		infarctus12.draw(g);
		infarctus13.draw(g);
		knight1.draw(g);
		knight2.draw(g);
		knight3.draw(g);
		knight4.draw(g);
		knight5.draw(g);
		knight6.draw(g);
		knight7.draw(g);
		knight8.draw(g);
		eliteGuard1.draw(g);
		eliteGuard2.draw(g);
		eliteGuard3.draw(g);
		eliteGuard4.draw(g);
		eliteGuard5.draw(g);
		eliteGuard6.draw(g);
		eliteGuard7.draw(g);

		// Boss fight drawing (below).
		bovis.draw(g);
		bovis.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		// Bovis minions stage 1.
		bovis.infarctus01.draw(g);
		bovis.infarctus02.draw(g);
		bovis.infarctus03.draw(g);
		// Bovis minions stage 2.
		bovis.knight01.draw(g);
		bovis.knight02.draw(g);
		bovis.knight03.draw(g);
		bovis.knight04.draw(g);
		// Bovis minions stage 3.
		bovis.infarctus04.draw(g);
		bovis.infarctus05.draw(g);
		bovis.infarctus06.draw(g);
		bovis.infarctus07.draw(g);
		bovis.infarctus08.draw(g);
		// Boss fight drawing (above).
		
		// Player drawing.
		Player.draw(g);
		
		// UI drawing.
		Hotbar.draw(g);
		Dialogue.draw(g);
		numbers1.draw(g);
		numbers2.draw(g);
		
	/*
		if (Hotbar.getWeapon() != null)
			Hotbar.getWeapon().draw(g);
		
		if (Hotbar.getHelmet() != null)
			Hotbar.getHelmet().draw(g);
		
		if (Hotbar.getChestplate() != null)
			Hotbar.getChestplate().draw(g);
		
		if (Hotbar.getLeggings() != null)
			Hotbar.getLeggings().draw(g);
		
		if (Hotbar.getBoots() != null)
			Hotbar.getBoots().draw(g);
		
		if (Hotbar.getRobes() != null)
			Hotbar.getRobes().draw(g);
	*/	
		// NPC functionality.
		dude.testInteraction(Player.getX(), Player.getY(), dude.getX(), dude.getY(), dude.getCollisionRadius(), "dude");
		grandpa.testInteraction(Player.getX(), Player.getY(), grandpa.getX(), grandpa.getY(), grandpa.getCollisionRadius(), "grandpa");
		shopkeeper.testInteraction(Player.getX(), Player.getY(), shopkeeper.getX(), shopkeeper.getY(), shopkeeper.getCollisionRadius(), "shopkeeper");
		relik.testInteraction(Player.getX(), Player.getY(), relik.getX(), relik.getY(), relik.getCollisionRadius(), "relik");
		bovisTalk.testInteraction(Player.getX(), Player.getY(), bovisTalk.getX(), bovisTalk.getY(), bovisTalk.getCollisionRadius(), "bovisTalk");
		
		// Enemy functionality.
		infarctus1.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus2.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus3.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus4.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus5.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus6.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus7.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus8.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus9.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus10.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus11.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus12.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		infarctus13.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight1.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight2.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight3.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight4.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight5.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight6.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight7.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		knight8.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard1.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard2.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard3.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard4.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard5.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard6.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
		eliteGuard7.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());

		
		// Player functionality.
		Player.run();
		
		// Slows down program.
		try 
		{
			Thread.sleep(20);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
}
