package lib;

public class WikiError extends Exception
{
	private String mistakeLine;
	protected WikiError(String mistakeLine)
	{
		super();
		this.mistakeLine = mistakeLine;
	}
	
	public String mistake()
	{
		return mistakeLine;
	}
}
