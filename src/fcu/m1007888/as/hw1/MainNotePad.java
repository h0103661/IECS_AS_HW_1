package fcu.m1007888.as.hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class MainNotePad{
	
	private static MainNotePad instance;
	
	private static boolean DEBUG = true;
	
	/*
	 * create instance
	 */

	public static void main(String argv[]) {
		instance = new MainNotePad();
		instance.init();
	}
	
	public MainNotePad() {
		
	}
	
	public static MainNotePad getinstance() {
		return instance;
	}
	
	/*
	 * log DEBUG
	 */
	
	public void log(String msg) {
    	System.out.println(msg);
	}
	
	public void logDEBUG(String msg) {
		if(DEBUG)System.out.println(msg);
	}
	
	/*
	 * init
	 */
	
	private Map<Integer, Note> mapNote;
	private GuiMain guiMain;
	
	private void init() {
		loadNotes();
		
		guiMain = new GuiMain();
		getGuiMain().initGui(getMapNotes());
	}
	
	public void clear() {
		getGuiMain().clearGui();
	}
	
	public void reload(Map<Integer, Note> note) {
		getGuiMain().initGui(note);
	}
	
	public GuiMain getGuiMain() {
		return guiMain;
	}
	
	/*
	 * function
	 */
	
	private void loadNotes() {
		logDEBUG("[loadNotes] Start ===============");
		
		mapNote = new TreeMap<Integer, Note>();
		File folder = new File("data");
		if (!folder.exists()) {
			folder.mkdir();
			logDEBUG("[loadNotes] make data folder");
		}
		
		/*
		 * load
		 */
		logDEBUG("[loadNotes] load files");
		for (File f : folder.listFiles()) {
			Note note = loadNoteFromFile(f);
			mapNote.put(note.getUID(), note);
			logDEBUG("[loadNotes] loaded note " + note.getUID());
		}
		
		/*
		 * TEST
		 */
		/*Note test1 = new Note();
		test1.setUID(1);
		test1.setPeople("自己");
		test1.setLocation("eclipse workspace");
		test1.setDescription("這是一個測試記錄\n包含完整訊息和一張預設圖片。");
		test1.setExtraLoc("img.jpeg");
		mapNote.put(1, test1);
		
		Note test2 = new Note();
		test2.setUID(205);
		test2.setPeople("老師");
		test2.setDescription("這是一個測試記錄\n訊息並不完全\n用來測試例外錯誤");
		mapNote.put(2, test2);*/
		
		/*
		 * loadImg
		 */
		
		logDEBUG("[loadNotes] load media");
		if(!mapNote.isEmpty()) {
			for(Note n : mapNote.values()) {
				n.loadImg();
			}
		}
		logDEBUG("[loadNotes] Finish ===============");
		
	}
	
	public void saveNotes() {
		logDEBUG("[saveNotes] Start ===============");
		if(!mapNote.isEmpty()) {
			for(Note n : mapNote.values()) {
				saveNotetoFile(n);
			}
		}
		logDEBUG("[saveNotes] Finish ===============");
	}
	
	private Note loadNoteFromFile(File file) {
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Note note = (Note) oi.readObject();
			
			oi.close();
			fi.close();
			
			return note;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void saveNotetoFile(Note note) {
		try {
			FileOutputStream fo = new FileOutputStream(new File("data" + File.separator + "note_" + note.getUID() + ".data"));
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			logDEBUG("[saveNotes] saveNotetoFile " + note.getUID());
			oo.writeObject(note);
			logDEBUG("[saveNotes] write " + note.getUID() + " finish");
			
			oo.close();
			fo.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteNoteFile(int UID) {
		File file = new File("data" + File.separator + "note_" + UID + ".data");
		if (file.exists()) {
			file.delete();
		}
	}
	
	public Map<Integer, Note> getMapNotes() {
		return mapNote;
	}
	
	public Note getNote(int UID) {
		return getMapNotes().get(UID);
	}
	
	public void addNote(Note note) {
		int UID = 1;
		while(getMapNotes().get(UID) != null) {
			UID++;
		}
		note.setUID(UID);
		getMapNotes().put(note.getUID(), note);
		
		logDEBUG("[add] reload gui");
		clear();
		reload(getMapNotes());
		
		logDEBUG("[add] Finish ===============");
	}
	
	public void modifyNote(Note note) {
		getMapNotes().put(note.getUID(), note);
		
		logDEBUG("[modify] reload gui");
		clear();
		reload(getMapNotes());
		
		logDEBUG("[modify] Finish ===============");
	}
	
	public void deleteNote(int UID) {
		if(!getMapNotes().isEmpty()) {
			getMapNotes().remove(UID);
			logDEBUG("[deleteNote] remove from map");
		}
		deleteNoteFile(UID);
		logDEBUG("[deleteNote] delete file");
	}
	
	public void searchNote(String people, String description, String time, String location) {
		MainNotePad.getinstance().logDEBUG("[search] people: " + people);
		MainNotePad.getinstance().logDEBUG("[search] description: " + description);
		if(time == null) {
			MainNotePad.getinstance().logDEBUG("[search] time: " + "NULL");
		} else {
			MainNotePad.getinstance().logDEBUG("[search] time: " + time.toString());
		}
		MainNotePad.getinstance().logDEBUG("[search] location: " + location);
		
		Map<Integer, Note> notes = new TreeMap<Integer, Note>();
		
		for(Note n : getMapNotes().values()) {
			if (n.search(people, description, time, location)) {
				notes.put(n.getUID(), n);
				MainNotePad.getinstance().logDEBUG("[search] add note " + n.getUID());
			}
		}
		
		logDEBUG("[search] reload gui");
		clear();
		reload(notes);
		
		logDEBUG("[search] Finish ===============");
	}
}
