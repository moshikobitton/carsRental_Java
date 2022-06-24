package View;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import Model.Branch;
import Model.Car;
import Model.Functions;
import Model.RangeDate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * In this class we are going to represent the table that is shown, that contains car relevant information.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class ShowCar extends JFrame{
	/**
	 * @select represents select button on which car the user wants.
	 * @price represents the price label which presented when user picking a car in table.
	 * @rngDate represents the date range the car is occupied.
	 * @columns represents the table columns.
	 * @data represents the data in the table - carID, price, model, etc...
	 * @scrollPane represents the scroll down and up option in the table
	 * @table represents the table
	 */
	 
	private static final long serialVersionUID = 13L;
	private JButton select;
	private JLabel price;
	private RangeDate rngDate;
	private String[] columns;
	private Object [][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton back;
	
	public ShowCar(ArrayList<Car> carList,RangeDate rngDate) {
		super("Car selection");
		select = new JButton("Select");
		this.rngDate = rngDate;
		price = new JLabel("");
		columns = new String[8];
		back = new JButton("Back");
		init(carList);
		
	}
	
	
	public void init(ArrayList<Car> carList) {	
		data = new Object[carList.size()][8];
		
		for(int i = 0; i < carList.size(); i++){
			data[i][0] = carList.get(i).getCarID();
			data[i][1] = "" + carList.get(i).getYear();
			data[i][2] = carList.get(i).getModel();
			data[i][3] = carList.get(i).getTat_Model();
			data[i][4] = carList.get(i).getCategory();
			data[i][5] = carList.get(i).isAutomatic();
			data[i][6] = "" + carList.get(i).getPricePerDay();
			data[i][7] = "" + carList.get(i).getBranchID();
									
		}
		columns[0] = "Car ID";
		columns[1] = "Production year";
		columns[2] = "Model";
		columns[3] = "Sub model";
		columns[4] = "Category";
		columns[5] = "Automatic";
		columns[6] = "Price per day";
		columns[7] = "Branch ID";
                                  
        table = new JTable(data,columns);
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        
        JPanel scrolling = new JPanel(new FlowLayout());
        scrolling.add(scrollPane,BorderLayout.NORTH);
		
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        buttons.add(select);
        buttons.add(back);
        
        JPanel p = new JPanel(new GridLayout(0,1));
        p.add(price);
        p.add(buttons);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(p);
       
        add(scrolling, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        table.setDefaultEditor(Object.class, null);
		pack();
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client c = new Client();
				c.setVisible(true);
			}
			
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
		           // do some actions here, for example
                // print first column value from selected row
               String carID = table.getValueAt(table.getSelectedRow(), 0).toString();
               int price1 = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString());
               price.setText("You chose car id - " + carID +", and the final price is - " + price1*(rngDate.getTotalDays()));
			}
        });
		
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(price.getText().length() == 0)
					return;
				String carID = table.getValueAt(table.getSelectedRow(), 0).toString();
				
				Car car = null;
				for(int i = 0; i < carList.size(); i++) {
					if(carList.get(i).getCarID().equals(carID)) {
						car = carList.get(i);
						break;
					}
				}
				
				if (car != null) {
					@SuppressWarnings("unchecked")
					ArrayList<Branch> branchList = (ArrayList<Branch>) Functions.readFromFile("Branches");
					for(int i = 0; i < branchList.size(); i++) {
						if(branchList.get(i).getBranchID() == (car.getBranchID())) {
							int index = branchList.get(i).getCarArr().indexOf(car);
							branchList.get(i).getCarArr().get(index).getRentDates().add(rngDate);
							break;
						}	
					}
					Functions.writeToFile("Branches", branchList);
					setVisible(false);
					Client c = new Client();
					c.setVisible(true);
				}	
			}
		});	
	}
}