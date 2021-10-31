package fcu.m1007888.as.hw1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiModify extends JFrame{

	private static final long serialVersionUID = 1L;
	private static String frameName = "�ק�";
	
	public GuiModify() {
		
	}
	
	/*
	 * gui
	 */
	
	private int layoutX = 1024;
	private int layoutY = 768;
    private Container containerMain;
    private JTextField tf_people, tf_time, tf_location;
	private JTextArea tf_description;
	private String tf_extra;
    
    private int UID = 0;
    
    public void initGui(int noteUID) {
    	UID = noteUID;
    	Note note = MainNotePad.getinstance().getNote(noteUID);
    	
    	/*
		 * main frame
		 */
        setSize(layoutX, layoutY);
        setTitle(frameName);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        GridBagLayout layout = new GridBagLayout();
        containerMain = getContentPane();
		containerMain.setLayout(layout);
		
		int lx1 = 100;
		int ly1 = 25;
		GridBagConstraints bag1 = new GridBagConstraints();
		bag1.gridx = 0;
		bag1.gridy = 0;
		containerMain.add(addJLabel("UID" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 1;
		containerMain.add(addJLabel("�H��" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 2;
		containerMain.add(addJLabel("�ɶ�" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 3;
		containerMain.add(addJLabel("�a�I" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 4;
		containerMain.add(addJLabel("�y�z" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 5;
		containerMain.add(addJLabel("����" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 6;
		JButton btnM = addJButton("������", 0, 0, lx1, ly1);
		btnM.addActionListener(getALChangeExtra());
		containerMain.add(btnM, bag1);
		bag1.gridx = 0;
		bag1.gridy = 7;
		JButton btnS = addJButton("�e�X", 0, 0, lx1, ly1);
		btnS.addActionListener(getALSendModify());
		containerMain.add(btnS, bag1);
		
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
		containerMain.add(addJTextFieldFix("" + note.getUID(), 0, 0, lx2, ly2), bag2);
		bag2.gridx = 1;
		bag2.gridy = 1;
		tf_people = addJTextField("" + note.getPeople(), 0, 0, lx2, ly2);
		containerMain.add(tf_people, bag2);
		bag2.gridx = 1;
		bag2.gridy = 2;
		tf_time = addJTextField("" + note.getTime().toString(), 0, 0, lx2, ly2);
		containerMain.add(tf_time, bag2);
		bag2.gridx = 1;
		bag2.gridy = 3;
		tf_location = addJTextField("" + note.getLocation(), 0, 0, lx2, ly2);
		containerMain.add(tf_location, bag2);
		bag2.gridx = 1;
		bag2.gridy = 4;
		tf_description = addJTextArea("" + note.getDescription(), 0, 0, lx2, ly2b);
		containerMain.add(tf_description, bag2);
		bag2.gridx = 1;
		bag2.gridy = 5;
		if(note.hasExtra() == 1) {
			tf_extra = note.getExtraLoc();
			JLabel j = new JLabel(new ImageIcon(note.getImg()));
			JScrollPane sp = new JScrollPane(j);
			sp.setPreferredSize(new Dimension(lx2, ly2c));
			containerMain.add(sp, bag2);
		} else if(note.hasExtra() == 2) {
			containerMain.add(addJTextFieldFix("", 0, 0, lx2, ly2c), bag2);
		} else {
			containerMain.add(addJTextFieldFix("", 0, 0, lx2, ly2c), bag2);
		}
		
		setVisible(true);
    }
    
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
	
	private ActionListener getALChangeExtra() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		return al;
	}
	
	private ActionListener getALSendModify() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[modify] start modify");
				sendModify();
				dispose();
				MainNotePad.getinstance().logDEBUG("[modify] dispose modify gui");
			}
			
		};
		return al;
	}
	
	/*
	 * function
	 */
    
    private void sendModify() {
    	Note note = new Note();
    	note.setUID(UID);
    	note.setPeople(tf_people.getText());
    	note.setTime(tf_time.getText());
    	note.setLocation(tf_location.getText());
    	note.setDescription(tf_description.getText());
    	note.setExtraLoc(tf_extra);
    	note.loadImg();
    	MainNotePad.getinstance().modifyNote(note);
    }
}
