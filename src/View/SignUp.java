package View;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import Model.*;
import Utils.Constants;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * In this class we are going to represent the sign up format. 
 * @author Tamir
 *
 */
public class SignUp extends JFrame{
	
	/**
	 * 	
	 * @firstName represents the user first name.
	 * @lastName represents the user last name.
	 * @iD represents the user ID.
	 * @email represents the user email.
	 * @licenseYear represents the user liceense year.
	 * @date represents the use date of birth.
	 * @userPass represents the user password.
	 * @validatePassword represents the user's password validation.
	 * @sign represents the sign button.
	 * @back represents the return button
	 * @error represents the error label.
	 */
	
	private static final long serialVersionUID = 15L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField iD;
	private JTextField email;
	private Choice licenseYear;
	private JDateChooser date;
	private JPasswordField userPass;
	private JPasswordField validatePassword;
	private JButton sign;
	private JButton back;
	private JLabel error;
	
	public SignUp() {
		super("Sign Up");
		firstName = new JTextField();
		lastName = new JTextField();
		iD = new JTextField();
		email = new JTextField();
		licenseYear = new Choice();
		userPass = new JPasswordField();
		validatePassword = new JPasswordField();
		sign = new JButton("Sign Up");
		error = new JLabel("");
		error.setForeground(Color.red);
		date = new JDateChooser();
		back = new JButton("Back");
		
		initChoice();
		initalize();
	}
	
	public void initChoice() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = 0; i < Constants.YEAR_RANGE_LICENSE; i++) {
			licenseYear.add(Integer.toString(year-i));
		}
	}
	
	private void initalize() {
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel(new GridLayout(0,2));
		p.add(new JLabel (" First Name"));
		p.add(firstName);
		p.add(new JLabel (" Last Name"));
		p.add(lastName);
		p.add(new JLabel(" Your ID"));
		p.add(iD);
		p.add(new JLabel(" Your Email"));
		p.add(email);
		p.add(new JLabel(" Your license year"));
		p.add(licenseYear);
		p.add(new JLabel(" Your Password"));
		p.add(userPass);
		p.add(new JLabel(" Confirm Password   "));
		p.add(validatePassword);
		p.add(new JLabel(" Birth Date"));
		p.add(date);
		p.add(error);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(sign);
		p1.add(back);
		
		add(p, BorderLayout.CENTER);
		add(p1, BorderLayout.SOUTH);
		setSize(400,400);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFrame mainF = new MainFrame();
				mainF.setVisible(true);
			}
			
		});
		
		sign.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e){
				reset();
				
				String firstname = firstName.getText();
				if(!Functions.checkStr(firstname) || firstname.length() == 0){
					Functions.showError(firstName, "first name", error);
					return;
				}
				
				String lastname = lastName.getText();
				if(!Functions.checkStr(lastname) || lastname.length() == 0){
					Functions.showError(lastName, "last name", error);
					return;
				}
				
				if (!Functions.checkOnlyNum(iD.getText()) || iD.getText().length() == 0) {
					Functions.showError(iD, "id", error);
					return;
				}
				
				if (!checkEmail(email.getText())) {
					Functions.showError(email, "email",error);
					return;
				}
				
				if(date.getDate() == null) {
					error.setText("Please insert  birth date");
					return;
				}
				else {
					int year = Calendar.getInstance().get(Calendar.YEAR);
					int birthDateYear = date.getCalendar().get(Calendar.YEAR);
					int licenseYear1 = Integer.parseInt(licenseYear.getSelectedItem());
					if ((year - birthDateYear < Constants.MIN_AGE) || (licenseYear1 - birthDateYear < Constants.MIN_AGE))  {
						error.setText("Plase insert valid dates");
						return;
					}
				}
				
				String str1 = new String(userPass.getPassword());
				String str2 = new String(validatePassword.getPassword());
				if (!(str1.equals(str2)) || str1.length() == 0 || str2.length() == 0)
				{	
					Functions.showError(validatePassword,"",error);
					Functions.showError(userPass,"password",error);
					return;
				}
				//Read from the file.
				ArrayList<User> userList =(ArrayList<User>) Functions.readFromFile("Users");
				
				// Admin - email - admin@ruppin.com
				//         password - admin.
				
				String userPassToAdd = new String(userPass.getPassword());
				User toAdd = new User(email.getText(),userPassToAdd,false);
				if (userList.contains(toAdd))
				{
					clearControls();
					error.setText("User already exist");
					return;
					
				}
					
				//Override the previous file
				userList.add(toAdd);	
				Functions.writeToFile("Users", userList);				
				setVisible(false);
				
				MainFrame homePage = new MainFrame();
				homePage.setVisible(true);
			}
			
		});
	}
	
	public boolean checkEmail(String toCheck) {
		String[] splitted = toCheck.split("@");
		if(!toCheck.contains(".") || splitted.length != 2) {
			return false;
		}
	
		//There is no char before the @.
		if(splitted[0].length() == 0)
			return false;	
		
		if(!splitted[1].contains("."))
			return false;
		//tamir@uzan.com
		String[] checkBeforePoint = splitted[1].split("\\.");
		if (checkBeforePoint[0].length() == 0)
			return false;
		
		if (toCheck.charAt(toCheck.length() - 1) == '.')
			return false;
		
		return true;
	}
	
	public boolean checkYear(String checkYear) {
		if (!Functions.checkOnlyNum(checkYear)) 
			return false;
		
		if (checkYear.charAt(0) == '0') 
			return false;
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (Integer.parseInt(checkYear) > year)
			return false;
		
		//checkYear > birthdate year
		
		return true;
		
	}
	
	public void clearControls() {
		firstName.setText("");
		lastName.setText("");
		iD.setText("");
		email.setText("");
		userPass.setText("");
		validatePassword.setText("");
		//JClearFrame.clearCommand(this, serialVersionUID);
		
	}
	
	public void reset() {
		firstName.setBackground(Color.white);
		lastName.setBackground(Color.white);
		iD.setBackground(Color.white);
		email.setBackground(Color.white);
		licenseYear.setBackground(Color.white);
		userPass.setBackground(Color.white);
		validatePassword.setBackground(Color.white);
		error.setText("");
	}
	
	
	
	
}
