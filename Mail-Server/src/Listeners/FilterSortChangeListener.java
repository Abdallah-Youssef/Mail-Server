package Listeners;

import eg.edu.alexu.csd.datastructure.mailServer.*;

public interface FilterSortChangeListener {
	public void filterChanged(Filter filter);
	public void sortChanged(sortComparator sort);
}
