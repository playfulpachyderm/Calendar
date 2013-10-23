package lib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Event 
{
	String name;
	int day;
	int month;
	int year;
	
	static InputStreamReader in;
	static{try {
		in = new InputStreamReader(Person.class.getResourceAsStream("/lib/events.txt"), "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
	static Event[] events;
	static 
	{
		ArrayList<Event> list = new ArrayList<>();
		StringBuilder s = new StringBuilder();
		char c;
		while ((c = read()) != (char)-1) // has to end with a newline
		{
			while (c != '\n')
			{
				s.append(c);
				c = read();
			}
			list.add(parse(s.toString()));
			s.delete(0, s.length());
		}
		events = list.toArray(new Event[list.size()]);
	}
	
	static private Event parse(String s)
	{
		String[] asdf = s.split(":");
		String name = asdf[0];
		asdf = asdf[1].split(";");
		int year = Integer.parseInt(asdf[1]);
		asdf = asdf[0].split(",");
		int month = Integer.parseInt(asdf[0]);
		int day = Integer.parseInt(asdf[1]);
		return new Event(name, month, day, year);
	}
	
	
	
	static private char read()
	{
		char c = (char)0;
		try{c = (char) in.read();} catch (IOException e) {e.printStackTrace();}
		return c;
	}
	
	public Event(String name, int month, int day, int year)
	{
		this.name = name;
		this.month = month;
		this.day = day;
		this.year = year;
	}
}
