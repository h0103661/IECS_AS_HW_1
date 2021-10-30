package fcu.m1007888.as.hw1;

import java.util.Map;
import java.util.TreeMap;

public class MainNotePad {
	
	private static MainNotePad instance;
	
	private static final long serialVersionUID = 1L;
	private static String frameName = "記事本程式" + "M1007888 呂宥融";
	
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
	
	private void init() {
		loadNote();
		initGui();
	}
	
	private void loadNote() {
		mapNote = new TreeMap<Integer, Note>();
		
		
	}
	
	/*
	 * gui
	 */
	
	private void initGui() {
		
	}
	
	/*
	 * function
	 */
	
	private void addNote() {
		
	}
	
	private void modifyNote() {
		
	}
	
	private void deleteNote() {
		
	}
	
	private void showNote() {
		
	}
	
	private void searchNote() {
		
	}
}
