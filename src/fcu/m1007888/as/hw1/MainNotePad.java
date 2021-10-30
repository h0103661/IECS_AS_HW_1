package fcu.m1007888.as.hw1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
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
		test1.setLocation("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		test1.setExtraLoc("img.jpeg");
		mapNote.put(1, test1);
		
		Note test2 = new Note();
		test2.setUID(205);
		test2.setPeople("老師");
		test2.setDescription("123456\n67890");
		mapNote.put(2, test2);
		
		/*
		 * loadImg
		 */
		
		for(Note n : mapNote.values()) {
			n.loadImg();
		}
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
        
        JLabel lb_total = addJLabel("總數: " + mapNote.size(), 200, 0, 100, 25);
        jp_basicBtn.add(lb_total);
        
        containerMain.add(jp_basicBtn, BorderLayout.NORTH);
        
        /*
         * load Note
         */
        JPanel jp_notes = new JPanel();
        BoxLayout layoutNotes = new BoxLayout(jp_notes, BoxLayout.PAGE_AXIS);
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
		p.setPreferredSize(new Dimension(1000, 700));
		GridBagLayout layout = new GridBagLayout();
		p.setLayout(layout);
		
		int lx1 = 100;
		int ly1 = 25;
		GridBagConstraints bag1 = new GridBagConstraints();
		bag1.gridx = 0;
		bag1.gridy = 0;
		p.add(addJLabel("UID" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 1;
		p.add(addJLabel("人物" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 2;
		p.add(addJLabel("時間" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 3;
		p.add(addJLabel("地點" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 4;
		p.add(addJLabel("描述" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 5;
		p.add(addJLabel("附件" + ":", 0, 0, lx1, ly1), bag1);
		
		int lx2 = 900;
		int ly2 = 25;
		int ly2b = 200;
		int ly2c = 300;
		GridBagConstraints bag2 = new GridBagConstraints();
		bag2.gridx = 1;
		bag2.gridy = 0;
		p.add(addJTextFieldFix("" + note.getUID(), 0, 0, lx2, ly2), bag2);
		bag2.gridx = 1;
		bag2.gridy = 1;
		p.add(addJTextFieldFix("" + note.getPeople(), 0, 0, lx2, ly2), bag2);
		bag2.gridx = 1;
		bag2.gridy = 2;
		p.add(addJTextFieldFix("" + note.getTime().toString(), 0, 0, lx2, ly2), bag2);
		bag2.gridx = 1;
		bag2.gridy = 3;
		p.add(addJTextFieldFix("" + note.getLocation(), 0, 0, lx2, ly2), bag2);
		bag2.gridx = 1;
		bag2.gridy = 4;
		p.add(addJTextAreaFix("" + note.getDescription(), 0, 0, lx2, ly2b), bag2);
		bag2.gridx = 1;
		bag2.gridy = 5;
		if(note.hasExtra() == 1) {
			JLabel j = new JLabel(new ImageIcon(note.getImg()));
			JScrollPane sp = new JScrollPane(j);
			sp.setPreferredSize(new Dimension(lx2, ly2c));
			p.add(sp, bag2);
		} else if(note.hasExtra() == 2) {
			p.add(addJTextFieldFix("", 0, 0, lx2, ly2c), bag2);
		} else {
			p.add(addJTextFieldFix("", 0, 0, lx2, ly2c), bag2);
		}
		
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
		j.setPreferredSize(new Dimension(width, height));
		
		return j;
	}
	
	private JTextArea addJTextAreaFix(String text, int x, int y, int width, int height) {
		JTextArea j = addJTextArea(text, x, y, width, height);
		j.setEditable(false);
		
		return j;
	}
	
	private JTextArea addJTextArea(String text, int x, int y, int width, int height) {
		JTextArea j = new JTextArea();
		j.setBounds(x, y, width, height);
		j.setText(text);
		j.setPreferredSize(new Dimension(width, height));
		
		return j;
	}
}
