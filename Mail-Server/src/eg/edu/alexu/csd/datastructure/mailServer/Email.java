package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

import interfaces.IFolder;
import interfaces.IMail;

public class Email implements IMail, Serializable
{
	int id;
	int priority;
	String subject;
	String body;
	LocalDateTime date;
	int senderID;
	String senderEmail;
	int receiverID;
	String receiverEmail;
	int numOfAttachements;
	
	/**
	 * 
	 * @param subject
	 * @param body
	 * @param senderID
	 * @param senderEmail
	 * @param receiverID
	 * @param receiverEmail
	 * @param numOfAttachements
	 * @param priority
	 */
	public Email(String subject, String body, int senderID, String senderEmail,
			int receiverID, String receiverEmail, int numOfAttachements, int priority) 
	{
		/*
		int id;
		try
		{
			File file = new File("./Users/" +  + "lastID.txt");
			Scanner cin = new Scanner(file);
			id = cin.nextInt()+1;
			cin.close();
			this.id = id;
		}catch(Exception e)
		{
			id = 1;
			try {
				new File("./Users/lastID.txt").createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println(id);
		createUserSubDirectory(id);
		
		try {
			FileWriter writer = new FileWriter("./Users/lastID.txt");
			writer.write(String.valueOf(id));
			writer.close();
		} catch (IOException e) {
		}
		*/
		
		this.subject = subject;
		this.body = body;
		this.date = LocalDateTime.now();
		this.receiverID = receiverID;
		this.receiverEmail = receiverEmail;
		this.senderID = senderID;
		this.senderEmail = senderEmail;
		this.numOfAttachements = numOfAttachements;
		this.priority = priority;
	}
	
	
	public static void DeleteTrash(int userID)
	{		
		String folders[] = Folder.listFolders(userID);
		for(String folder: folders)
		{
			String path = "./Users/" + userID + "/" + folder + "/index.txt";
			DoubleLinkedList emails = (DoubleLinkedList)FolderManagerBIN.ReadObjectFromFile(path);
			for(int i = 0; i < emails.size();i++)
			{
				Email m = (Email)emails.get(i);
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime emailDate = m.date;
				if(now.compareTo(emailDate.plusDays(30)) >= 0)
				{
					String emailPath = "./Users/" + userID + "/" + folder + "/" + m.id;
					File index = new File(emailPath);
					String files[] = index.list();
					for(String s: files)
					{
						File currentFile = new File(index.getPath(), s);
						currentFile.delete();
					}
					index.delete();
					emails.remove(i--);
				}
			}
			FolderManagerBIN.WriteObjectToFile(emails, path);
		}
	}
	
	
	/*
	 * Send Logic: call this function with the sender id and "Sent" folder
	 * Receive Logic: call this function with the Receiver id and "inbox" folder
	 */
	public void saveEmail(int userID, IFolder ifolder)
	{
		User user = FolderManagerBIN.getUser(userID);
		DoubleLinkedList emails = readUserEmails(userID, ifolder);
		
		
		for (int i = 0;i < emails.size();i++)
			System.out.print(((Email)emails.get(i)).subject);
		
		
		Folder folder = (Folder)ifolder;
		
		String path = "./Users/" + userID + "/" + folder.type + "/lastID.txt";
		String savePath = "./Users/" + userID + "/" + folder.type + "/index.txt";
		
		//ID the email object
		try
		{
			File file = new File(path);
			Scanner cin = new Scanner(file);
			id = cin.nextInt()+1;
			cin.close();
		}catch(Exception e)
		{
			id = 1;
			try {
				new File(path).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println(id);
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(String.valueOf(id));
			writer.close();
			emails.add(this);
			FolderManagerBIN.WriteObjectToFile(emails, savePath);
		} catch (IOException e) {
		
		}
	}
	
	/**
	 * 
	 * @param userID
	 * @param Folder object, its type will be used (inbox, sent, trash ..)
	 * @return DoubleLinkedList of the Email objects inside the folder
	 */
	public static DoubleLinkedList readUserEmails(int userID, IFolder f)
	{
		Folder folder = (Folder)f;
		String path = "./Users/" + userID + "/" + folder.type + "/index.txt";
		//System.out.println(path);
		if (FolderManagerBIN.ReadObjectFromFile(path) == null)
			return new DoubleLinkedList();
		
		return (DoubleLinkedList)FolderManagerBIN.ReadObjectFromFile(path);
	}
	
	
	/*public static void main(String[] args) 
	{
		DoubleLinkedList e = readUserEmails(1, new Folder("inbox"));
		Email email = new Email("subject", "body", 1, "a@b.c", 2, "x@y.z", 0, 0);
		email.saveEmail(e, 1, new Folder("inbox"));
	}*/
	
}
