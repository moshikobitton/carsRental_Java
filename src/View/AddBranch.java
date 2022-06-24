package View;
import Model.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

/**
 * This class represent the frame that sign up a new branch to our company.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class AddBranch extends JFrame{
	/**
	 * 	
	 * @branchNum Text field to insert branch id.
	 * @location Text field to insert branch location.
	 * @error Label to tell the user what is wrong.
	 * @addBtn Button that user click when he want to add branch.
	 * @back Button that user click when he want to go back.
	 * @openHour This choice contains all the hours (00-23) for open time.
	 * @closeHour This choice contains all the hours (00-23) for close time.
	 * @openMintues This choice contains all the minutes (00-60) for open time.
	 * @closeMintues This choice contains all the minutes (00-60) for close time.
	 */
	private static final long serialVersionUID = 6L;
	JTextField branchNum; //Text field to insert branch id.
	JTextField location;  //Text field to insert branch location.
	JLabel error;         //Label to tell the user what is wrong.
	JButton addBtn;		  //Button that user click when he want to add branch.
	JButton back;		  //Button that user click when he want to go back.
	Choice openHour;	  //This choice contains all the hours (00-23) for open time.
	Choice closeHour;	  //This choice contains all the hours (00-23) for close time.
	Choice openMintues;	  //This choice contains all the minutes (00-60) for open time.
	Choice closeMintues;  //This choice contains all the minutes (00-60) for close time.
	
	public AddBranch() {
		branchNum = new JTextField(15);
		location = new JTextField(25);
		openHour = new Choice();
		closeHour = new Choice();
		openMintues = new Choice();
		closeMintues = new Choice();
		addBtn = new JButton("Add");
		back = new JButton("Back");
		error = new JLabel("");
		error.setForeground(Color.RED);
		
		initalizeChoices();
		initalize();
		
	}
	
	public void initalizeChoices() {
		String str="0";
		for(int i = 0;i < 60;i++) {
			if(i < 10) {
				if(i < 24) {
					
					openHour.add(str + Integer.toString(i));
					closeHour.add(str + Integer.toString(i));
				}
				openMintues.add(str + Integer.toString(i));
				closeMintues.add(str + Integer.toString(i));
			}
			else {
				if(i < 24) {
					openHour.add(Integer.toString(i));
					closeHour.add(Integer.toString(i));
				}
				openMintues.add(Integer.toString(i));
				closeMintues.add(Integer.toString(i));
			}
		}
	}
	
	public void initalize() {
		setLayout(new BorderLayout());
		JPanel detailsPanel = new JPanel(new GridLayout(0,2));
		JPanel openPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		openPanel.add(openHour);
		openPanel.add(new JLabel("  :  "));
		openPanel.add(openMintues);
		
		closePanel.add(closeHour);
		closePanel.add(new JLabel("  :  "));
		closePanel.add(closeMintues);
		
		detailsPanel.add(new JLabel("Branch Number"));
		detailsPanel.add(branchNum);
		detailsPanel.add(new JLabel("Branch Location"));
		detailsPanel.add(location);
		detailsPanel.add(new JLabel("Open time"));
		detailsPanel.add(openPanel);
		detailsPanel.add(new JLabel("close time"));
		detailsPanel.add(closePanel);
		detailsPanel.add(error);
		
		buttons.add(addBtn);
		buttons.add(back);
		
		add(buttons,BorderLayout.SOUTH);
		add(detailsPanel,BorderLayout.CENTER);
		setSize(400,400);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Manager c = new Manager();
				c.setVisible(true);
			}
			
		});
		
		addBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				if(!Functions.checkOnlyNum(branchNum.getText()) || branchNum.getText().length() == 0) {
					Functions.showError(branchNum,"branch ID",error);
					return;
				}
				
				String loc = location.getText();
				if(loc.length() == 0 || !Functions.checkStr(loc)) {
					Functions.showError(location,"location",error);
					return;
				}
				
				//Read from branchFile
				ArrayList<Branch> branchList =(ArrayList<Branch>) Functions.readFromFile("Branches");
				
				int hourTimeOpen=Integer.parseInt(openHour.getSelectedItem());
				int hourTimeClose=Integer.parseInt(closeHour.getSelectedItem());
				int mintuesTimeOpen=Integer.parseInt(openMintues.getSelectedItem());
				int mintuesTimeClose=Integer.parseInt(closeMintues.getSelectedItem());
				
				if (hourTimeOpen > hourTimeClose) {
					showTimeError(closeHour,"close hour");
					return;
				}
				else if (hourTimeOpen == hourTimeClose && mintuesTimeOpen >= mintuesTimeClose) {
					showTimeError(closeMintues,"close mintues");
					return;
				}
				Time activityTime = new Time(openHour.getSelectedItem(),closeHour.getSelectedItem(),openMintues.getSelectedItem(),closeMintues.getSelectedItem());
				Branch newBranch = new Branch(Integer.parseInt(branchNum.getText()), location.getText(), activityTime);
				if(branchList.contains(newBranch)) {
					error.setText("This branch already exist");
					return;
				}
				branchList.add(newBranch);			
				
				//Write to a branchfile.
				Functions.writeToFile("Branches",branchList);
				
				setVisible(false);
				Manager mng = new Manager();
				mng.setVisible(true);			
			}
		});
	}
	
	public void showTimeError(Choice el,String str) {
		el.setBackground(Color.red);
		error.setText("Please fix your " + str +".");		
	}
	
	public void reset() {
		branchNum.setBackground(Color.white);
		location.setBackground(Color.white);
		openHour.setBackground(Color.white);
		closeHour.setBackground(Color.white);
		openMintues.setBackground(Color.white);
		closeMintues.setBackground(Color.white);
		error.setText("");
	}
	
	public void clearText() {
		openHour.removeAll();
		closeHour.removeAll();
		openMintues.removeAll();
		closeMintues.removeAll();
		initalizeChoices();
	}
}
