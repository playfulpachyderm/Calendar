package lib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

public class Person 
{
	private String name;
	private int month;
	private int day;
	private int born;
	private int died;
	private URL url;
	
	static InputStreamReader in;
	static{try {
		in = new InputStreamReader(Person.class.getResourceAsStream("/lib/birthdays.txt"), "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

	static public Person[] people;
	static 
	{
		ArrayList<Person> list = new ArrayList<>();
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
		people = list.toArray(new Person[list.size()]);
	}
	
	static private char read()
	{
		char c = (char)0;
		try{c = (char) in.read();} catch (IOException e) {e.printStackTrace();}
		return c;
	}
	static private Person parse(String s)
	{
		String[] data = s.split(":");
		
		String name = data[0];
		
		String[] dates = data[1].split("[;,]");
		int month = Integer.parseInt(dates[0]);
		int day = Integer.parseInt(dates[1]);
		int born = Integer.parseInt(dates[2]);
		int died;
		try{died = Integer.parseInt(dates[3]);} catch (ArrayIndexOutOfBoundsException e) {died = -1;}

		return new Person(name, month, day, born, died);
	}
	
	public Person(String name, int month, int day, int born, int died)
	{

		this.name = name;
		this.month = month;
		this.day = day;
		this.born = born;
		this.died = died;
	}
		
	public String getName()
	{
		return name;
	}
	
	public int getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	public int getBorn()
	{
		return born;
	}
	public int getDied()
	{
		return died;
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(name).append(":").append(month).append(",").append(day).append(";")
			.append(born).append(",");
		if (died != -1) s.append(died);
		return s.toString();
	}
	
	static public void main(String[] args) throws Exception
	{
		Person p = new Person("Ludwig van Beethoven", 0,0,0,0);
		System.out.println(p.getDescription());
	}
	
	public String getDescription() throws Exception
	{
		url = new URL("http://en.wikipedia.org/wiki/" + name.replace(' ', '_'));
		in = new InputStreamReader(url.openStream(), "UTF-8");
		String str = "";
		while (true)
		{
			if(read() == '<')
			if(read() == 'p')
			if(read() == '>')
				break;
		}
		
		char c;
		int brackets = 0;
		int roundBrackets = 0;
		asdf:while (true)
		{
			c = read();
			switch (c)
			{
				case '<':
				case '[':
				case '(':
					brackets += 1;
					break;
				case '>':
				case ']':
				case ')':
					brackets -= 1;
					break;
					
				case '.':
					if (brackets == 0 && roundBrackets == 0) break asdf;					
			}
			str += c;
		}
				
		str += '.';
		str = str.replace("&#160;", " ").replaceAll("<.*?>", "").replaceAll("\\[.*?\\]", "");
		return str;
	}
	
}
