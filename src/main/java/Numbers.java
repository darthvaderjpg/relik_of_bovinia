// Author : Ofer Koren
// Date   : 5/25/2026
// Rev    : 01
// Notes  : This class handles updating numbers for the ruby and potion count.
// Types  : Ruby, Shop.

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//import javax.swing.ImageIcon;

public class Numbers implements ImageObserver
{
	// Fields.
	
	// Number models (png).
	private final static Image NUMBER_CROSS = Tools.getNewImageIcon("NUMBER_CROSS.png");
	private final static Image NUMBER_ZERO = Tools.getNewImageIcon("NUMBER_0.png");
	private final static Image NUMBER_ONE = Tools.getNewImageIcon("NUMBER_1.png");
	private final static Image NUMBER_TWO = Tools.getNewImageIcon("NUMBER_2.png");
	private final static Image NUMBER_THREE = Tools.getNewImageIcon("NUMBER_3.png");
	private final static Image NUMBER_FOUR = Tools.getNewImageIcon("NUMBER_4.png");
	private final static Image NUMBER_FIVE = Tools.getNewImageIcon("NUMBER_5.png");
	private final static Image NUMBER_SIX = Tools.getNewImageIcon("NUMBER_6.png");
	private final static Image NUMBER_SEVEN = Tools.getNewImageIcon("NUMBER_7.png");
	private final static Image NUMBER_EIGHT = Tools.getNewImageIcon("NUMBER_8.png");
	private final static Image NUMBER_NINE = Tools.getNewImageIcon("NUMBER_9.png");
	
	// Number coordinate fields.
	private int firstNumberX = 0;
	private int secondNumberX = 0;
	private int numberCrossX = 0;
	private int numbersY = 16;
	private String type = "";
	
	// Constructor.
	public Numbers(int firstNumberX, int numbersY, String type)
	{
		this.firstNumberX = firstNumberX;
		secondNumberX = firstNumberX + 41;
		numberCrossX = firstNumberX + 74;
		this.numbersY = numbersY;
		this.type = type;
	}
	
	// Methods.
	
	// Returns the left-most digit of the parameter value as an image.
	public Image getFirstNumberModel(int number)
	{
		if (number >= 10)
		{
			int lastNumber = (int)((double)number / 10);
			return convertNumberToModel(lastNumber);
		}
		else
		{
			return convertNumberToModel(0);
		}
	}
	
	// Returns the right-most digit of the parameter value as an image.
	public Image getSecondNumberModel(int number)
	{
		if (number < 10)
		{
			return convertNumberToModel(number);
		}
		else
		{
			int firstNumber = number % 10;
				
			return convertNumberToModel(firstNumber);
		}
	}
	
	
	public Image getNumberCross()
	{
		return NUMBER_CROSS;
	}
	
	// Returns the parameter as a number model.
	public Image convertNumberToModel(int number)
	{
		switch (number)
		{
			case 0:
				return NUMBER_ZERO;
			
			case 1:
				return NUMBER_ONE;
			
			case 2:
				return NUMBER_TWO;
			
			case 3:
				return NUMBER_THREE;
			
			case 4:
				return NUMBER_FOUR;
			
			case 5:
				return NUMBER_FIVE;
				
			case 6:
				return NUMBER_SIX;
			
			case 7:
				return NUMBER_SEVEN;
			
			case 8:
				return NUMBER_EIGHT;
			
			case 9:
				return NUMBER_NINE;
		
			default:
				return NUMBER_ZERO;
		}
	}
	
	// Returns the x-value of the left-most digit.
	public int getFirstNumberX()
	{
		return firstNumberX;
	}
	
	// Returns the x-value of the right-most digit.
	public int getSecondNumberX()
	{
		return secondNumberX;
	}
	
	// Returns the x-value of the number cross (Looks like this: 52x).
	public int getNumberCrossX()
	{
		return numberCrossX;
	}
	
	// Returns the y-value of the numbers.
	public int getNumbersY()
	{
		return numbersY;
	}
	
	// Returns the value that the number must show, like the amount of rubies the player has or the shop's stock.
	public int getValue()
	{
		switch (type)
		{
			case "Ruby":
				return Hotbar.getCoins();
			
			case "Shop":
				return Shop.getPotionStock();
			
			default:
				return 0;
		}
	}
	
	// Draws both numbers and the number cross.
	public void draw(Graphics g)
	{
		if (Dialogue.getCurrentNPC().equals("shopkeeper") && type.equals("Shop") || type.equals("Ruby"))
		{
			g.drawImage(getFirstNumberModel(getValue()), getFirstNumberX(), getNumbersY(), null);
			g.drawImage(getSecondNumberModel(getValue()), getSecondNumberX(), getNumbersY(), null);
			g.drawImage(getNumberCross(), getNumberCrossX(), getNumbersY(), null);
		}
	}
	
	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}
