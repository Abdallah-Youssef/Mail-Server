package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Scanner;

import dataStructures.DoubleLinkedList;
import dataStructures.SinglyLinked;
import interfaces.IFolder;
import interfaces.IMail;

public class Email implements IMail, Serializable
{
	int id;
	public int priority;
	String subject;
	String body;
	LocalDateTime date;
	int senderID;
	String senderEmail;
	int receiverID;
	String receiverEmail;
	SinglyLinked attachments;
	
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
			int receiverID, String receiverEmail, SinglyLinked attachments, int priority) 
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
		this.attachments = attachments;
		this.priority = priority;
		System.out.println("Registeres priority " + priority + " for " + subject);
	}
	
	
	public String getSubject(){
		return subject;
	}
	
	public String getSender(){
		return senderEmail;
	}
	public String getReceiver(){
		return receiverEmail;
	}
	public String getBody(){
		return body;
	}
	public LocalDateTime getDate(){
		return date;
	}
	public SinglyLinked getAttachments() {
		return attachments;
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
	
	
	int calculateEmailID(int userID, Folder folder)
	{
		int id;
		//User user = FolderManagerBIN.getUser(userID);
		DoubleLinkedList emails = readUserEmails(userID, folder);
		
		
		for (int i = 0;i < emails.size();i++)
			System.out.print(((Email)emails.get(i)).subject);
		
		
		
		String path = "./Users/" + userID + "/" + folder.type + "/lastID.txt";

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
		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(String.valueOf(id));
			writer.close();
		} catch (IOException e) {
			
		}
		
		System.out.println(id);
		return id;
	}
	
	public void saveEmail(int userID, IFolder ifolder)
	{
		User user = FolderManagerBIN.getUser(userID);
		DoubleLinkedList emails = readUserEmails(userID, ifolder);
		Folder folder = (Folder)ifolder;
		
		//ID the email object
		id = calculateEmailID(userID, folder);
		String path = "./Users/" + userID + "/" + folder.type + "/lastID.txt";
		String savePath = "./Users/" + userID + "/" + folder.type + "/index.txt";

		try {
			FileWriter writer = new FileWriter(path);
			writer.write(String.valueOf(id));
			writer.close();
			emails.add(this);
			FolderManagerBIN.WriteObjectToFile(emails, savePath);
		} catch (IOException e) {
		
		}

		
		String attachmentSavePath = "./Users/" + userID + "/" + folder.type + "/" + id + "/";
		new File(attachmentSavePath).mkdirs();
		for(int i = 0; i < attachments.size();i++)
		{
			File attachmentPath = new File((String)attachments.get(i));
			try
			{
			Files.copy(attachmentPath.toPath(), 
					new File(attachmentSavePath + attachmentPath.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			
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
		DoubleLinkedList emails = (DoubleLinkedList) FolderManagerBIN.ReadObjectFromFile(path);
		if (emails == null)
			return new DoubleLinkedList();
		
		return emails;
	}
	
	public static void saveBulkEmails(DoubleLinkedList emails, int userID, Folder folder)
	{
		String path = "./Users/" + userID + "/" + folder.type + "/index.txt";
		FolderManagerBIN.WriteObjectToFile(emails, path);
	}
	
	
	
}
