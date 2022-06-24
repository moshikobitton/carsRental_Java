package View;
import javax.swing.*;

import Model.Functions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represent the opening frame for our system.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class MainFrame  extends JFrame{
	/**
	 * @signUp This button sending the user to other frame that show him document for sign up.
	 * @signIn This button sending the user to other frame that ask him details for sign in.
	 */
	private static final long serialVersionUID = 10L;
	private JButton signUp;			
	private JButton signIn;			
	
	 public MainFrame() {
		 super("Rent Company");
		 signUp = new JButton("Sign Up");
		 signIn = new JButton("Sign In");
		 
		 initalize();
	 }
	 
	 public void initalize() {

		 signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp sign = new SignUp();
				setVisible(false);
				sign.setVisible(true);
			}
		 });
		 
		 signIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SignIn signIn = new SignIn();
				setVisible(false);
				signIn.setVisible(true);
			}
		 });
		 
		 /////// set image as background
		 Functions.setImageBackground(this, "main");

		 setLayout(new BorderLayout());
		 
		 JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		 p1.setBackground(Color.darkGray.darker());
		 p1.add(signUp);
		 p1.add(signIn);		 
		 add(p1, BorderLayout.SOUTH);
		 
		 JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
		 p2.setBackground(Color.darkGray.darker());
		 p2.add(Functions.setFont(new JLabel("Welcome to our rent company!"), 50));
		 add(p2, BorderLayout.NORTH);
		 
		 setSize(900,820);
	 } 
}
