package fcu.m1007888.as.hw1;

import java.awt.Image;
import java.util.Date;

public class Note {
	
	private int UID;
	
	private String people;
	private String description;
	private Date time;
	private String location;
	
	private Image img;
	private String med;

	public Note() {
		
	}
	
	/*
	 * getter & setter
	 */

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String getMed() {
		return med;
	}

	public void setMed(String med) {
		this.med = med;
	}
	
	/*
	 * 
	 */
	
	public void show() {
		
	}
	
	public boolean search(String people, String description, Date time, String location) {
		return false;
	}
}
