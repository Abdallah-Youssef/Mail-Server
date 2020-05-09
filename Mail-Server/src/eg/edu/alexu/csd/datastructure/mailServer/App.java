package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Interfaces.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.gui.EMailHomePageGUI;
import eg.edu.alexu.csd.datastructure.mailServer.gui.SignInErrorListener;
import interfaces.IApp;
import interfaces.IContact;
import interfaces.IFilter;
import interfaces.IFolder;
import interfaces.IMail;
import interfaces.ISort;

public class App implements IApp {

	SignInErrorListener signInErrorListener;
	Folder folder;
	User loggedInUser;
	FilterComp filter;
	sortComparator sort;
	DoubleLinkedList currentlyLoadedEmails;
	
	public App() {
		folder = new Folder("inbox");
		filter = null;
		sort = new sortComparator(5);	//default sorted by date
	}
	
	
	@Override
	public boolean signin(String email, String password) {
		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			//emailErrorMessage.setText("Please enter a valid email address");
			signInErrorListener.sendEmailError("Please enter a valid email address");
			return false;
		}
		else if(FolderManagerBIN.getUser(email)==null) {
			signInErrorListener.sendEmailError("User doesn't exist");
			return false;
		}
		
		User user = FolderManagerBIN.getUser(email);
		if (password.contentEquals("")) {
			signInErrorListener.sendPasswordError("Please enter a password");
			return false;
		}
		else if(!(user.password).equals(password)) {
			signInErrorListener.sendPasswordError("Wrong password");
			return false;
		}
		else {
			signInErrorListener.sendPasswordError("");
			loggedInUser = user;
			return true;
		}
	}

	@Override
	public boolean signup(IContact contact) {
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
<<<<<<< HEAD
		currentlyLoadedEmails = Email.readUserEmails(LoggedInUserID, folder);	//TODO
		if(filter != null)
			Filter.filter(currentlyLoadedEmails, (FilterComp)filter);
		SortingTemp.quickSort(currentlyLoadedEmails, sort);
=======
		//Abdallah : this is giving me the error: the method setViewingOptions (IFolder ,IFilter, ISort)
				//				isn't applicable for the arguments (Folder, Filter, SortComparator)
		
		currentlyLoadedEmails = Email.readUserEmails(LoggedInUserID, folder);
		
		//Abdallah : this is giving me the error: the method filter (DoubleLinkedList, FilterComp)
		//				isn't applicable for the arguments (DoubleLinkedList, ISort)
		
		//TODO Should I cast sort to ISort?
		
		/*Filter.filter(currentlyLoadedEmails, sort);
		SortingTemp.quickSort(currentlyLoadedEmails, sort);*/
>>>>>>> refs/remotes/origin/master
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
	
	
	public void setSignInListener (SignInErrorListener listener) {
		this.signInErrorListener = listener;
	}

}
