package eg.edu.alexu.csd.datastructure.mailServer;

public interface IComparator {
	/**
	 * 
	 * @param x
	 * @param y
	 * @return negative number if x comes before y.
	 *  0 if they are equal.
	 *  Positive if x comes after y.
	 */
	public int compare(Object x, Object y);
}
