package listeners;

public interface SignInErrorListener {
	public void sendEmailError(String errorMessage);
	public void sendPasswordError(String errorMessage);
}
