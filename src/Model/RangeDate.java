package Model;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * In this class we are going to present a date range object that will be in the car date list.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class RangeDate implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private Date fromDate;
	private Date toDate;
	
	/**
	 * Constructs and initalizes a RangeDate object.
	 * @param fromDate represents the starting date to rent the car.
	 * @param toDate represents the final date to rent the car.
	 */
	public RangeDate(Date fromDate, Date toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	//Getters & Setters----------------------------------------------------------------
	
	/**
	 * @return returns the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param setting the fromDate 
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return returns the final Date
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param setting toDate
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	//Methods--------------------------------------------------------------------------
	/**
	 * In this method we are checking whether dates are intercepted.
	 * @param checkFrom represents the date a client wants to start renting.
	 * @param checkTo represents the date a client want to finish the rent.
	 * @return returns true if they are not intercepted, false otherwise.
	 */
	public boolean isAvailable(Date checkFrom, Date checkTo) {
		if (checkFrom.compareTo(fromDate) >= 0 && toDate.compareTo(checkFrom) >= 0)
			return false;
		else if(fromDate.compareTo(checkFrom) >= 0 && checkTo.compareTo(fromDate) >= 0)
			return false;
		return true;
	}
	
	/**
	 * In this method we are going to get the total days between the final to start date.
	 * @return
	 */
	public int getTotalDays() {
		long diff2 = fromDate.getTime() - toDate.getTime();
		int days =(int) Math.abs(diff2 / 1000 / 60 / 60 / 24 );
		return (days + 1);
	}
	
	//Equals hashcode ToString----------------------------------------------
	/**
	 * In this method we are going use the hashcode.
	 * @return returns int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fromDate, toDate);
	}
	/**
	 * In this method we are going to compare between rangeDate object by their fromDate and toDate.
	 * @return returns true if the objects are equal, false otherwise.
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
		RangeDate other = (RangeDate) obj;
		return Objects.equals(fromDate, other.fromDate) && Objects.equals(toDate, other.toDate);
	}
	/**
	 * @return returns a string that contains all the relevant information regarding this object.
	 */
	@Override
	public String toString() {
		return "RangeDate [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
}