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
	private GLabel statusLabel;
	private GRect emptyBox;
	private GLabel noImage;
	private GLabel friendHeader;
	
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		messageLabel = null;
		
		nameLabel = new GLabel("", LEFT_MARGIN, TOP_MARGIN);
		nameLabel.setColor(Color.BLUE);
		nameLabel.setFont(PROFILE_NAME_FONT);
		
		statusLabel = new GLabel("", LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN + STATUS_MARGIN));
		
		emptyBox = new GRect(LEFT_MARGIN, (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN), IMAGE_WIDTH, IMAGE_HEIGHT);
		
		noImage = new GLabel("No Image", LEFT_MARGIN+(IMAGE_WIDTH/2), (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN)+(IMAGE_HEIGHT/2));
		noImage.setFont(PROFILE_IMAGE_FONT);
		noImage.move(-noImage.getWidth()/2, -noImage.getHeight()/2);
		
		friendHeader = new GLabel ("Friends:" , getWidth()/2 , (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN));
		friendHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendHeader.move(0, -friendHeader.getHeight());
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		remove(messageLabel);
		messageLabel = new GLabel(msg, 0, getHeight()-BOTTOM_MESSAGE_MARGIN);
		messageLabel.setFont(MESSAGE_FONT);
		messageLabel.move((getWidth()/2)-(messageLabel.getWidth()/2), -messageLabel.getHeight());
		add(messageLabel);
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
		addName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getName(), profile.getStatus());
		addFriends(profile.getFriends());
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
		double y = (TOP_MARGIN + nameLabel.getHeight() + IMAGE_MARGIN);
		while (friends.hasNext()) {
			y += friendHeader.getHeight();
			GLabel friend = new GLabel (friends.next(), getWidth()/2, y);
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend);
		}
		
	}
}
