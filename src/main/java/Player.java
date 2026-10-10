// Author : Ofer Koren 
// Editor : Jake Jiang -- Subscribe to Scrapnel on YT
// Editor2 :Siddarth Iyer
// Date   : 5/14/2026
// Rev    : 01
// Notes  : This class is responsible for player movement, collision, models, and values. Also responsible for the player's inventory, and the using of items. 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//import javax.swing.ImageIcon;

public class Player implements ImageObserver
{
	// Non-animated player models (png). Art is resized to 400% in aseprite.
	private final static Image PLAYER_STANDING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_SOUTH_MODEL.png");
	private final static Image PLAYER_STANDING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_NORTH_MODEL.png");
	private final static Image PLAYER_STANDING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_WEST_MODEL.png");
	private final static Image PLAYER_STANDING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_STANDING_EAST_MODEL.png");
	
	// Animated player walking models (gif).
	private final static Image PLAYER_WALKING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_SOUTH_MODEL.gif");
	private final static Image PLAYER_WALKING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_NORTH_MODEL.gif");
	private final static Image PLAYER_WALKING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_WEST_MODEL.gif");
	private final static Image PLAYER_WALKING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_WALKING_EAST_MODEL.gif");
	
	// Animated player attacking models (gif).
	private final static Image PLAYER_ATTACKING_SOUTH_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_SOUTH_MODEL.gif");
	private final static Image PLAYER_ATTACKING_NORTH_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_NORTH_MODEL.gif");
	private final static Image PLAYER_ATTACKING_WEST_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_WEST_MODEL.gif");
	private final static Image PLAYER_ATTACKING_EAST_MODEL = Tools.getNewImageIcon("PLAYER_ATTACKING_EAST_MODEL.gif");
	
	// Other player fields.
	private static int currency = 0;
	private static int health = 100;
	private static int maxHealth = 100;
	private static int damage = 3;
	private static int x = 708;
	private static int y = 289;
	private static int speed = 7;
	private static int attackingRange = 150;
	private static int room = 0;
	private static boolean canMove = true;
	private static boolean isAttacking = false;
	private final static boolean DEBUG = false;
	private final static int OFFSET_X = -64;
	private final static int OFFSET_Y = -54;
	public static GamePanel gamePanel;
	
	// Methods.
	
	// Handles collision, repainting, movement, and the debug menu.
	public static void run()
	{
			// Makes player move in a direction based on the current button pressed.
			if (canMove)
			{
				if (Main.getIsPressingW())
				{
					moveNorth();
				}
				else if (Main.getIsPressingA())
				{
					moveWest();
				}
				else if (Main.getIsPressingS())
				{
					moveSouth();
				}
				else if (Main.getIsPressingD())
				{
					moveEast();
				}
			}
			
			// Loops player if they're on the edges of the screen.
			loopAround();
			
			// Repaints the player after moving.
			gamePanel.repaint();

			// Player debug menu.
	  		if (DEBUG)
	  		{
	  			System.out.println("\r"); // Carriage return.
	  			System.out.println("playerX: " + x + "   playerY: " + y);
	  			System.out.println("W: " + Main.getIsPressingW());
	  			System.out.println("A: " + Main.getIsPressingA());
	  			System.out.println("S: " + Main.getIsPressingS());
	  			System.out.println("D: " + Main.getIsPressingD());
	  			System.out.println("P: " + Main.getIsPressingP());
	  			System.out.println("E: " + Main.getIsPressingE());
	  			System.out.println("last: " + Main.getLastButtonPressed());
	  			System.out.println("Room: " + room);
	  		}
	}
	
	// Detects when the player is past the bounds of the screen and loops them around to the other side.
	public static void loopAround()
	{
		// X Loop.
		if (x > 790)
		{
			x = -30;
			changeRoom("East");
		}
		else if (x < -30)
		{
			x = 790;
			changeRoom("West");
		}
		
		// Y Loop.
		if (y > 711)
		{
			y = 111;
			changeRoom("South");
		}
		else if (y < 111)
		{
			y = 711;
			changeRoom("North");
		}
	}
	
	// Moves the player to the appropriate room when called.
	public static void changeRoom(String Direction)
	{
		// Changes the room if the player moves past the northern corridor.
		if (Direction.equals("North"))
		{
			switch (room)
			{
				case 2:
					room = 3;
					break;
					
				case 5:
					room = 4;
					break;
					
				case 6:
					room = 7;
					break;
			}
		}
		
		// Changes the room if the player moves past the southern corridor.
		else if (Direction.equals("South"))
		{
			switch (room)
			{
				case 3:
					room = 2;
					break;
					
				case 4:
					room = 5;
					break;
					
				case 7:
					room = 6;
					break;
			}
		}
		
		// Changes the room if the player moves past the western corridor.
		else if (Direction.equals("West"))
		{
			switch (room)
			{
				case 2:
					room = 1;
					break;
					
				case 4:
					room = 2;
					break;
					
				case 6:
					room = 4;
					break;
			}
		}
		
		// Changes the room if the player moves past the eastern corridor.
		else if (Direction.equals("East"))
		{
			switch (room)
			{
				case 1:
					room = 2;
					break;
					
				case 2:
					room = 4;
					break;
					
				case 4:
					room = 6;
					break;
			}
		}
	}
	
	// While the player is walking into a wall, this methods moves the player back.
	public static void doWallCollision()
	{
		// East wall.
		while (x > 730 && (y < 396 || y > 451))
		{
			moveWest();
		}
		
		// West wall.
		while (x < 20 && (y < 396 || y > 451))
		{
			moveEast();
		}
		
		// North wall.
		while (y < 171 && (x < 352 || x > 412))
		{
			moveSouth();
		}
		
		// South wall.
		while (y > 641 && (x < 352 || x > 412))
		{
			moveNorth();
		}
	}
	
	// Handles when the game system moves the player to  specific room along with coordinates.
	public static void moveToRoom(int room)
	{
		switch (room)
		{
			case 0:
				x = 728;
				y = 346;
				Player.room = 0;
				
			case 1:
				x = 134;
				y = 565;
				Player.room = 1;
				break;
				
			case 8:
				x = 42;
				y = 427;
				Player.room = 8;
		}
	}
	
	// Teleports the player back to the 0th room and reset's their health on death.
	public static void die() 
	{
		Hotbar.addCoins(-2);
		moveToRoom(0);
		setHealth(maxHealth);
	}
	
	// Returns specific type of player animation/model depending on the current button that's pressed.
	public static Image getModel()
	{
		if (canMove)
		{
			// Walking.
			if (Main.getIsPressingW())
			{
				isAttacking = false;
				return PLAYER_WALKING_NORTH_MODEL;
			}
			else if (Main.getIsPressingA())
			{
				isAttacking = false;
				return PLAYER_WALKING_WEST_MODEL;
			}
			else if (Main.getIsPressingS())
			{
				isAttacking = false;
				return PLAYER_WALKING_SOUTH_MODEL;
			}
			else if (Main.getIsPressingD())
			{
				isAttacking = false;
				return PLAYER_WALKING_EAST_MODEL;
			}
		
			// Standing.
			else if (Main.getLastButtonPressed() == "W" && !Main.getIsPressingSpace())
			{
				isAttacking = false;
				return PLAYER_STANDING_NORTH_MODEL;
			}
			else if (Main.getLastButtonPressed() == "A" && !Main.getIsPressingSpace())
			{
				isAttacking = false;
				return PLAYER_STANDING_WEST_MODEL;
			}
			else if (Main.getLastButtonPressed() == "S" && !Main.getIsPressingSpace())
			{
				isAttacking = false;
				return PLAYER_STANDING_SOUTH_MODEL;
			}	
			else if (Main.getLastButtonPressed() == "D" && !Main.getIsPressingSpace())
			{
				isAttacking = false;
				return PLAYER_STANDING_EAST_MODEL;
			}
		
			// Attacking.
			else if (Main.getLastButtonPressed() == "W" && Main.getIsPressingSpace())
			{
				isAttacking = true;
				return PLAYER_ATTACKING_NORTH_MODEL;
			}
			else if (Main.getLastButtonPressed() == "A" && Main.getIsPressingSpace())
			{
				isAttacking = true;
				return PLAYER_ATTACKING_WEST_MODEL;
			}
			else if (Main.getLastButtonPressed() == "S" && Main.getIsPressingSpace())
			{
				isAttacking = true;
				return PLAYER_ATTACKING_SOUTH_MODEL;
			}
			else if (Main.getLastButtonPressed() == "D" && Main.getIsPressingSpace())
			{
				isAttacking = true;
				return PLAYER_ATTACKING_EAST_MODEL;
			}
		}

		// Default.
		return PLAYER_STANDING_SOUTH_MODEL;
	}
	
	// Tests if the player is dead and returns the amount of health the player has.
	public static int getHealth()
	{
		if(health <= 0) 
		{
			Player.die();
		}
		
		return health;
	}
	
	// Returns the player's max health.
	public static int getMaxHealth()
	{	
		return maxHealth;
	}
	
	// Returns the player's current X position.
	public static int getX()
	{
		return x;
	}
	
	// Returns the player's current Y position.
	public static int getY()
	{
		return y;
	}
	
	// Returns the player's speed value.
	public static int getSpeed()
	{
		return speed;
	}
	
	// Returns the attacking range of the player.
	public static int getAttackingRange()
	{
		return attackingRange;
	}
	
	// Returns the room number that the player is in.
	public static int getRoom()
	{
		return room;
	}
	
	// Returns the amount of damage the player does.
	public static int getDamage() 
	{
		return damage;
	}
	
	// Returns true if the player can move and false if otherwise.
	public static boolean getMoveState()
	{
		return canMove;
	}
	
	// Returns true if the player is currently attacking and false if otherwise.
	public static boolean getAttackingState()
	{
		return isAttacking;
	}
	
	// Returns the amount of currency the player has.
	public static int getCurrency() 
	{
		return currency;
	}
	
	// Sets the player health to the parameter value.
	public static void setHealth(double playerHealth)
	{
		Player.health = (int) playerHealth;
	}
	
	// Sets the max player health to the parameter value.
	public static void setMaxHealth(int maxPlayerHealth)
	{
		Player.maxHealth = maxPlayerHealth;
	}
	
	// Decreases the player's health by the parameter value.
	public static void takeDamage(int damage) 
	{
		Player.health -= damage;
	}
	
	// Moves the player up by decreasing their Y value by speed, and tests a wall collision.
	public static void moveNorth()
	{
		y -= speed;
		doWallCollision();
	}

	// Moves the player down by increasing their Y value by speed, and tests a wall collision.
	public static void moveSouth()
	{
		y += speed;
		doWallCollision();
	}
	
	// Moves the player left by decreasing their X value by speed, and tests a wall collision.
	public static void moveWest()
	{
		x -= speed;
		doWallCollision();
	}
	
	// Moves the player right by increasing their X value by speed, and tests a wall collision.
	public static void moveEast()
	{
		x += speed;
		doWallCollision();
	}
	
	// Sets the player's current room to the parameter value.
	public static void setRoom(int room)
	{
		Player.room = room;
	}
	
	// Sets the player's current move state to the parameter value.
	public static void setMoveState(boolean canMove)
	{
		Player.canMove = canMove;
	}
	
	public static void draw(Graphics g)
	{
		g.drawImage(getModel(), getX() + OFFSET_X, getY() + OFFSET_Y, null);
	}

	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}