/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	//define the instance variables
	private GLabel messageLabel;
	private GLabel nameLabel;
	private GRect emptyBox;
	private GLabel noImage;	
	private GLabel statusLabel;
	private GLabel friendHeader;
	
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		//initialize the instance variables
		messageLabel = label("", MESSAGE_FONT);
		nameLabel = label("", PROFILE_NAME_FONT);
		nameLabel.setColor(Color.BLUE);
		emptyBox = new GRect(LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN), IMAGE_WIDTH, IMAGE_HEIGHT);
		noImage = label("No Image", PROFILE_IMAGE_FONT);
		statusLabel = label("", PROFILE_STATUS_FONT);
		friendHeader = label("Friends:", PROFILE_FRIEND_LABEL_FONT);
	}
	// this method creates a label given a string and a font
	private GLabel label(String str, String font) {
		GLabel label = new GLabel(str);
		label.setFont(font);
		return label;
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		messageLabel.setLabel(msg);
		add(messageLabel, getWidth()/2-(messageLabel.getWidth()/2), getHeight()-BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if (profile != null) {
			addName(profile.getName());
			addImage(profile.getImage());
			addStatus(profile.getName(), profile.getStatus());
			addFriends(profile.getFriends());
		}

	}
	//this method adds the name label on the canvas
	private void addName(String name) {
		nameLabel.setLabel(name);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + nameLabel.getHeight());
	}
	//if an image exists for the profiel, the image is added to the canvas. If not, in place of the image is a rectangle and a label saying "no image" 
	private void addImage(GImage image) {
		if (image != null) {
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image, LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN));
		} else {
			add(emptyBox);
			add(noImage, LEFT_MARGIN + (IMAGE_WIDTH/2) - noImage.getWidth()/2, emptyBox.getY() + (IMAGE_HEIGHT/2) + noImage.getHeight()/2);
		}
	}
	//this method adds a status if a status is entered. If no status is entered, a message is displayed to that effect
	private void addStatus(String name, String status) {
		String string = name + " is " + status;
		if (status.equals("")) {
			string = "No current status";
		}
		statusLabel.setLabel(string);
		add(statusLabel, LEFT_MARGIN, emptyBox.getY() + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getHeight());
	}
	//this method adds the friends of the profile selected to the canvas by using a while loop that goes through the list of friends of the profile and adds them one at a time on the canvas
	private void addFriends(Iterator <String> friends){
		add(friendHeader, getWidth()/2 , emptyBox.getY());
		double y = (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN);
		while (friends.hasNext()) {
			//increase the y value to have all the friend names aligned in a column uniformly dispersed
			y += friendHeader.getHeight();
			GLabel friend = new GLabel (friends.next(), getWidth()/2, y);
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend);
		}
		
	}
}
