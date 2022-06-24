package Model;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.*;
import Utils.Category;
import Utils.Constants;

/**
 * In this class we are going to use methods to make reuse of the code.
 * basically its a class that contains a static methods that gets reused many time.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Functions{
	
	/**
	 * In this method we are reading from file.
	 * @param fileName represents the file name we want to read from.
	 * @return returns the array list the contains all relevant data.
	 */
	public static ArrayList <?> readFromFile(String fileName) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		ArrayList <?> arrList = new ArrayList<>();
		try {
			fis = new FileInputStream(fileName+".ser");
			in = new ObjectInputStream(fis);
			arrList = (ArrayList<?>) in.readObject();
			in.close();
		}
		
		catch (IOException ex)
		{
			ex.printStackTrace();
		} 
		
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return arrList;
		
	}
	
	/**
	 * In this method we are going to write to the file we created.
	 * @param fileName represents the file name we want to write into.
	 * @param toUpdate represents the new data we want to write.
	 */
	public static void writeToFile(String fileName,ArrayList <?> toUpdate){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;		
		
		try {
			fos = new FileOutputStream(fileName + ".ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(toUpdate);
			out.close();
		}
		
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * In this method we are going to check whether a string only char a-z and A-Z.
	 * @param toCheck represents the string we want to check.
	 * @return returns true if contains only char letter, false otherwise.
	 */
	public static boolean checkStr(String toCheck) {
		for(int i = 0; i < toCheck.length(); i++) {
			if(!(toCheck.charAt(i) >= 'a' && toCheck.charAt(i) <= 'z') && !(toCheck.charAt(i) >= 'A' && toCheck.charAt(i) <= 'Z') && (toCheck.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * In this method we are going to check whether a model contains a valid string
	 * A valid string means that chars letters and numbers can be used only.
	 * @param toCheck represents the string to check.
	 * @return returns true if valid, false otherwise.
	 */
	public static boolean checkModel(String toCheck) {
		for(int i = 0; i < toCheck.length(); i++) {
			if(!(toCheck.charAt(i) >= '0' && toCheck.charAt(i) <= '9') && !(toCheck.charAt(i) >= 'a' && toCheck.charAt(i) <= 'z') && !(toCheck.charAt(i) >= 'A' && toCheck.charAt(i) <= 'Z') && (toCheck.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * In this method we are going to check if a string contains only numbers.
	 * @param checkId represents the string we want to check.
	 * @return returns true if contains only numbers, false otherwise.
	 */
	public static boolean checkOnlyNum(String checkId) {
		for(int i = 0; i < checkId.length(); i++) {
			if(!(checkId.charAt(i) >= '0' && checkId.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * In this method we are going to show error if the client didn't do a valid action.
	 * @param txt represents the object that are passed which is JTextField.
	 * @param str represents the name of the label.
	 * @param error represents the error label that presented on the frame.
	 */
	public static void showError(JTextField txt,String str,JLabel error) {
		txt.setBackground(Color.red);
		error.setText("Please fix your " + str +".");
		if(txt instanceof JPasswordField) {
			txt.setText("");
			return;
		}	
		txt.setText("Please insert valid "+ str + ".");		
	}
	
	/**
	 * In this method we are going to create the choice category
	 * @param category represents a ENUM field that going to contain all the Category ENUM options.
	 * @return
	 */
	public static Choice addCategory(Choice category) {
		category.add(""+Category.MINI);
		category.add(""+Category.SUV);
		category.add(""+Category.SEDAN);
		category.add(""+Category.MANAGER);
		
		return category;
	}
	
	/**
	 * In this method we are setting the production year choice, giving range of 100 years back.
	 * @param prodYear represents the Choice object.
	 * @return
	 */
	public static Choice productionYear(Choice prodYear) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = 0; i < Constants.YEAR_RANGE; i++) {
			prodYear.add(Integer.toString(year-i));
		}
		return prodYear;
	}
	
	/**
	 * In this method we are going to set image as background.
	 * @param frame represents the frame we are working on.
	 * @param img represents the image string in the images folder.
	 */
	public static void setImageBackground(JFrame frame, String img) {
		try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/" + img + ".jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * In this method we are receiving a label and design it.
	 * @param lbl represent the label we get.
	 * @param size represents the font's size.
	 * @return returns a new designed label
	 */
	public static JLabel setFont(JLabel lbl, int size) {
		 lbl.setFont(new Font("Calibri", Font.BOLD, size));
		 lbl.setForeground(Color.WHITE);
		 return lbl;
	}
}