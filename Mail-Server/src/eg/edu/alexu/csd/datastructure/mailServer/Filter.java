package eg.edu.alexu.csd.datastructure.mailServer;

import dataStructures.DoubleLinkedList;
import dataStructures.SinglyLinked;

public class Filter
{
	public static void filter(DoubleLinkedList emails, SinglyLinked filteredIndices, FilterComp comp)
	{
		for(int i = 0;i < emails.size();i++)
			if(comp.check((Email)emails.get(i)))
			{	
				filteredIndices.add(i);
				System.out.println(((Email)emails.get(i)).subject + " == " + comp.predicate);
			}
	}
}

