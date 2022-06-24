package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Model.Functions;
import Model.User;

/**
 * In this class we are going to present the sign in format.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class SignIn extends JFrame{

	/**
	 * @email represents the user email.
	 * @loginPassword represents the user's login password.
	 * @login represents the login button.
	 * @back represents the return button
	 * @error represents the error label.
	 */
	private static final long serialVersionUID = 14L;
	private JTextField email;
	private JPasswordField loginPassword;
	private JButton login;
	private JLabel error;
	private JButton back;
	
	public SignIn() {
		super("Sign in");
		email = new JTextField();
		loginPassword = new JPasswordField();
		login = new JButton ("Login");
		error = new JLabel("");
		error.setForeground(Color.red);	
		error.setFont(new Font("Calibri", Font.BOLD, 20));
		back = new JButton("Back");
		initalize();
	}
	
	public void initalize() {
		Functions.setImageBackground(this, "signIn");
		
		setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(0,2));
		p.add(Functions.setFont(new JLabel("   Email"), 15));
		p.add(email);
		p.add(Functions.setFont(new JLabel("   Password"), 15));
		p.add(loginPassword);
		p.setBackground(Color.darkGray.darker());
		add(p,BorderLayout.NORTH);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(login);
		p1.add(back);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(error);
		
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p1,BorderLayout.SOUTH);
		p3.add(p2,BorderLayout.NORTH);
		
		p1.setBackground(Color.darkGray.darker());
		p2.setBackground(Color.darkGray.darker());
		 
		add(p3,BorderLayout.SOUTH);
		pack();
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFrame mainF = new MainFrame();
				mainF.setVisible(true);
			}
		});
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				ArrayList<User> userList = (ArrayList<User>) Functions.readFromFile("Users");
				String passUser = new String(loginPassword.getPassword());
				User toSignIn = new User(email.getText(),passUser,false);		
				
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).equalsMailAndPass(toSignIn)) {
						if(userList.get(i).isManager()) {
							Manager mng = new Manager ();
							mng.setVisible(true);	
						}
						else {
							Client customer = new Client();
							customer.setVisible(true);
						}
						
						setVisible(false);
						return;
					}
				}
				clearControls();
				error.setText("Unvalid email or password, please try again");
			}
		});
	}
	
	public void clearControls() {
		email.setText("");
		loginPassword.setText("");
		error.setText("");
	}
}
