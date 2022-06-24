package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import Utils.Category;

/**
 * In this class we are going to represent the car object.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Car implements Serializable{
	private static final long serialVersionUID = 2L;
	private String carID;
	private int year;
	private String model;
	private String tat_Model;
	private Category category;
	private boolean automatic;
	private int pricePerDay;
	private ArrayList<RangeDate> rentDates;
	private int branchID;
	
	/**
	 * Initializes and constructs a Car object
	 * @param carID represents the car ID
	 * @param year represents the car's year.
	 * @param model represents the car's model
	 * @param tat_Model represents car's the sub model.
	 * @param category represents the car's category (ENUM)
	 * @param automatic represents whether the car is automatic true/false
	 * @param pricePerDay represents the rent car price for each day.
	 * @param branchID represents the branchID which contains this specific car.
	 */
	
	public Car(String carID, int year, String model, String tat_Model, Category category, boolean automatic,
			int pricePerDay,int branchID) {
		super();
		this.carID = carID;
		this.year = year;
		this.model = model;
		this.tat_Model = tat_Model;
		this.category = category;
		this.automatic = automatic;
		this.pricePerDay = pricePerDay;
		this.branchID = branchID;
		rentDates = new ArrayList<RangeDate>();
	}
	
	
	//Getters & Setters--------------------------------------------------------------------

	/**
	 * @return returns the carID
	 */
	public String getCarID() {
		return carID;
	}
	/**
	 * @param setting the carID.
	 */
	public void setCarID(String carID) {
		this.carID = carID;
	}
	/**
	 * @return returns the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param setting the car's year.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return returns the car model.
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param setting the car's model.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return returns the tat_Model
	 */
	public String getTat_Model() {
		return tat_Model;
	}
	/**
	 * @param setting tat_Model
	 */
	public void setTat_Model(String tat_Model) {
		this.tat_Model = tat_Model;
	}
	/**
	 * @return returns the ENUM category field that selected.
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param setting category the category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return returns true or false whether the car is automatic.
	 */
	public boolean isAutomatic() {
		return automatic;
	}
	/**
	 * @param setting automatic to true or false.
	 */
	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
	/**
	 * @return returns the pricePerDay for the car renting
	 */
	public int getPricePerDay() {
		return pricePerDay;
	}
	/**
	 * @param setting pricePerDay for the car
	 */
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	/**
	 * @return returns the rentDates that the car got rented.
	 */
	public ArrayList<RangeDate> getRentDates() {
		return rentDates;
	}

	/**
	 * @param setting the rentDates.
	 */
	public void setRentDates(ArrayList<RangeDate> rentDates) {
		this.rentDates = rentDates;
	}

	/**
	 * @return returns the branchID
	 */
	public int getBranchID() {
		return branchID;
	}

	/**
	 * @param setting the car's branchID
	 */
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	
	//ToString and equals + hashcode----------------------------------------------------------------------------
	/**
	 * @return returns a string that contains all the relevant information regarding this object.
	 */
	@Override
	public String toString() {
		return "carID=" + carID + ", year=" + year + ", model=" + model + ", tat_Model=" + tat_Model
				+ ", category=" + category + ", automatic=" + automatic + ", pricePerDay=" + pricePerDay;
	}
	@Override
	public int hashCode() {
		return Objects.hash(automatic, carID, category, model, pricePerDay, tat_Model, year);
	}
	/**
	 * @return returns true or false the cars are equal ( comparing by the car ID).
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Car other = (Car) obj;
		return  Objects.equals(carID, other.carID);
	}
}