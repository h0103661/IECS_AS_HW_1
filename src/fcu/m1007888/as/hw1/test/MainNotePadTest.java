package fcu.m1007888.as.hw1.test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import fcu.m1007888.as.hw1.MainNotePad;
import fcu.m1007888.as.hw1.Note;

@SuppressWarnings("unused")
class MainNotePadTest {
	
	private MainNotePad instance;
	private Note testNote;
	
	public MainNotePadTest() {
		instance = new MainNotePad();
		instance.createMapNote();
		
		testNote = getTestNote();
	}
	
	private Note getTestNote() {
		Note note = new Note();
		note.setUID(1);
    	note.setPeople("dummy");
    	note.setTime("2021/01/01");
    	note.setLocation("junit");
    	note.setDescription("this is a test note");
    	
    	return note;
	}
	
	@Test
	public void addNote() {
		/*
		 * values
		 */
		testNote = getTestNote(); //init
		
		/*
		 * test
		 */
    	Note newNote = instance.addNote(testNote);
    	
    	/*
    	 * expected
    	 */
    	Note expectedNote = testNote;
    	
		assertEquals(expectedNote, newNote);
	}

	@Test
	public void modifyNote() {
		/*
		 * values
		 */
		String people = "tester";
		String description = "";
		String time = "";
		String location = "";
		
		testNote = getTestNote(); //init
		testNote.setPeople(people);
		
		/*
		 * test
		 */
    	Note modifedNote = instance.modifyNote(testNote);
    	
    	/*
    	 * expected
    	 */
    	Note expectedNote = testNote;
    	
		assertEquals(expectedNote, modifedNote);
	}
	
	@Test
	public void deleteNote() {
		/*
		 * values
		 */
		testNote = getTestNote();
		
		/*
		 * test
		 */
		instance.deleteNote(testNote.getUID());
    	boolean hasNote = instance.hasNote(testNote.getUID());
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = false;
    	
		assertEquals(expectedAns, hasNote);
	}
	
	@Test
	public void addNotes300() {
		/*
		 * values
		 */
		int testAmount = 301;
		testNote = getTestNote();
		
		/*
		 * test
		 */
		for(int i = 0; i < testAmount; i++) {
			instance.addNote(testNote);
		}
		
    	int countNote = instance.getMapNotes().size();
    	
    	/*
    	 * expected
    	 */
    	int expectedAns = testAmount;
    	
		assertEquals(expectedAns, countNote);
	}

}
