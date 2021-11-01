package fcu.m1007888.as.hw1.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import fcu.m1007888.as.hw1.Note;

class NoteTest {
	
	private Note testNote;
	
	public NoteTest() {
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
	public void searchPeople() {
		/*
		 * values
		 */
		String people = "dummy";
		String description = "";
		String time = "";
		String location = "";
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(people, description, time, location);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
	
	@Test
	public void searchDescription() {
		/*
		 * values
		 */
		String people = "";
		String description = "test note";
		String time = "";
		String location = "";
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(people, description, time, location);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
	
	@Test
	public void searchTime() {
		/*
		 * values
		 */
		String people = "";
		String description = "";
		String time = "2021/01/01";
		String location = "";
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(people, description, time, location);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
	
	@Test
	public void searchLocation() {
		/*
		 * values
		 */
		String people = "";
		String description = "";
		String time = "";
		String location = "junit";
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(people, description, time, location);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
	
	@Test
	public void searchMulti() {
		/*
		 * values
		 */
		String people = "dummy";
		String description = "test note";
		String time = "2021/01/01";
		String location = "junit";
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(people, description, time, location);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
	
	@Test
	public void searchWeek() {
		/*
		 * values
		 */
		int week = 1;
		
		/*
		 * test
		 */
    	boolean testAns = testNote.search(week);
    	
    	/*
    	 * expected
    	 */
    	boolean expectedAns = true;
    	
		assertEquals(expectedAns, testAns);
	}
}
