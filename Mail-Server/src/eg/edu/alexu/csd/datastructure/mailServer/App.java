package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Interfaces.ILinkedList;
import interfaces.IApp;
import interfaces.IContact;
import interfaces.IFilter;
import interfaces.IFolder;
import interfaces.IMail;
import interfaces.ISort;

public class App implements IApp {

	
	Folder folder;
	Filter filter;
	sortComparator sort;
	DoubleLinkedList currentlyLoadedEmails;
	int LoggedInUserID;
	
	public App() {
		folder = new Folder("inbox");
		filter = new Filter();
		sort = new sortComparator(0);
	}
	
	
	@Override
	public boolean signin(String email, String password) {
		return false;
	}

	@Override
	public boolean signup(IContact contact) {
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		//Abdallah : this is giving me the error: the method setViewingOptions (IFolder ,IFilter, ISort)
				//				isn't applicable for the arguments (Folder, Filter, SortComparator)
		
		currentlyLoadedEmails = Email.readUserEmails(LoggedInUserID, folder);
		
		//Abdallah : this is giving me the error: the method filter (DoubleLinkedList, FilterComp)
		//				isn't applicable for the arguments (DoubleLinkedList, ISort)
		
		//TODO Should I cast sort to ISort?
		
		/*Filter.filter(currentlyLoadedEmails, sort);
		SortingTemp.quickSort(currentlyLoadedEmails, sort);*/
	}

	@Override
	public IMail[] listEmails(int page) {
		//Abdallah : this is giving me the error: the method setViewingOptions (IFolder ,IFilter, ISort)
		//				isn't applicable for the arguments (Folder, Filter, SortComparator)
		//setViewingOptions(folder, filter, sort); 
		
		Email[] emails = new Email[10];
		
		for(int i = 0;i < 10 && 10*page + i < currentlyLoadedEmails.size();i++)
			emails[i] = (Email)currentlyLoadedEmails.get(10*page+i);
		return emails;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {

	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {

	}

	@Override
	public boolean compose(IMail email) {
		
		return false;
	}

}
