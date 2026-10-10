// Author : Ofer Koren & Siddarth Iyer
// Date   : 5/14/2026
// Rev    : 01
// Notes (Ofer)  : This class is responsible for the player object, created the windows and panels, and inputs.
// Notes2 (Sid)  : This class allows for a custom icon as well. 

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.File;
import java.net.URL;

public class Main implements KeyListener
{
	// Fields.
	//damn
	// Key fields.
	private static boolean isPressingW = false;
	private static boolean isPressingA = false;
	private static boolean isPressingS = false;
	private static boolean isPressingD = false;
	private static boolean isPressingP = false;
	private static boolean isPressingE = false;
	private static boolean isPressingB = false;
	private static boolean isPressingSpace = false;
	private static String lastButtonPressed = "";
	
	// Screen fields.
	private final static int x = 50;
	private final static int y = 0;
	private final static int width = 1064;
	private final static int height = 771;
	
	// Methods.
	
	//Implements the Icon of the Game
	private static ImageIcon loadAppIcon()
	{
		URL iconUrl = Main.class.getResource("new-bovis-icon-pixilart.png");
		
		if (iconUrl != null)
		{
			return new ImageIcon(iconUrl);
		}
		
		File resourcesIcon = new File("resources/new-bovis-icon-pixilart.png");
		
		if (resourcesIcon.isFile())
		{
			return new ImageIcon(resourcesIcon.getPath());
		}
		
		return new ImageIcon("new-bovis-icon-pixilart.png");
	}
	
	// macOS dock icon: Taskbar API (Java 9+) or Apple API via reflection (Java 8). No com.apple.eawt import (breaks Eclipse).
	private static void setDockIcon(Image icon)
	{
		if (icon == null)
		{
			return;
		}
		
		try
		{
			Class<?> taskbarClass = Class.forName("java.awt.Taskbar");
			if ((Boolean) taskbarClass.getMethod("isTaskbarSupported").invoke(null))
			{
				Object taskbar = taskbarClass.getMethod("getTaskbar").invoke(null);
				taskbarClass.getMethod("setIconImage", Image.class).invoke(taskbar, icon);
				return;
			}
		}
		catch (Exception ignored) { }
		
		try
		{
			Class<?> appClass = Class.forName("com.apple.eawt.Application");
			Object app = appClass.getMethod("getApplication").invoke(null);
			appClass.getMethod("setDockIconImage", Image.class).invoke(app, icon);
		}
		
		catch (Exception ignored) { }
	}
	
	public static void main(String[] args)
	{		 
		ImageIcon logo = loadAppIcon();
		
		// Create a window frame.
		JFrame mainScreen = new JFrame("Relik of Bovinia");
		
		// Establishes initial size in pixels.
		mainScreen.setBounds(x, y, width, height);
	
		// Sets background color.
		mainScreen.getContentPane().setBackground(Color.BLACK);
	
		// The window cannot be resized.
		mainScreen.setResizable(false);
		
		// Closes program when the window is closed.
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
		// Creates components (widgets) to add to the window, which can be panels, buttons, images, etc. In this case , a new 
		// panel is being added to the window.     
		GamePanel mainPanel = new GamePanel();
	
		mainScreen.setIconImage(logo.getImage());
	
		// Sets the background color of the panel to white.
		mainPanel.setBackground(Color.BLACK);
		      
		// Adds the panel to the frame by obtaining the window's container.
		Container windowContainer = mainScreen.getContentPane();
	
		// Adds the panel to the window.
		windowContainer.add(mainPanel);
	
		// Connects the panel created in the this class to the panel created in the Player class.
		Player.gamePanel = mainPanel;
	
		// Instantiates and activates a key listener for player inputs.
		Main key = new Main();
		mainScreen.addKeyListener(key);
	
		// Makes the window visible.
		mainScreen.setVisible(true);
	
		setDockIcon(logo.getImage());
	
	}

	// Unused but a necessary method for the KeyListener class.
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	// Detects when the keys W, A, S, and D are being pressed and sets them to true.
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			isPressingW = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			isPressingA = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			isPressingS = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			isPressingD = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_P)
		{
			isPressingP = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_E)
		{
			isPressingE = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_B)
		{
			isPressingB = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			isPressingSpace = true;
		}
	}

	// Detects when the keys W, A, S, and D are being released and sets them to false.
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			isPressingW = false;
			lastButtonPressed = "W";
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			isPressingA = false;
			lastButtonPressed = "A";
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			isPressingS = false;
			lastButtonPressed = "S";
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			isPressingD = false;
			lastButtonPressed = "D";
		}
		else if (e.getKeyCode() == KeyEvent.VK_P)
		{
			isPressingP = false;
			Hotbar.usePotion();
			// lastButtonPressed = "P"; // This is commented out on purpose for the Player model.
		}
		else if (e.getKeyCode() == KeyEvent.VK_E)
		{
			isPressingE = false;
			Dialogue.transitionDialogueModel();
			// lastButtonPressed = "E"; // This is commented out on purpose for the Player model.
		}
		else if (e.getKeyCode() == KeyEvent.VK_B)
		{
			isPressingB = false;
			Shop.buyPotion();
			// lastButtonPressed = "P"; // This is commented out on purpose for the Player model.
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			isPressingSpace = false;
			// lastButtonPressed = "Space"; // This is commented out on purpose for the Player model.
		}
	}
	
	// This method returns true if W is being pressed, and false if otherwise.
	public static boolean getIsPressingW()
	{
		return isPressingW;
	}
	
	// This method returns true if A is being pressed, and false if otherwise.
	public static boolean getIsPressingA()
	{
		return isPressingA;
	}
	
	// This method returns true if S is being pressed, and false if otherwise.
	public static boolean getIsPressingS()
	{
		return isPressingS;
	}
	
	// This method returns true if D is being pressed, and false if otherwise.
	public static boolean getIsPressingD()
	{
		return isPressingD;
	}
	
	// This method returns true if P is being pressed, and false if otherwise.
	public static boolean getIsPressingP()
	{
		return isPressingP;
	}
	
	// This method returns true if E is being pressed, and false if otherwise.
	public static boolean getIsPressingE()
	{
		return isPressingE;
	}
	
	// This method returns true if E is being pressed, and false if otherwise.
	public static boolean getIsPressingB()
	{
		return isPressingB;
	}
	
	// This method returns true if space is being pressed, and false if otherwise.
	public static boolean getIsPressingSpace()
	{
		return isPressingSpace;
	}
	
	// This method returns the last movement based button pressed (WASD).
	public static String getLastButtonPressed()
	{
		return lastButtonPressed;
	}
}
//dunno why this doesn't work