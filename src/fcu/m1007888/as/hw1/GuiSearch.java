package fcu.m1007888.as.hw1;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GuiSearch extends JFrame{

	private static final long serialVersionUID = 1L;
	private static String frameName = "記事本程式";
	
	public GuiSearch() {
		
	}
	
	private String people = "";
	private String description = "";
	private Date time = new java.util.Date();
	private String location = "";
	
	/*
	 * gui
	 */
	
	private int layoutX = 800;
	private int layoutY = 600;
    private Container containerMain;
	
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
		JButton btnS = addJButton("送出", 0, 0, lx1, ly1);
		btnS.addActionListener(getALSearch());
		containerMain.add(btnS, bag1);
		
		/*
         * finish
         */
        
        setVisible(true);
        
        MainNotePad.getinstance().logDEBUG("[search] search gui created, wait for command");
    }
    
    private JButton addJButton(String title, int x, int y, int width, int height) {
		JButton j = new JButton(title);
		j.setBounds(x, y, width, height);
		
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
    	MainNotePad.getinstance().searchNote(people, description, time, location);
    }
}
