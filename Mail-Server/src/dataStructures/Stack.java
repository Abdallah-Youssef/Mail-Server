package dataStructures;

import interfaces.IStack;

public class Stack implements IStack {
	
	//the index of the top element
	DoubleLinkedList list = new DoubleLinkedList();
	
	@Override
	public Object pop() {
		if (list.size() == 0)
			throw new RuntimeException("Empty Stack");
		
		int index = list.size() - 1;
		
		Object ret = list.get(index);
		list.remove(index);
		return ret;
	}

	@Override
	public Object peek() {
		if (list.size() == 0)
			throw new RuntimeException("Empty Stack");
		
		return list.get(list.size() - 1);
	}

	@Override
	public void push(Object element) {
		list.add(element);
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public int size() {
		return list.size();
	}
	

}
