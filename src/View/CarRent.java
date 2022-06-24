package View;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.*;
import Model.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;
/**
 * This class represent the frame that client need to insert details to search car for rent.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class CarRent extends JFrame{
	/**
	 * @carModel Text field to insert car model.
	 * @searchByBranchNum This choice contains all the branches we have.
	 * @category This choice contains all category types.
	 * @productionYear This choice contains all the years (1923-2022) for production year.
	 * @pricePerDay Text field to insert car price per day.
	 * @automatic This check box represent if this car is auto or not.
	 * @search Button that user click when he want see the results.
	 * @back Button that user click when he want to go back.
	 * @error Label to tell the user what is wrong.
	 * @fromDate Calendar for choose start day.  
	 * @toDate Calendar for choose end day. 
	 */
	private static final long serialVersionUID = 8L;
	private JTextField carModel;
	private Choice searchByBranchNum;
	private Choice category;		 
	private Choice productionYear;	 
	private Choice pricePerDay;		
	private JCheckBox automatic;	 
	private JButton search;			 
	private JButton back;		    
	private JLabel error;			 
	private JDateChooser fromDate;	 
	private JDateChooser toDate;	

	
	public CarRent() {
		searchByBranchNum = new Choice();
		carModel = new JTextField();
		category = new Choice();
		productionYear = new Choice();
		pricePerDay = new Choice();
		automatic = new JCheckBox("Automatic");
		search = new JButton("Search");
		fromDate = new JDateChooser();
		toDate = new JDateChooser();
		error = new JLabel();
		error.setForeground(Color.RED);
		back = new JButton("Back");
		
		initalizeChoices();
		initalize();
	}
	
	public void initalizeChoices() {
		@SuppressWarnings("unchecked")
		ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");
		searchByBranchNum.add("");
		for(int i = 0; i < branchList.size(); i++) {
			searchByBranchNum.add(""+branchList.get(i).getBranchID());
		}
		
		//Method to add categories.
		category.add("");
		category = Functions.addCategory(category);
		productionYear.add("");
		productionYear = Functions.productionYear(productionYear);
		
		//add pricePerDay
		pricePerDay.add("");
		pricePerDay.add("0 - 100");
		pricePerDay.add("101 - 200");
		pricePerDay.add("201 - 300");
		pricePerDay.add("301+");
	}
	
	public void initalize() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(0,2));
		p.add(new JLabel("Branch"));
		p.add(searchByBranchNum);
		p.add(new JLabel("Price range"));
		p.add(pricePerDay);
		p.add(new JLabel("Vehicle category"));
		p.add(category);
		p.add(new JLabel("Production year"));
		p.add(productionYear);
		p.add(new JLabel("Model"));
		p.add(carModel);
		p.add(new JLabel("Rent from date"));
		p.add(fromDate);
		p.add(new JLabel("to date"));
		p.add(toDate);
		p.add(automatic);
		p.add(new JLabel(""));
		p.add(error);
		add(p,BorderLayout.NORTH);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(search);
		p2.add(back);
		add(p2,BorderLayout.SOUTH);
		setSize(400,400);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client c = new Client();
				c.setVisible(true);
			}
		});
		
		search.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");
				
				ArrayList<Car> carList = new ArrayList<>();
				ArrayList<Car> tempList = new ArrayList<>();
				
				if(fromDate.getDate() == null || toDate.getDate() == null) {
					error.setText("You must choose date");
					return;
				}
				
				if (searchByBranchNum.getSelectedItem() != "") {
					Branch foundBranch = new Branch(Integer.parseInt(searchByBranchNum.getSelectedItem()), "",null);
					for (int i = 0; i < branchList.size(); i++) {
						if (foundBranch.equals(branchList.get(i))){
							carList = branchList.get(i).getCarArr();
							break;
						}
					}
				}
				else {
					for (int i = 0; i < branchList.size(); i++) {
						for (int j = 0; j < branchList.get(i).getCarArr().size(); j++) {
							carList.add(branchList.get(i).getCarArr().get(j));
						}
					}
				}
				
				if (productionYear.getSelectedItem() != "") {
					tempList = new ArrayList<>();
					for (int i = 0; i < carList.size(); i++) {
						if (("" + carList.get(i).getYear()).equals(productionYear.getSelectedItem())) {
							tempList.add(carList.get(i));
						}
					}
					carList = (ArrayList<Car>) tempList.clone();
				}
				
				if (category.getSelectedItem() != "") {
					tempList = new ArrayList<>();
					for (int i = 0; i < carList.size(); i++) {
						if (("" + carList.get(i).getCategory()).equals(category.getSelectedItem())) {
							tempList.add(carList.get(i));
						}
					}
					carList = (ArrayList<Car>) tempList.clone();
				}
				
				if (pricePerDay.getSelectedItem() != "") {
					tempList = new ArrayList<>();
					String[] range = null;
					int price1 = 301;
					int price2 = 0;
					
					if (pricePerDay.getSelectedItem().contains("-")) {
						range = pricePerDay.getSelectedItem().split(" - ");
						price1 = Integer.parseInt(range[0]);
						price2 = Integer.parseInt(range[1]);
						
						for (int i = 0; i < carList.size(); i++) {
							if (carList.get(i).getPricePerDay() >= price1 && carList.get(i).getPricePerDay() <= price2) {
								tempList.add(carList.get(i));
							}
						}
					}

					else {
						for (int i = 0; i < carList.size(); i++) {
							if (carList.get(i).getPricePerDay() >= price1) {
								tempList.add(carList.get(i));
							}
						}
					}
					carList = (ArrayList<Car>) tempList.clone();
				}
				
				if (category.getSelectedItem() != "") {
					tempList = new ArrayList<>();
					for (int i = 0; i < carList.size(); i++) {
						if (("" + carList.get(i).getCategory()).equals(category.getSelectedItem())) {
							tempList.add(carList.get(i));
						}
					}
					carList = (ArrayList<Car>) tempList.clone();
				}
				//By automatic
				tempList = new ArrayList<>();
				for(int i = 0; i < carList.size(); i++) {
					if(carList.get(i).isAutomatic() == automatic.isSelected()) {
						tempList.add(carList.get(i));
					}
				}
				carList = (ArrayList<Car>) tempList.clone();
				
				if (carModel.getText().length() != 0) {
					tempList = new ArrayList<>();
					for(int i = 0; i < carList.size(); i++) {
						if(carList.get(i).getModel().equals(carModel.getText())) {
							tempList.add(carList.get(i));
						}
					}
					carList = (ArrayList<Car>) tempList.clone();
				}
				Date fromDate1 = null;
				Date toDate1 = null;
				fromDate1 = fromDate.getDate();
				toDate1 = toDate.getDate();

				Date today = new Date();
				try {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					today = formatter.parse(formatter.format(new Date()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				if (today.compareTo(fromDate1) == 1) {
					error.setText("Please enter future range date");
					return;
				}
					
				if(fromDate1.compareTo(toDate1) == 1) {
					error.setText("Please enter valid range date");
					return;
				}
				
				
				tempList = new ArrayList<>();
				Car currentCar = null;
				RangeDate currentRange = null;
				boolean intercept = false;
 				for(int i = 0; i < carList.size(); i++) {
					currentCar = carList.get(i);
					for(int j = 0; j < currentCar.getRentDates().size(); j++) {
						currentRange = currentCar.getRentDates().get(j);
						if (!(currentRange.isAvailable(fromDate1, toDate1))) {
							intercept = true;
						}
					}
					if(!intercept)
						tempList.add(currentCar);
					intercept = false;
				}
 				RangeDate rngDate = new RangeDate(fromDate.getDate(),toDate.getDate());
 
 				carList = (ArrayList<Car>) tempList.clone();
 				if(carList.size() != 0) {
					setVisible(false);
					ShowCar show = new ShowCar(carList,rngDate);
					show.setVisible(true);
					return;
 				}
 				
 				clearText();
 				error.setText("No such car exist");
 				
			}
			
		});
	}
	
	public void clearText() {
		  searchByBranchNum.select(0);
		  carModel.setText("");
		  category.select(0);
		  productionYear.select(0);
		  pricePerDay.select(0);
		  error.setText("");
		  fromDate.setCalendar(null);
		  toDate.setCalendar(null);
		  automatic.setSelected(false);
	}
	
	
}
