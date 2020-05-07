package eg.edu.alexu.csd.datastructure.mailServer;

import interfaces.IFilter;

public class Filter
{
	public static void filter(DoubleLinkedList emails, FilterComp comp)
	{
		for(int i = 0;i < emails.size();i++)
			if(!comp.check((Email)emails.get(i)))
				emails.remove(i);
	}
}

class FilterComp implements IFilter
{
	int type;
	String predicate;
	public FilterComp(int type, String predicate) {
		this.type = type;
	}
	
	boolean check(Email email)
	{
		if(type == 1 && email.subject.equals(predicate)
		|| type == 2 && email.senderEmail.equals(predicate))
			return true;
		return false;
			
			
	}
}