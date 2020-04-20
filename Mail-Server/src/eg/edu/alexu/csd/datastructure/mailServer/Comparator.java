package eg.edu.alexu.csd.datastructure.mailServer;

public interface Comparator {
	/**
	 * 
	 * @param x
	 * @param y
	 * @param typeOfData
	 * @return negative number if x comes after(Strings)\or smaller than  y.
	 *  0 if they are equal.
	 *  Positive if x comes before(Strings)\ or bigger than y.
	 *  typesOfData:Strings,numbers.
	 */
	public int compare(Object x, Object y,String typeOfData);
}
