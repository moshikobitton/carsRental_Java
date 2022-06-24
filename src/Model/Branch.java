package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
/**
 * In this class we are going to represent the branch which contains ID,location,Activity time and car list.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Branch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int branchID;
	private String location;
	private Time branchActivityTime;
	private ArrayList<Car> carArr;
	
	/**
	 * Initalizes and constructs a Branch object.
	 * @param branchID represents the branch number.
	 * @param location represents the branch's location
	 * @param branchActivityTime represents the activity time.
	 */
	public Branch(int branchID, String location, Time branchActivityTime) {
		super();
		this.branchID = branchID;
		this.location = location;
		this.branchActivityTime = branchActivityTime;
		carArr = new ArrayList<>();
	}

	
	//Getters & Setters----------------------------------------------------------------
	/**
	 * @return returns the branch ID.
	 */
	public int getBranchID() {
		return branchID;
	}
	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	/**
	 * @return returns the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param setting the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return returns the branchActivityTime
	 */
	public Time getBranchActivityTime() {
		return branchActivityTime;
	}
	/**
	 * @param setting the branchActivityTime
	 */
	public void setBranchActivityTime(Time branchActivityTime) {
		this.branchActivityTime = branchActivityTime;
	}
	
	/**
	 * @return returns the car list for this branch.
	 */
	public ArrayList<Car> getCarArr() {
		return carArr;
	}

	/**
	 * @param setting the carArr
	 */
	public void setCarArr(ArrayList<Car> carArr) {
		this.carArr = carArr;
	}
	
	//Equals hashcode ToString----------------------------------------------
	/**
	 *@return returns the full string which provides all field details.
	 */
	@Override
	public String toString() {
		return "Branch [branchID=" + branchID + ", location=" + location + ", branchActivityTime=" + branchActivityTime
				+ "]";
	}
	
	/**
	 * @return whether the branches are equal or not, comparing by the branchID .
	 */
	@Override
	public int hashCode() {
		return Objects.hash(branchID);
	}
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
		Branch other = (Branch) obj;
		return branchID == other.branchID;
	}
}