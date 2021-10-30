package fcu.m1007888.as.hw1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class MainNotePad extends JFrame implements WindowListener{
	
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
		loadNotes();
		initGui();
		
	}
	
	/*
	 * function
	 */
	
	private void loadNotes() {
		mapNote = new TreeMap<Integer, Note>();
		File folder = new File("data");
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		/*
		 * load
		 */
		for (File f : folder.listFiles()) {
			Note note = loadNoteFromFile(f);
			mapNote.put(note.getUID(), note);
		}
		
		/*
		 * TEST
		 */
		/*Note test1 = new Note();
		test1.setUID(1);
		test1.setPeople("自己");
		test1.setTime(new java.util.Date());
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
		
		if(!mapNote.isEmpty()) {
			for(Note n : mapNote.values()) {
				n.loadImg();
			}
		}
		
	}
	
	private void saveNotes() {
		logDEBUG("[saveNotes] Start");
		if(!mapNote.isEmpty()) {
			for(Note n : mapNote.values()) {
				saveNotetoFile(n);
			}
		}
	}
	
	private Note loadNoteFromFile(File file) {
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);
			Note note = (Note) oi.readObject();
			
			/*note.setUID(1);
			note.setPeople("自己");
			note.setTime(new java.util.Date());
			note.setLocation("eclipse workspace");
			note.setDescription("這是一個測試記錄\n包含完整訊息和一張預設圖片。");
			note.setExtraLoc("img.jpeg");*/
			
			oi.close();
			fi.close();
			
			return note;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void saveNotetoFile(Note note) {
		logDEBUG("[saveNotes] saveNotetoFile");
		try {
			FileOutputStream fo = new FileOutputStream(new File("data" + File.separator + "note_" + note.getUID() + ".data"));
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			logDEBUG("[saveNotes] write " + note.getUID());
			oo.writeObject(note);
			logDEBUG("[saveNotes] finish");
			
			oo.close();
			fo.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
        
        JButton btn_add = addJButton("新增記錄", 0, 0, 100, 25);
        btn_add.addActionListener(getALAddNote());
        jp_basicBtn.add(btn_add);
        
        JButton btn_showall = addJButton("顯示所有記錄", 100, 0, 100, 25);
        btn_showall.addActionListener(getALShowNote());
        jp_basicBtn.add(btn_showall);
        
        JButton btn_search = addJButton("查詢記錄", 200, 0, 100, 25);
        btn_search.addActionListener(getALSearchNote());
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
        
        addWindowListener(this);
        setVisible(true);
	}
	
	private int color = 0;
	
	private JPanel addGuiNote(Note note) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1000, 750));
		GridBagLayout layout = new GridBagLayout();
		p.setLayout(layout);
		if (color == 0) {
			color = 1;
			p.setBackground(Color.GREEN);
		} else {
			color = 0;
			p.setBackground(Color.YELLOW);
		}
		
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
		bag1.gridx = 0;
		bag1.gridy = 6;
		JButton btnM = addJButton("修改", 0, 0, lx1, ly1);
		btnM.addActionListener(getALNoteModify(note.getUID()));
		p.add(btnM, bag1);
		bag1.gridx = 0;
		bag1.gridy = 7;
		JButton btnD = addJButton("刪除", 0, 0, lx1, ly1);
		btnD.addActionListener(getALNoteDelete(note.getUID()));
		p.add(btnD, bag1);
		
		/*
		 * 
		 */
		
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
	
	private JButton addJButton(String title, int x, int y, int width, int height) {
		JButton j = new JButton(title);
		j.setBounds(x, y, width, height);
		
		return j;
	}
	
	private JTextField addJTextFieldFix(String text, int x, int y, int width, int height) {
		JTextField j = addJTextField(text, x, y, width, height);
		j.setEditable(false);
		j.setBackground(Color.WHITE);
		
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
		j.setBackground(Color.WHITE);
		
		return j;
	}
	
	private JTextArea addJTextArea(String text, int x, int y, int width, int height) {
		JTextArea j = new JTextArea();
		j.setBounds(x, y, width, height);
		j.setText(text);
		j.setPreferredSize(new Dimension(width, height));
		
		return j;
	}
	
	/*
	 * 
	 */
	
	private ActionListener getALAddNote() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	private ActionListener getALShowNote() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	private ActionListener getALSearchNote() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	private ActionListener getALNoteModify(int noteUID) {
		ActionListener al = new ActionListener() {

			final int UID = noteUID;
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	private ActionListener getALNoteDelete(int noteUID) {
		ActionListener al = new ActionListener() {

			final int UID = noteUID;
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	/*
	 * Window Listener
	 */

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		saveNotes();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
}
