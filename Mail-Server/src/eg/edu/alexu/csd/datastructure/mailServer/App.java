package eg.edu.alexu.csd.datastructure.mailServer;

import Listeners.SignInErrorListener;
import Listeners.SignUpErrorListener;
import eg.edu.alexu.csd.datastructure.linkedList.cs.Interfaces.ILinkedList;
import interfaces.IApp;
import interfaces.IContact;
import interfaces.IFilter;
import interfaces.IFolder;
import interfaces.IMail;
import interfaces.ISort;

public class App implements IApp {

	SignInErrorListener signInErrorListener;
	SignUpErrorListener signUpErrorListener;
	
	Folder folder;
	public User loggedInUser;
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
			signInErrorListener.sendEmailError("Please enter a valid email address");
			return false;
		}
		User user = FolderManagerBIN.getUser(email);
		if(user == null) {
			signInErrorListener.sendEmailError("User doesn't exist");
			return false;
		}
		
		
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
		User user = (User)contact;
		String email = (String)user.emails.get(0);
		
		
		if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
		{
			
			signUpErrorListener.sendError("Invalid Email form");
			return false;
		}else if(FolderManagerBIN.getUser(email)!=null)
		{
			signUpErrorListener.sendError("User already exists");
			return false;
		}
		
		else if(user.password.length() < 8)
		{
			signUpErrorListener.sendError("Password length must be at least 8");
			return false;
		}
		else
		{
			user.saveToFileSystem();
		}
		return true;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		currentlyLoadedEmails = Email.readUserEmails(loggedInUser.getID(), folder);

		if(filter != null)
			Filter.filter(currentlyLoadedEmails, (FilterComp)filter);
		SortingTemp.quickSort(currentlyLoadedEmails,(ISort) sort);
	}

	@Override
	public IMail[] listEmails(int page) {
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
	
	
	//Listeners Setters
	public void setSignInListener (SignInErrorListener listener) {
		this.signInErrorListener = listener;
	}
	public void setSignUpListener (SignUpErrorListener listener) {
		this.signUpErrorListener = listener;
	}

}
