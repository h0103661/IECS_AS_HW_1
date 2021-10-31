package fcu.m1007888.as.hw1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GuiMain extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	private static String frameName = "記事本程式" + " M1007888 呂宥融";
	
	public GuiMain() {
		
	}
	
	private int layoutX = 1024;
	private int layoutY = 768;
    private Container containerMain;
	
	public void initGui(Map<Integer, Note> notes) {
		MainNotePad.getinstance().logDEBUG("[initGui] Start ===============");
		
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
        
        JButton btn_searchDay = addJButton("依日期選取", 300, 0, 100, 25);
        btn_searchDay.addActionListener(getALSearchNoteDay());
        jp_basicBtn.add(btn_searchDay);
        
        JButton btn_searchWeek = addJButton("依周選取", 400, 0, 100, 25);
        btn_searchWeek.addActionListener(getALSearchNoteWeek());
        jp_basicBtn.add(btn_searchWeek);
        
        JLabel lb_total = addJLabel("數量: " + notes.size(), 500, 0, 100, 25);
        jp_basicBtn.add(lb_total);
        
        containerMain.add(jp_basicBtn, BorderLayout.NORTH);
        
        /*
         * load Note
         */
        MainNotePad.getinstance().logDEBUG("[initGui] load notes gui");
        
        JPanel jp_notes = new JPanel();
        BoxLayout layoutNotes = new BoxLayout(jp_notes, BoxLayout.PAGE_AXIS);
        jp_notes.setLayout(layoutNotes);
        
        if (!notes.isEmpty()) {
        	for (Note n : notes.values()) {
        		JPanel p = addGuiNote(n);
        		jp_notes.add(p);
        		MainNotePad.getinstance().logDEBUG("[initGui] add " + n.getUID());
        	}
        } else {
        	jp_notes.add(addJLabel("查無記錄", 0, 0, 100, 25));
        	MainNotePad.getinstance().logDEBUG("[initGui] no notes, skip");
        }
        
        JScrollPane sp = new JScrollPane(jp_notes, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        containerMain.add(sp, BorderLayout.CENTER);
        
        /*
         * finish
         */
        
        addWindowListener(this);
        setVisible(true);
        
        MainNotePad.getinstance().logDEBUG("[initGui] Finish ===============");
	}
	
	public void clearGui() {
    	containerMain.removeAll();
    	containerMain.revalidate();
    	containerMain.repaint();
    	MainNotePad.getinstance().logDEBUG("[clearGui] clear!");
    }
	
	/*
	 * main control
	 */
	
	/*
	 * module
	 */
	
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
				GuiAdd guiAdd = new GuiAdd();
				guiAdd.initGui();
			}
			
		};
		return al;
	}
	
	private ActionListener getALShowNote() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[showAllNote] reload gui");
				MainNotePad.getinstance().clear();
				MainNotePad.getinstance().reload(MainNotePad.getinstance().getMapNotes());
			}
			
		};
		return al;
	}
	
	private ActionListener getALSearchNote() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[search] Start ===============");
				if(MainNotePad.getinstance().getMapNotes().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "沒有任何記錄可以搜尋!", "警告", JOptionPane.WARNING_MESSAGE);
					MainNotePad.getinstance().logDEBUG("[search] no notes, exit");
				} else {
					GuiSearch guiSearch = new GuiSearch();
					guiSearch.initGui();
				}
			}
			
		};
		return al;
	}
	
	private ActionListener getALSearchNoteDay() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[search] Start ===============");
				if(MainNotePad.getinstance().getMapNotes().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "沒有任何記錄可以搜尋!", "警告", JOptionPane.WARNING_MESSAGE);
					MainNotePad.getinstance().logDEBUG("[search] no notes, exit");
				} else {
					String time = JOptionPane.showInputDialog(new JFrame(), "時間:", "依日期選取", JOptionPane.INFORMATION_MESSAGE);
					MainNotePad.getinstance().searchNote("", "", time, "");
				}
			}
			
		};
		return al;
	}
	
	private ActionListener getALSearchNoteWeek() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[search] Start ===============");
				if(MainNotePad.getinstance().getMapNotes().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "沒有任何記錄可以搜尋!", "警告", JOptionPane.WARNING_MESSAGE);
					MainNotePad.getinstance().logDEBUG("[search] no notes, exit");
				} else {
					String week = JOptionPane.showInputDialog(new JFrame(), "周:", "依周選取", JOptionPane.INFORMATION_MESSAGE);
					MainNotePad.getinstance().searchNoteByWeek(week);
				}
			}
			
		};
		return al;
	}
	
	private ActionListener getALNoteModify(int noteUID) {
		ActionListener al = new ActionListener() {

			final int UID = noteUID;
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiModify guiModify = new GuiModify();
				guiModify.initGui(UID);
			}
			
		};
		return al;
	}
	
	private ActionListener getALNoteDelete(int noteUID) {
		ActionListener al = new ActionListener() {

			final int UID = noteUID;
			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[deleteNote] Start delete note " + UID);
				MainNotePad.getinstance().deleteNote(UID);
				MainNotePad.getinstance().logDEBUG("[deleteNote] finish, reload gui");
				MainNotePad.getinstance().clear();
				MainNotePad.getinstance().reload(MainNotePad.getinstance().getMapNotes());
			}
			
		};
		return al;
	}
	
	/*
	 * WindowListener
	 */

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainNotePad.getinstance().saveNotes();
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
