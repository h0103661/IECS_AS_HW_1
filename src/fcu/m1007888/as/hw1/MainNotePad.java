package fcu.m1007888.as.hw1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MainNotePad extends JFrame{
	
	private static MainNotePad instance;
	
	private static final long serialVersionUID = 1L;
	private static String frameName = "記事本程式" + " M1007888 呂宥融";
	
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
		
		/*
		 * TEST
		 */
		Note test1 = new Note();
		test1.setUID(1);
		test1.setPeople("自己");
		mapNote.put(1, test1);
		
		Note test2 = new Note();
		test2.setUID(205);
		test2.setPeople("老師");
		mapNote.put(2, test2);
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
	
	/*
	 * gui
	 */
	
	private int layoutX = 1024;
	private int layoutY = 768;
    private Container containerMain;
	
	private void initGui() {
		/*
		 * main frame
		 */
        setSize(layoutX, layoutY);
        setTitle(frameName);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        BorderLayout layoutMain = new BorderLayout();
        containerMain = getContentPane();
        containerMain.setLayout(layoutMain);
        
        /*
         * basic
         */
        JPanel jp_basicBtn = new JPanel();
        
        JButton btn_add = addJButton("新增記錄", 0, 0);
        btn_add.addActionListener(null);
        jp_basicBtn.add(btn_add);
        
        JButton btn_showall = addJButton("顯示所有記錄", 100, 0);
        btn_showall.addActionListener(null);
        jp_basicBtn.add(btn_showall);
        
        JButton btn_search = addJButton("查詢記錄", 200, 0);
        btn_search.addActionListener(null);
        jp_basicBtn.add(btn_search);
        
        containerMain.add(jp_basicBtn, BorderLayout.NORTH);
        
        /*
         * load Note
         */
        JPanel jp_notes = new JPanel();
        FlowLayout layoutNotes = new FlowLayout();
        jp_notes.setLayout(layoutNotes);
        
        if (!mapNote.isEmpty()) {
        	for (Note n : mapNote.values()) {
        		JPanel p = addGuiNote(n);
        		jp_notes.add(p);
        	}
        } else {
        	jp_notes.add(addJLabel("無記錄", 0, 0, 100, 25));
        }
        
        JScrollPane sp = new JScrollPane(jp_notes, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        containerMain.add(sp, BorderLayout.CENTER);
        
        /*
         * finish
         */
        
        
        setVisible(true);
	}
	
	private JPanel addGuiNote(Note note) {
		JPanel p = new JPanel();
		p.setBounds(0, 0, 1000, 700);
		GridLayout layout = new GridLayout(10, 1);
		p.setLayout(layout);
		
		p.add(addJLabel("UID" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("" + note.getUID(), 0, 0, 500, 25));
		p.add(addJLabel("人物" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("" + note.getPeople(), 0, 0, 500, 25));
		p.add(addJLabel("時間" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("" + note.getTime().toString(), 0, 0, 500, 25));
		p.add(addJLabel("地點" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("" + note.getLocation(), 0, 0, 500, 25));
		p.add(addJLabel("描述" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("" + note.getDescription(), 0, 0, 500, 25));
		p.add(addJLabel("附件" + ":", 0, 0, 100, 25));
		p.add(addJTextFieldFix("", 0, 0, 500, 500));
		
		return p;
	}
	
	///////////////////////////////////
	
	private JLabel addJLabel(String title, int x, int y, int width, int height) {
		JLabel j = new JLabel(title);
		j.setBounds(x, y, width, height);
		
		return j;
	}
	
	private JButton addJButton(String title, int x, int y) {
		JButton j = new JButton(title);
		j.setBounds(x, y, 100, 25);
		
		return j;
	}
	
	private JTextField addJTextFieldFix(String text, int x, int y, int width, int height) {
		JTextField j = addJTextField(text, x, y, width, height);
		j.setEditable(false);
		
		return j;
	}
	
	private JTextField addJTextField(String text, int x, int y, int width, int height) {
		JTextField j = new JTextField();
		j.setBounds(x, y, width, height);
		j.setText(text);
		
		return j;
	}
}
