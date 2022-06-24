package View;
import Utils.*;
import javax.swing.*;

import Model.Branch;
import Model.Car;
import Model.Functions;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class represent the frame that sign up a new car to branch.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class AddCar extends JFrame{
	/**
	 * @carID Text field to insert branch location.
	 * @carModel Text field to insert car model.
	 * @subModel Text field to insert car sub model.
	 * @pricePerDay Text field to insert car price per day.
	 * @category This choice contains all category types.
	 * @productionYear This choice contains all the years (1923-2022) for production year.
	 * @selectBranch This choice contains all the branches we have.
	 * @automatic This check box represent if this car is auto or not.
	 * @addCar Button that user click when he want to add car.
	 * @back Button that user click when he want to go back.
	 * @error Label to tell the user what is wrong.
	 */
	private static final long serialVersionUID = 7L;
	JTextField carID;		
	JTextField carModel;	
	JTextField subModel;	
	JTextField pricePerDay;	
	Choice category;		
	Choice productionYear;	
	Choice selectBranch;	
	JCheckBox automatic;
	JButton addCar;			
	JButton back;		    
	JLabel error;	
	
	public AddCar() {
		carID = new JTextField();
		carModel = new JTextField();
		subModel = new JTextField();
		pricePerDay = new JTextField();
		category = new Choice();
		productionYear = new Choice();
		automatic = new JCheckBox("Automatic");
		addCar = new JButton("Add car");
		back = new JButton("Back");
		error = new JLabel ("");
		error.setForeground(Color.RED);
		selectBranch = new Choice();
		initalizeChoices();
		initalize();
	}
	
	public void initalizeChoices() {
		category = Functions.addCategory(category);	
		productionYear = Functions.productionYear(productionYear);	
		//Read from branchFile
		
		@SuppressWarnings("unchecked")
		ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");
		
		for(int i = 0; i < branchList.size(); i++) {
			selectBranch.add(""+branchList.get(i).getBranchID());
		}
		
	}
	
	public void initalize() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(0,2));
		p.add(new JLabel("Car ID: "));
		p.add(carID);
		p.add(new JLabel("Car model: "));
		p.add(carModel);
		p.add(new JLabel("Car sub model: "));
		p.add(subModel);
		p.add(new JLabel("Price for day: "));
		p.add(pricePerDay);
		p.add(new JLabel("Category: "));
		p.add(category);
		p.add(new JLabel("Production year: "));
		p.add(productionYear);
		p.add(new JLabel ("Branch ID: "));
		p.add(selectBranch);
		p.add(automatic);
		add(p,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttons.add(addCar);
		buttons.add(back);
		add(buttons,BorderLayout.SOUTH);
		
		add(error,BorderLayout.CENTER);
		setSize(400,400);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Manager c = new Manager();
				c.setVisible(true);
			}
			
		});
		
		addCar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				
				if(!(Functions.checkOnlyNum(carID.getText())) || carID.getText().length() == 0) {
					Functions.showError(carID,"car ID",error);
					return;	
				}
				
				if (!Functions.checkModel(carModel.getText()) || carModel.getText().length() == 0) {
					Functions.showError(carModel,"car model",error);
					return;	
				}
				
				if (!Functions.checkModel(subModel.getText()) || subModel.getText().length() == 0) {
					Functions.showError(subModel,"sub model",error);
					return;		
				}
				
				if(pricePerDay.getText().length() == 0 || Integer.parseInt(pricePerDay.getText())<1)
				{
					Functions.showError(pricePerDay,"price for day",error);
					return;
				}
				
				//Get the file
				@SuppressWarnings("unchecked")
				ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");

				int branchID = Integer.parseInt(selectBranch.getSelectedItem());
				Branch currentBranch = new Branch(branchID,"",null);
				int priceEachDay = Integer.parseInt(pricePerDay.getText());
				int carYear = Integer.parseInt(productionYear.getSelectedItem());			
				Car carToAdd = new Car(carID.getText(),carYear,carModel.getText(),subModel.getText(),Enum.valueOf(Category.class,category.getSelectedItem()),automatic.isSelected(),priceEachDay,Integer.parseInt(selectBranch.getSelectedItem()));
				
				//ArrayList<Car> 
				
				for(int i = 0; i < branchList.size(); i++) {
					if((branchList.get(i).getCarArr().contains(carToAdd))) {
						//Car already exist in the branch
						clearText();
						error.setText("Car already exist");
						return;
					}
				}

				branchList.get(branchList.indexOf(currentBranch)).getCarArr().add(carToAdd);
			
				
				//Write to the file.
				Functions.writeToFile("Branches", branchList);	
				//Returning to the manager Homepage.
				setVisible(false);
				Manager managerPage = new Manager();
				managerPage.setVisible(true);	
				
			}
		
			//Clear and validation methods--------------------------------------------------------------------------
			public void clearText() {
				for(Component c : p.getComponents()) {
					if(c instanceof JTextField) {
						JTextField f = (JTextField)c;
						f.setText("");
					}
				}
			}
			
			public void reset() {
				carID.setBackground(Color.white);
				carModel.setBackground(Color.white);
				subModel.setBackground(Color.white);
				pricePerDay.setBackground(Color.white);
				category.setBackground(Color.white);
				productionYear.setBackground(Color.white);
				selectBranch.setBackground(Color.white);
	
			}
			
		});
		
	}
	
	

}
