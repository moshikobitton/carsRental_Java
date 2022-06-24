package Model;
import java.io.Serializable;
import java.util.Objects;
/**
 * In this class we are going to represent a time activity that a branch is open.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Time implements Serializable{
	private static final long serialVersionUID = 4L;
	private String fromHour;
	private String toHour;
	private String fromMintues;
	private String toMintues;
	
	/**
	 * constructs and initalizes a time object which represents the branch's activity time.
	 * @param fromHour represents the hour the branch opens.
	 * @param toHour represents the hour the branch closes.
	 * @param fromMintues represents the mintues the branch opens.
	 * @param toMintues represents the mintues the branch closes.
	 */
	public Time(String fromHour,String toHour, String fromMintues, String toMintues) {
		super();
		this.fromHour = fromHour;
		this.toHour = toHour;
		this.fromMintues = fromMintues;
		this.toMintues = toMintues;
	}
	
	//Getters & Setters------------------------------------------------------------------
	
	/**
	 * @return returns the hour opens
	 */
	public String getFromHour() {
		return fromHour;
	}
	/**
	 * @param setting the open hour
	 */
	public void setFromHour(String fromHour) {
		this.fromHour = fromHour;
	}
	/**
	 * @return returns the close hour
	 */
	public String getToHour() {
		return toHour;
	}
	/**
	 * @param setting the close hour
	 */
	public void setToHour(String toHour) {
		this.toHour = toHour;
	}
	/**
	 * @return returns the open mintuess
	 */
	public String getFromMintues() {
		return fromMintues;
	}
	/**
	 * @param setting the open mintues time
	 */
	public void setFromMintues(String fromMintues) {
		this.fromMintues = fromMintues;
	}
	/**
	 * @return  returns the close mintuess
	 */
	public String getToMintues() {
		return toMintues;
	}
	/**
	 * @param setting the close mintues time
	 */
	public void setToMintues(String toMintues) {
		this.toMintues = toMintues;
	}

	
	//Equals + ToString + HashCode -----------------------------------------------------------
	/**
	 * In this method we are going to use hashcode method.
	 * @return returns int.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fromHour, fromMintues, toHour, toMintues);
	}
	
	/**
	 * In this method we are going to compare between Time objects by their open close hour and open close mintues.
	 * @return returns true if time are equals, false otherwise.
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
		Time other = (Time) obj;
		return fromHour == other.fromHour && fromMintues == other.fromMintues && toHour == other.toHour
				&& toMintues == other.toMintues;
	}
	
	/**
	 * @return returns a string that contains all the relevant information regarding this object.
	 */
	@Override
	public String toString() {
		return fromHour + ":" + fromMintues + " - " + toHour + ":"+ toMintues;
	}
}