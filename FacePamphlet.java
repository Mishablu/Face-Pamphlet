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

public class FacePamphlet extends Program 
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
		canvas = new FacePamphletCanvas();
		add(canvas);
		
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
		if (e.getActionCommand().equals("Add") && !nameField.getText().equals("")) {
			addProfile(nameField.getText());
		} else if (e.getActionCommand().equals("Delete") && !nameField.getText().equals("")) {
			deleteProfile(nameField.getText());
		} else if (e.getActionCommand().equals("Lookup") && !nameField.getText().equals("")) {
			lookupProfile(nameField.getText());
		} else if ((e.getActionCommand().equals("Change Status") || e.getSource() == statusField) 
				&& !statusField.getText().equals("")) {
			changeStatus(statusField.getText());
		} else if ((e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField)
				&& !pictureField.getText().equals("")) {
			changePicture(pictureField.getText());
		} else if ((e.getActionCommand().equals("Add Friend") || e.getSource() == friendField)
				&& !friendField.getText().equals("")) {
			addFriend(friendField.getText());
		}
	}
    
    private void addProfile(String name) {
    	FacePamphletProfile profile = new FacePamphletProfile(name);
    	if (database.containsProfile(name)) {
    		currentProfile = database.getProfile(name);
    		canvas.displayProfile(database.getProfile(name));
    		canvas.showMessage("A profile with the name " + name + " already exists");
    	} else {
    		database.addProfile(profile);
    		currentProfile = profile;
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("New profile created");
    	}
    }
    
    private void deleteProfile(String name) {
    	currentProfile = null;
    	canvas.displayProfile(currentProfile);
    	if (database.containsProfile(name)) {
    		database.deleteProfile(name);
    		canvas.showMessage("Profile of " + name + " deleted");
    	} else {
    		canvas.showMessage("A profile with the name " + name + " does not exist");
    	}
    }
    
    private void lookupProfile(String name) {
    	if (database.containsProfile(name)) {
    		currentProfile = database.getProfile(name);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Displaying " + name);
    	} else {
    		println("this profile does not exist");
    		currentProfile = null;
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with the name " + name + " does not exist");
    	}
    }
    
    private void changeStatus(String status) {
    	if (currentProfile != null) {
    		currentProfile.setStatus(status);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Status updated to " + status);
    	} else {
    		canvas.showMessage("Please select a profile to change status");
    	}
    }
    
    private void changePicture(String filename) {
    	if (currentProfile != null) {
    		GImage image = null;
    		try {
    			image = new GImage(filename);    		
    			currentProfile.setImage(image);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Picture updated");
    		} catch (ErrorException ex) {
    			canvas.showMessage("Unable to open the image file " + filename);
    		}
    	} else {
    		canvas.showMessage("Please select a profile to change picture");
    	}
    }
    
    private void addFriend(String friend) {
    	if (currentProfile != null) {
    		if (database.containsProfile(friend)) {
    			if (currentProfile.addFriend(friend)) {
    				database.getProfile(friend).addFriend(currentProfile.getName());
    				canvas.displayProfile(currentProfile);
    				canvas.showMessage(friend + " added as a friend");
    			} else {
    				canvas.showMessage(currentProfile.getName() + " already has " + friend + " as a friend");
    			}
    		} else {
    			canvas.showMessage(friend + " does not exist");
    		}
    	} else {
    		canvas.showMessage("Please select a profile to add friend");
    	}
    }
}
