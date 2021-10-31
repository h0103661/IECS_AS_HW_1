package fcu.m1007888.as.hw1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiSearch extends JFrame{

	private static final long serialVersionUID = 1L;
	private static String frameName = "搜尋";
	
	public GuiSearch() {
		
	}
	
	private String people = "";
	private String description = "";
	private String time = "";
	private String location = "";
	
	/*
	 * gui
	 */
	
	private int layoutX = 800;
	private int layoutY = 200;
    private Container containerMain;
    private JTextField tf_people, tf_description, tf_time, tf_location;
	
    public void initGui() {
    	MainNotePad.getinstance().logDEBUG("[search] init search gui");
    	
    	/*
		 * main frame
		 */
        setSize(layoutX, layoutY);
        setTitle(frameName);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        GridBagLayout layoutMain = new GridBagLayout();
        containerMain = getContentPane();
        containerMain.setLayout(layoutMain);
        
        /*
         * 
         */
        
        int lx1 = 100;
		int ly1 = 25;
		GridBagConstraints bag1 = new GridBagConstraints();
		bag1.gridx = 0;
		bag1.gridy = 0;
		containerMain.add(addJLabel("人物" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 1;
		containerMain.add(addJLabel("時間" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 2;
		containerMain.add(addJLabel("地點" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 3;
		containerMain.add(addJLabel("描述" + ":", 0, 0, lx1, ly1), bag1);
		bag1.gridx = 0;
		bag1.gridy = 4;
		JButton btnS = addJButton("送出", 0, 0, lx1, ly1);
		btnS.addActionListener(getALSearch());
		containerMain.add(btnS, bag1);
		
		int lx2 = 700;
		int ly2 = 25;
		GridBagConstraints bag2 = new GridBagConstraints();
		bag2.gridx = 1;
		bag2.gridy = 0;
		tf_people = addJTextField("", 0, 0, lx2, ly2);
		containerMain.add(tf_people, bag2);
		bag2.gridx = 1;
		bag2.gridy = 1;
		tf_time = addJTextField("", 0, 0, lx2, ly2);
		containerMain.add(tf_time, bag2);
		bag2.gridx = 1;
		bag2.gridy = 2;
		tf_location = addJTextField("", 0, 0, lx2, ly2);
		containerMain.add(tf_location, bag2);
		bag2.gridx = 1;
		bag2.gridy = 3;
		tf_description = addJTextField("", 0, 0, lx2, ly2);
		containerMain.add(tf_description, bag2);
		
		/*
         * finish
         */
        
        setVisible(true);
        
        MainNotePad.getinstance().logDEBUG("[search] search gui created, wait for command");
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
    
    private JTextField addJTextField(String text, int x, int y, int width, int height) {
		JTextField j = new JTextField();
		j.setBounds(x, y, width, height);
		j.setText(text);
		j.setPreferredSize(new Dimension(width, height));
		
		return j;
	}
    
    private ActionListener getALSearch() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainNotePad.getinstance().logDEBUG("[search] start search");
				sendSearch();
				dispose();
				MainNotePad.getinstance().logDEBUG("[search] dispose search gui");
			}
			
		};
		return al;
	}
	
	/*
	 * function
	 */
    
    private void sendSearch() {
    	if (tf_people != null && !tf_people.getText().isEmpty() && !tf_people.getText().isBlank()) {
    		people = tf_people.getText();
    	}
    	if (tf_description != null && !tf_description.getText().isEmpty() && !tf_description.getText().isBlank()) {
    		description = tf_description.getText();
    	}
    	if (tf_time != null && !tf_time.getText().isEmpty() && !tf_time.getText().isBlank()) {
    		time = tf_time.getText();
    	}
    	if (tf_location != null && !tf_location.getText().isEmpty() && !tf_location.getText().isBlank()) {
    		location = tf_location.getText();
    	}
    	
    	MainNotePad.getinstance().searchNote(people, description, time, location);
    }
}
