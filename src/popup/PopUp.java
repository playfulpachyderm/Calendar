package popup;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;

import lib.Person;

public class PopUp extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	public static final Font f = new Font("Georgia", Font.PLAIN, 14);
    public static final FontMetrics m = new JFrame().getFontMetrics(f);
    
    private BirthdayIcon icon;
    private Text t;
    private JButton b;
    
    public PopUp(String s)
	{ 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Java Program Graphic User Interface Window Application Object Instance");
		setSize(600, 300);
		setLayout(null);
		
		icon = new BirthdayIcon(60);
		icon.setLocation(0,0);
		add(icon);
		
		Text t = new Text(s, getWidth() - 150);
		t.setLocation(75, 75);
		add(t);
		
		JButton b = new JButton("OK");
		b.setSize(100, 40);
		b.setLocation(getWidth()/2 - b.getWidth()/2, getHeight() - 100);
		b.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e) {System.exit(0);}
		});
		add(b);
	}
    
    static public void showWindow(String s)
    {
		PopUp p = new PopUp(s);
		p.setLocationRelativeTo(null);
		p.setVisible(true);
    }  
}
