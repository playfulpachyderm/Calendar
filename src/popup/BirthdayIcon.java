package popup;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BirthdayIcon extends JPanel 
{
	static private Image i;
	private Image img;
	static
	{
		try {
		i = ImageIO.read(BirthdayIcon.class.getResourceAsStream("/popup/asdf.jpg"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public BirthdayIcon(int height)
	{
		img = i.getScaledInstance(-1, height, 0);
		setSize(img.getWidth(null), img.getHeight(null));
	}
    
	@Override
    public void paint(Graphics g)
    {
    	g.drawImage(img, 0, 0, null);
	}
}
