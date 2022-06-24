package Model;
import java.io.Serializable;
import java.util.Objects;
/**
 * In this class we are going to represent the user object, by email, password and whether he is manager.
 * @author Tamir
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 5L;
	private String email;
	private String password;
	private boolean manager;
	/**
	 * constructs and initalizes a user object.
	 * @param email represents the the User's email.
	 * @param password represents the user's password.
	 * @param manager represents if user is a manager.
	 */
	public User(String email, String password, boolean manager) {
		super();
		this.email = email;
		this.password = password;
		this.manager = manager;
	}
	
	//Getters&Setters------------------------------------------------------------
	/**
	 * @return returns the user's email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param sets the email 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return returns the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param sets the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return returns if he is manager or not
	 */
	public boolean isManager() {
		return manager;
	}
	/**
	 * @param setting if he is manager or not
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	//Hash,equals,ToString------------------------------------------------------------------
	/**
	 * In this method we are going to use hashcode method.
	 * @return returns int.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, manager, password);
	}
	
	/**
	 * In this method we are going to compare between User objects by their email and password.
	 * @return returns true if users are same, false otherwise.
	 */
	public boolean equalsMailAndPass(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(password, other.password);
	}
	
	/**
	 * In this method we are going to compare between User objects by their email
	 * @return returns users emails are equals, false otherwise.
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
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

	/**
	 * @return returns a string that contains all the relevant information regarding this object.
	 */
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", manager=" + manager + "]";
	}
}