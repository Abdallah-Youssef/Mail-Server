package listeners;

import eg.edu.alexu.csd.datastructure.mailServer.*;

public interface FilterSortChangeListener {
	public void filterChanged(FilterComp filter);
	public void sortChanged(sortComparator sort);
}
