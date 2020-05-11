package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.alexu.csd.datastructure.mailServer.SinglyLinked;
import listeners.RemoveElementListener;

public class ElementsBox extends JPanel{
	public class Element extends JPanel{
		Element me = this;
		ElementsBox parent;
		JLabel label;
		public Element(String text, ElementsBox parent) {
			this.parent = parent;
			label = new JLabel(text);
			label.setFont(new Font("Serif", Font.PLAIN, 20));
			JButton delete = new JButton("X");
			setLayout(new FlowLayout());
			add(label);
			add(delete);
			
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (removeElementListener != null) {
						//If delete fails
						if (!removeElementListener.elementRemoved(me)) {
							parent.errorLabel.setText("Cannot remove element");
							return;
						}
					}
					
					parent.errorLabel.setText("");
					parent.Delete(me);
				}
			});
		}
	}
	
	SinglyLinked elements;
	JLabel errorLabel;
	JLabel label;
	RemoveElementListener removeElementListener;
	
	/**
	 * 
	 * @param elements the List of elements it handles
	 * @param Label of the box
	 * @param errorLabel for when an error occurs (put a label in your frame and pass its reference here)
	 */
	public ElementsBox(SinglyLinked elements, String Label, JLabel errorLabel) {
		this.elements = elements;
		this.errorLabel = errorLabel;
		
		System.out.println(elements.size());
		
		setMinimumSize(new Dimension(200,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		label = new JLabel(Label);
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		
		
		for (int i = 0;i < elements.size();i++) {
			add(new Element((String) elements.get(i), this));
			revalidate();
		}
	}
	
	public boolean Add(String string) {
		//Check if it's not already existing in the list
		for (int i = 0;i < elements.size();i++) {
			if ( ((String)elements.get(i)) .equals(string)){
				errorLabel.setText("Element already included");
				return false;
			}
		}
		
		elements.add(string);
		
		errorLabel.setText("");
		add(new Element(string, this));
		revalidate();
		return true;
	}
	
	public void Delete(Element element) {
		//Remove component
		element.setVisible(false);
		remove(element);
		revalidate();
		
		for(int i = 0;i < elements.size();i++)
			if ((element.label.getText()).equals((String)elements.get(i))) {
				elements.remove(i);
				break;
			}
		
	}
	
	public void DeleteAll() {
		removeAll();
		add(label);
		revalidate();
		
		elements.clear();
	}
	
	/**
	 * 
	 * @param removeElementListener
	 * If you want to handle what happens when an element is deleted and prevent last element deletion
	 * Implement a RemoveElementListener and pass it
	 */
	public void setRemoveListener(RemoveElementListener removeElementListener) {
		this.removeElementListener = removeElementListener;
	}
	
	
	
	
	
}