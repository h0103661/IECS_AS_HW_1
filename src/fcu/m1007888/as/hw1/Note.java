package fcu.m1007888.as.hw1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.imageio.ImageIO;

public class Note implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int UID;
	
	private String people;
	private String description;
	private Date time;
	private String location;
	
	private String extraLoc;
	transient private BufferedImage img;
	transient private String med;

	public Note() {
		setUID(0);
		setPeople("");
		setTime(new java.util.Date());
		setLocation("");
		setDescription("");
		setExtraLoc("");
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
		if(people == null || people.isEmpty() || people.isBlank()) {
			return "";
		}
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getDescription() {
		if(description == null || description.isEmpty() || description.isBlank()) {
			return "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		if(time == null) {
			return "";
		}
		return time.toString();
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLocation() {
		if(location == null || location.isEmpty() || location.isBlank()) {
			return "";
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExtraLoc() {
		if(extraLoc == null || extraLoc.isEmpty() || extraLoc.isBlank()) {
			return "";
		}
		return extraLoc;
	}

	public void setExtraLoc(String extraLoc) {
		this.extraLoc = extraLoc;
	}
	
	public int hasExtra() {
		if(getExtraLoc() == null || getExtraLoc().isEmpty() || getExtraLoc().isBlank()) {
			return 0;
		}
		if(getImg() != null) {
			return 1;
		}
		if(getMed() != null) {
			return 2;
		}
		return 0;
	}
	
	public void loadImg() {
		if(getExtraLoc() == null || getExtraLoc().isEmpty() || getExtraLoc().isBlank()) {
			return;
		}
		File f = new File(getExtraLoc());
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
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
	
	public boolean search(String people, String description, Date time, String location) {
		return true;
	}
	
	/*
	 * 
	 */
	
	@Override
	public String toString() {
		return 
				"UID:" + this.getUID() + "\n" + 
				"People: " + this.getPeople() + "\n" + 
				"Date: " + this.getTime() + "\n" + 
				"Location: " + this.getLocation() + "\n" + 
				"Description: " + this.getDescription() + "\n" + 
				"ExtraLoc: " + this.getExtraLoc() + "\n";
	}
}
