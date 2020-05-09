package eg.edu.alexu.csd.datastructure.mailServer.gui;

public interface SignInErrorListener {
	public void sendEmailError(String errorMessage);
	public void sendPasswordError(String errorMessage);
}
