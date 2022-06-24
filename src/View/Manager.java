package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Functions;
/**
 * This class represent the opening frame for manager and shows him his options.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Manager extends JFrame{
	/**
	 * @addBranch This button sending the user to other frame that show him document for sign up a branch to the system.
	 * @addCar This button sending the user to other frame that show him document for sign up a car to branch.
	 * @logOut This button returns the user to the main frame.
	 */
	private JButton addBranch;		
	private JButton addCar;			
	private JButton logOut;         
	private static final long serialVersionUID = 11L;
		
	public Manager() {
		super("Manager");
		addBranch = new JButton("Add Branch");
		addCar = new JButton("Add Car");
		logOut = new JButton("Log out");
		
		initalize();
	}
	
	public void initalize() {
		
		/////// set image as background
		Functions.setImageBackground(this,"manager");
		/////////	
		
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(Functions.setFont(new JLabel("Hello Manager!"), 30));
		p1.add(Functions.setFont(new JLabel(" Please choose action"), 30));
		p1.setBackground(Color.darkGray.darker());
		add(p1,BorderLayout.NORTH);
		
		JPanel p = new JPanel(new FlowLayout());
		p.add(addBranch);
		p.add(addCar);
		p.add(logOut);
		p.setBackground(Color.darkGray.darker());
		add(p,BorderLayout.SOUTH);
		pack();
		
		addBranch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBranch add_Branch = new AddBranch();
				add_Branch.setVisible(true);
				setVisible(false);		
			}		
		});

		addCar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddCar add_Car = new AddCar();
				add_Car.setVisible(true);
				setVisible(false);		
			}
		});
		
		logOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame main = new MainFrame();
				setVisible(false);
				main.setVisible(true);
			}
		});	 
	}
}
