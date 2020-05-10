package eg.edu.alexu.csd.datastructure.mailServer;

import interfaces.ISort;

public class sortComparator implements ISort
{
	int type;
	
	public sortComparator(int type) {
		this.type = type;
	}
	
	public int mycompare(Object a, Object b)
	{
		switch(type)
		{
		case 0:
			return ((User)a).firstName.compareTo(((User)b).firstName);
		case 1:
			String emailA = (String) ((User)a).emails.get(0);
			String emailB = (String) ((User)b).emails.get(0);
			return emailA.compareTo(emailB);
		case 2:
			return ((Email)a).subject.compareTo(((Email)b).subject);
		case 3:
			return ((Email)a).senderEmail.compareTo(((Email)b).senderEmail);
		case 4:
			return ((Email)a).receiverEmail.compareTo(((Email)b).receiverEmail);
		case 5:
			return ((Email)a).date.compareTo(((Email)b).date);
		default:
			{
			if(((Email)a).priority < ((Email)b).priority)
				return -1;
			else if(((Email)a).priority == ((Email)a).priority)
				return 0;
			else
				return 1;
			}
		}	
	}
}
