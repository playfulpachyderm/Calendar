package main;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;

import lib.GetPeople;
import lib.Person;

public class Main 
{
	static public String[] months = 
	{
		"January",      
		"February",
		"March",        
		"April",        
		"May",          
		"June",         
		"July",         
		"August",       
		"September",    
		"October",      
		"November",     
		"December"
	};
	
	public static void main(String[] args) throws Exception
    {
    	while (!isConnected()) Thread.sleep(1000);

    	int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    	int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		String s = "It's " + months[month-1] + " " + day + ". \n";
		String greeting = "";
		ArrayList<Person> people = new ArrayList<>();
		for (Person person: Person.people)
		{
		    if(person.getMonth() != month) continue;
		    if(person.getDay() != day) continue;
		    people.add(person);
		}
		if (people.size() != 0)
		{
			s += "Happy birthday ";
			for (int i = 0; i < people.size(); i++)
			{
				Person person = people.get(i);
				if (i > 0) s += " and ";
				greeting += person.getName();
			}
			s += greeting;
			s += "!";
		}
		else
		{
			s += "Random person of the day: \n";
			Person[] randoms = new GetPeople(month, day).get();
			Person choice = randoms[(int) (randoms.length*Math.random())];
			s += "Happy birthday " + choice.getName() + ". \n" + choice.getDescription();
		}
		popup.PopUp.showWindow(s);
    }
    
    private static boolean isConnected() throws Exception
    {
		URLConnection url = new URL("http://www.facebook.com").openConnection();
		if (url.getContentLength() == -1) return false;
		else return true;
    }
}
