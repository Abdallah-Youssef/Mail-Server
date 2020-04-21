package eg.edu.alexu.csd.datastructure.mailServer;

public class Comparator_ implements Comparator {

	@Override
	public int compare(Object x, Object y,String typeOfData) {
		// TODO Auto-generated method stub
		if(typeOfData.equals("String")) {
			String first=(String) x;
			String second=(String)y;
			return second.compareTo(first);
		}
		else if(typeOfData.equals("numbers")) {
			return (int)x-(int )y;
		}
		return 0;
		
	}

}
