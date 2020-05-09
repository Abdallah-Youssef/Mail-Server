package eg.edu.alexu.csd.datastructure.mailServer.gui;

public interface RemoveElementListener {
	/**
	 * 
	 * @param element
	 * @return implement it to return false if the element shouldn't be removed
	 */
	public boolean elementRemoved(ElementsBox.Element element);
}
