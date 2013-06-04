/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	private JTextField nameField;
	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		//add objects to the NORTH
		//add the name label to the north indicating that a name should be inserted into the text box
		add(new JLabel("Name"), NORTH);
		//add textfield to the north where the user will insert a name
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		//add the add, delete and lookup buttons
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		//add objects to the WEST
		statusField = new JTextField(TEXT_FIELD_SIZE);
		add(statusField, WEST);
		statusField.addActionListener(this);
		add(new JButton("Change Status"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField, WEST);
		pictureField.addActionListener(this);
		add(new JButton("Change Picture"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		add(friendField, WEST);
		friendField.addActionListener(this);
		add(new JButton("Add Friend"), WEST);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add") && nameField.getText() != "") {
			println("Add: " + nameField.getText());
		} else if (e.getActionCommand().equals("Delete")) {
			
		} else if (e.getActionCommand().equals("Lookup")) {
			
		} else if (e.getActionCommand().equals("Change Status") || e.getSource() == statusField) {
			
		} else if (e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField) {
			
		} else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friendField) {
			
		}
	}

}
