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
	
	private FacePamphletProfile currentProfile;
	private FacePamphletDatabase database;
	private FacePamphletCanvas canvas;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		addControllers();
		currentProfile = null;
		
		addActionListeners();
    }
	
	private void addControllers() {
		//add North controllers
		add(new JLabel("Name"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		//add West controllers
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
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	//add check for empty textfield
		if (e.getActionCommand().equals("Add") && nameField.getText() != "") {
			//addProfile(nameField.getText());
			println("Add: " + nameField.getText());
		} else if (e.getActionCommand().equals("Delete")) {
			deleteProfile(nameField.getText());
		} else if (e.getActionCommand().equals("Lookup")) {
			lookupProfile(nameField.getText());
		} else if (e.getActionCommand().equals("Change Status") || e.getSource() == statusField) {
			println("Change Status: " + statusField.getText());
		} else if (e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField) {
			println("Change Picture: " + pictureField.getText());
		} else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friendField) {
			println("Add Friend: " + friendField.getText());
		}
	}
    
    private void addProfile(String name) {
    	FacePamphletProfile profile = new FacePamphletProfile(name);
    	if (!database.containsProfile(name)) {
    		database.addProfile(profile);
    		currentProfile = profile;
    	} else {
    		currentProfile = database.getProfile(name);
    	}
    }
    
    private void deleteProfile(String name) {
    	database.deleteProfile(name);
    	currentProfile = null;
    }
    
    private void lookupProfile(String name) {
    	//delete this test in database
    	if (database.containsProfile(name)) {
    		currentProfile = database.getProfile(name);
    	} else {
    		currentProfile = null;
    	}
    	
    }
}
