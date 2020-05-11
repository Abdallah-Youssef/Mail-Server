package eg.edu.alexu.csd.datastructure.mailServer;

import interfaces.IFilter;

public class FilterComp implements IFilter
{
	int type;
	String predicate;
	public FilterComp(int type, String predicate) {
		this.type = type;
		this.predicate = predicate;
	}	
	boolean check(Email email)
	{
		if(type == 0 ||
		   type == 1 && email.subject.equals(predicate)|| 
		   type == 2 && email.senderEmail.equals(predicate))
			return true;
		return false;
			
			
	}
}