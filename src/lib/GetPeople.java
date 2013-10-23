package lib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import main.Main;

public class GetPeople 
{
	InputStreamReader in;
	URL url;
	URLConnection connection;
	int month, day;
	char c;
	StringBuffer buffer;
	
	public GetPeople(int month, int day)
	{
		this.month = month;
		this.day = day;
	}
	
	static public void main(String[] args)
	{
		GetPeople g = new GetPeople(9,27);
		Person[] people = g.get();
		for (Person p: people)
			System.out.println(p);
	}
	
	private char read()
	{
		char c = 0;
		try {
			c = (char)in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.print(c);
		return c;
	}
	
	public Person[] get()
	{
		return wikiSearch();
	}
	
	//====================================================================
	
	private Person[] wikiSearch()
	{
		ArrayList<Person> people = new ArrayList<>();
		String s = "";
		// open input stream
		try 
		{
			url = new URL("http://en.wikipedia.org/wiki/" + Main.months[month-1] + "_" + day);
			connection = url.openConnection();
			in = new InputStreamReader(connection.getInputStream(), "UTF-8");
		} catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		
		// skip to beginning of the "Births" section which starts with a <ul> tag
		for (int i = 0; i < 3;)
		{
			if (read() == '<')
			if (read() == 'u')
			if (read() == 'l')
			if (read() == '>')
			i += 1;
		}
		read(); // newline
		
		// "Births" section ends with a </ul> tag
		while (true)
		{
			buffer = new StringBuffer();
			// people are delimited by newlines
			while ((c = read()) != '\n')
				buffer.append(c);
			s = buffer.toString();
			if (s.equals("</ul>")) break; // (here)
			try{people.add(parse(s));}catch(Exception e){e.printStackTrace();}
		}
		
		return people.toArray(new Person[0]);
	}
	
	// ==================================================================
	
	private Person parse(String s)
	{
		int born, died;
		String name;
		
		s = s.replaceAll("<.*?>", "");

		buffer = new StringBuffer();
		int i;
		for (i = 0; Character.isDigit(s.charAt(i)); i++)
			buffer.append(s.charAt(i));
		born = Integer.parseInt(buffer.toString());
		
		// fast forward
		for(; !Character.isAlphabetic(s.charAt(i)); i++);

		buffer = new StringBuffer();
		for(; s.charAt(i) != ','; i++)
		{
			if (s.charAt(i) == '(') 
			{
				buffer.deleteCharAt(buffer.length()-1);
				i--;
				break;
			}
			buffer.append(s.charAt(i));
		}
		name = buffer.toString();

		buffer = new StringBuffer();
		while (i < s.length())
		{
			if (s.charAt(i++) == '(')
			if (s.charAt(i++) == 'd')
			if (s.charAt(i++) == '.')
			if (s.charAt(i++) == ' ')
			{
				for (; s.charAt(i) != ')'; i++)
					buffer.append(s.charAt(i));
				break;
			}
		}
		try{died = Integer.parseInt(buffer.toString());}catch(NumberFormatException e){died = -1;}

		Person p = new Person(name, month, day, born, died);
		return p;
	}
	
	
	
	
	
	
	
	
}
