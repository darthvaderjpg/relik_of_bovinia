import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tools {
    public static Image getNewImageIcon(String path) 
	{
		URL imageUrl = Tools.class.getResource(path);
		
		if (imageUrl != null)
		{
			return new ImageIcon(imageUrl).getImage();
		}
		
		File resourcesImage = new File("resources/" + path);
		
		if (resourcesImage.isFile())
		{
			return new ImageIcon(resourcesImage.getPath()).getImage();
		}
		
		return new ImageIcon(path).getImage();
	}
}
