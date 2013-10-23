package popup;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Text extends JPanel 
{
    Font f = PopUp.f;
    FontMetrics m = PopUp.m;
    String[] text;
    
    public Text(String s, int maxWidth)
    {
    	if (s.equals("")) s = "THE WORST THING EVER!!! BLaghhh urrsg aaasdfaaaaaaa!!!! LA DONNA E NE PAS DE LE FRANCAIS MOBILE AVEC FROMAGE!!!";
		setFont(f);
		setSize(maxWidth, getHeight());
		String[] words = s.split(" ");
		
		ArrayList<String> list = new ArrayList<>();
		int longest = 0;
		String string = "";
		int stringWidth = 0;
		for (String word: words)
		{
		    if (word.equals("")) continue;
		    int wordWidth = m.stringWidth(word + " ");
		    if (wordWidth + stringWidth >= maxWidth || word.charAt(0) == '\n')
		    {
				list.add(string);
				string = "";
				if (stringWidth > longest) longest = stringWidth;
				stringWidth = 0;
		    }
		    string += word + " ";
		    stringWidth += wordWidth;
		}
		list.add(string);
		if (stringWidth > longest) longest = stringWidth;
		text = list.toArray(new String[0]);
		setSize(longest, (int)((text.length+0.5)*m.getHeight()));	
    }
    
    @Override
    public void paint(Graphics g)
    {
		//g.setColor(Color.black);
		//g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		int y = 0;
		for (String s: text)
		{
		    y += m.getHeight();
		    g.drawString(s, 0, y);
		}
    }
}
