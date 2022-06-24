package View;

import javax.swing.*;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * In this class we are going to represent the branch scheduele by a table.
 * @author Tamir
 *
 */
public class ShowActivity extends JFrame{
	/**
	 * @columns represents the table columns.
	 * @data represents the data in the table - open hour, close hour, etc...
	 * @table represents the table.
	 * @scrollPane represents the scroll down and up option in the table
	 * @back represents back where we go back to the client frame.
	 */
	private static final long serialVersionUID = 12L;
	
	private String[] columns; 		
	private Object [][] data;		
	private JTable table;			
	private JScrollPane scrollPane;	
	private JButton back;
	
	public ShowActivity() {
		columns = new String[3];
		initalize();
	}
	
	@SuppressWarnings("unchecked")
	public void initalize() {	
		//Read from branchFile
		
		ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");
		data = new Object[branchList.size()][3];
		
		for(int i = 0; i < branchList.size(); i++){
				data[i][0] = branchList.get(i).getLocation();
				data[i][1] = ""+branchList.get(i).getBranchID();
				data[i][2] = ((Time)branchList.get(i).getBranchActivityTime()).toString();
		}
		columns[0]="Location";
		columns[1] = "ID";
		columns[2] = "Activity time";
		
		table = new JTable(data,columns);
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        back = new JButton("Back");
        
        setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.NORTH);		
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(scrollPane,BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);   
        table.setEnabled(false);
		pack();
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client c = new Client();
				c.setVisible(true);
			}
			
		});
		
		
		
		/////////////////////////////////////



	}
	
	

}
