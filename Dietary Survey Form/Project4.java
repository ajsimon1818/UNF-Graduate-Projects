/* 
 * Author:    Andrew Simon (n00695969)
 * Course:    COP3503 
 * Project #: 4
 * Title  :  Dietary Survey Form
 * Due Date:  8/4/2022 
 * 
 * This program presents a GUI to the user to input their dietary information. 
 * Upon execution, the program creates a csv file for the data and creates a 
 * header for the file. The GUI has two buttons that trigger actions. The 
 * "Clear" button resets the component values in the GUI to their default 
 * values and the "Submit" button logs their data entered into the csv file. 
 * (Time of submission logged as well)
 * 
 */ 
import javax.swing.JFrame;

/**
 * 
 * @author Andrew Simon
 * -Creates the CustomJFrame for the GUI and presents it as visible for the 
 * user to interface with.
 *
 */
public class Project4 {

	public static void main(String[] args) {
		
		CustomJFrame frame = new CustomJFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
