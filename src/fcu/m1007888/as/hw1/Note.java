package fcu.m1007888.as.hw1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class Note implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int UID;
	
	private String people;
	private String description;
	private String time;
	private String location;
	
	private String extraLoc;
	transient private BufferedImage img;
	transient private String med;

	public Note() {
		setUID(0);
		setPeople("");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd");
		Date now = new Date();
		setTime(format.format(now));
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
		if(time == null || time.isEmpty() || time.isBlank()) {
			return "";
		}
		return time.toString();
	}

	public void setTime(String time) {
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
	
	public boolean search(String people, String description, String time, String location) {
		boolean isTrue = false;
		if(people != null && !people.isEmpty() && !people.isBlank()) {
			if(this.getPeople().contains(people)) {
				isTrue = true;
			} else {
				return false;
			}
		}
		if(description != null && !description.isEmpty() && !description.isBlank()) {
			if(this.getDescription().contains(description)) {
				isTrue = true;
			} else {
				return false;
			}
		}
		if(location != null && !location.isEmpty() && !location.isBlank()) {
			if(this.getLocation().contains(location)) {
				isTrue = true;
			} else {
				return false;
			}
		}
		if(time != null && !time.isEmpty() && !time.isBlank()) {
			if(this.getTime().contains(time.toString())) {
				isTrue = true;
			} else {
				return false;
			}
		}
		
		return isTrue;
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
