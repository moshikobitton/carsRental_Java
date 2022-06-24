package View;
import javax.swing.*;
import Model.Functions;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represent the opening frame for client and shows him his options.
 * @author Tamir Uzan and Moshiko Bitton
 *
 */
public class Client extends JFrame{
	/**
	 * @showOpenTime This button sending the user to other frame that show him the activity all branches time.
	 * @showRentOpt This button sending the user to other frame - search car for rent.
	 * @logOut This button returns the user to the main frame.
	 */
	private static final long serialVersionUID = 9L;
	private JButton showOpenTime;	
	private JButton showRentOpt;
	private JButton logOut;        
	
	public Client() {
		super("Client");
		showOpenTime = new JButton("Show activity time");
		showRentOpt = new JButton("Show rental options");
		logOut = new JButton("Log out");
		initalize();
		
	}
	
	public void initalize() {	
		
		/////// set image as background
		Functions.setImageBackground(this, "client");
		/////////
		
		setLayout(new BorderLayout());
		JPanel p = new JPanel(new FlowLayout());
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(Functions.setFont(new JLabel("Hello Client!"), 30));
		p1.add(Functions.setFont(new JLabel(" Please choose action"), 30));
		p1.setBackground(Color.darkGray.darker());
		add(p1,BorderLayout.NORTH);
		
		p.add(showOpenTime);
		p.add(showRentOpt);
		p.add(logOut);
		p.setBackground(Color.darkGray.darker());
		add(p,BorderLayout.SOUTH);
		pack();
		
		showOpenTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				ShowActivity time= new ShowActivity();
				time.setVisible(true);
			}
		});
		
		showRentOpt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				CarRent rent= new CarRent();
				rent.setVisible(true);
			}
		});
		
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame main = new MainFrame();
				setVisible(false);
				main.setVisible(true);
				
			}
		});
	}
}
