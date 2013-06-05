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
		messageLabel = new GLabel("");
		
		nameLabel = new GLabel("", LEFT_MARGIN, TOP_MARGIN);
		nameLabel.setColor(Color.BLUE);
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.move(0, nameLabel.getHeight());
		
		emptyBox = new GRect(LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN), IMAGE_WIDTH, IMAGE_HEIGHT);
		
		noImage = new GLabel("No Image", LEFT_MARGIN+(IMAGE_WIDTH/2), (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN)+(IMAGE_HEIGHT/2));
		noImage.setFont(PROFILE_IMAGE_FONT);
		noImage.move(-noImage.getWidth()/2, noImage.getHeight()/2);
		
		statusLabel = new GLabel("", LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN));
		statusLabel.setFont(PROFILE_STATUS_FONT);
		statusLabel.move(0, statusLabel.getHeight());
		
		friendHeader = new GLabel ("Friends:");
		friendHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		messageLabel.setLabel(msg);
		messageLabel.setFont(MESSAGE_FONT);
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
	private void addName(String name) {
		nameLabel.setLabel(name);
		add(nameLabel);
	}
	
	private void addImage(GImage image) {
		if (image != null) {
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image, LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN));
		} else {
			add(emptyBox);
			add(noImage);
		}
	}
	
	private void addStatus(String name, String status) {
		String string = name + " is " + status;
		if (status.equals("")) {
			string = "No current status";
		}
		statusLabel.setLabel(string);
		add(statusLabel);
	}
	
	private void addFriends(Iterator <String> friends){
		add(friendHeader, getWidth()/2 , emptyBox.getY() - friendHeader.getHeight());
		double y = (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN);
		while (friends.hasNext()) {
			y += friendHeader.getHeight();
			GLabel friend = new GLabel (friends.next(), getWidth()/2, y);
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend);
		}
		
	}
}
