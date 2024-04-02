import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * -Creates the "survey_results.csv" file and writes the user's data into 
 * the file with the current time and date attached
 * @author Andrew Simon
 *
 */
public class FileHandler {
	
	private String surveyFile = "";
	private FileWriter fileOutput;
	private PrintWriter printWriter;
	
	/**
	 * -Creates a new csv file called "survey_results.csv"
	 */
	public FileHandler() {
		surveyFile = "survey_results.csv";
		try {
			fileOutput = new FileWriter(surveyFile);
			printWriter = new PrintWriter(fileOutput);
			printWriter.println("DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight");
			
			printWriter.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			System.out.println("Results file already open. Please close to continue");
			//e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * -Writes the result data the user entered from the GUI into the 
	 * "survey_data.csv" file created by the constructor
	 * @param surveyData - user data from the GUI after hitting "Submit"
	 */
	public void writeResults(String surveyData)
	{
		String dataFile = "survey_results.csv";
		try {
			fileOutput = new FileWriter(dataFile, true);
			printWriter = new PrintWriter(fileOutput);
			
			printWriter.println(dateTime() + "," + surveyData);

			printWriter.close();
			fileOutput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Results file already open. Please close to continue");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * - Returns the current date and time
	 * @return dateTime - The current date and time
	 */
	public static String dateTime()
	{
		String dateTime = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
		
		Date date = new Date();
		
		dateTime = dateFormat.format(date);
		
		return dateTime;
	}
}
