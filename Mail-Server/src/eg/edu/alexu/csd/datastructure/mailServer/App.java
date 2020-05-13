package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import dataStructures.DoubleLinkedList;
import dataStructures.SinglyLinked;
import interfaces.ILinkedList;
import interfaces.IApp;
import interfaces.IContact;
import interfaces.IFilter;
import interfaces.IFolder;
import interfaces.IMail;
import interfaces.ISort;
import listeners.SignInErrorListener;
import listeners.SignUpErrorListener;

public class App implements IApp {

	SignInErrorListener signInErrorListener;
	SignUpErrorListener signUpErrorListener;
	
	public Folder folder;
	public User loggedInUser;
	public FilterComp filter;
	public sortComparator sort;
	public DoubleLinkedList currentlyLoadedEmails;
	
	public SinglyLinked filteredIndices;
	
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
		}else if(FolderManagerBIN.getUser(email) != null)
		{
			signUpErrorListener.sendError("User already exists");
			return false;
		}
		
		else if(user.password.length() < 8)
		{
			signUpErrorListener.sendError("Password length must be at least 8");
			return false;
		}
		return true;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		currentlyLoadedEmails = Email.readUserEmails(loggedInUser.getID(), folder);
		SortingTemp.sort(currentlyLoadedEmails,(ISort) sort);
		filteredIndices = new SinglyLinked();
		Filter.filter(currentlyLoadedEmails, filteredIndices,(FilterComp)filter);
		
		this.folder = (Folder) folder;
		this.filter = (FilterComp) filter;
		this.sort = (sortComparator) sort;
	}

	@Override
	public IMail[] listEmails(int page) {
		Email[] emails = new Email[10];
		for(int i = 0;i < 10 && 10*page + i < filteredIndices.size();i++)
			emails[i] = (Email)currentlyLoadedEmails.get((int)filteredIndices.get(10*page + i));
		return emails;
	}

	
	// mails is a boolean array
	@Override
	public void deleteEmails(ILinkedList mails) {
		if(currentlyLoadedEmails == null)
			return;
		int n = 0;
		DoubleLinkedList trash = Email.readUserEmails(loggedInUser.getID(), new Folder("trash"));
		
		for(int i = 0; i < mails.size();i++)
		{
			if((Boolean)mails.get(i))
			{
				trash.add((Email)currentlyLoadedEmails.get((int)filteredIndices.get(i)-n));
				currentlyLoadedEmails.remove((int)filteredIndices.get(i)-n);
				n++;
			}
		}
		Email.saveBulkEmails(currentlyLoadedEmails, loggedInUser.getID(), folder);

		if(!folder.type.equals("trash"))
		{
			Email.saveBulkEmails(trash, loggedInUser.getID(), new Folder("trash"));
		}
		
		mails.clear();
		mails.add(currentlyLoadedEmails.size());
	}

	
	
	// this requires updating the index file from outside this function in the source folder
	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		DoubleLinkedList emails = Email.readUserEmails(loggedInUser.id, (Folder)des);
		for(int i = 0; i < mails.size();i++)
		{
			Email m = ((Email)mails.get(i));
			int oldID = m.id;
			m.id = m.calculateEmailID(loggedInUser.id, (Folder)des);
			emails.add(m);
			String srcPath = "./Users/" + loggedInUser.id + "/" + folder.type + "/" + oldID + "/";
			String destPath = "./Users/" + loggedInUser.id + "/" + ((Folder)des).type + "/" + m.id + "/";
			new File(destPath).mkdirs();
			String files[] = new File(srcPath).list();
			for(String file: files)
			{
				try {
					Files.move(new File(srcPath + file).toPath(), new File(destPath+file).toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			new File(srcPath).delete();
		}
		Email.saveBulkEmails(emails, loggedInUser.id, (Folder)des);
	}
	
	//Listeners Setters
	public void setSignInListener (SignInErrorListener listener) {
		this.signInErrorListener = listener;
	}
	public void setSignUpListener (SignUpErrorListener listener) {
		this.signUpErrorListener = listener;
	}

}