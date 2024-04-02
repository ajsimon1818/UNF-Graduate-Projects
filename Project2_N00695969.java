/* 
 * Author:    Andrew Simon (n00695969)
 * Course:    COP3503 
 * Project #: 2 
 * Title  :  Input/Output
 * Due Date:  6/30/2022 
 * 
 * Prompts the user for an input to a csv file/path, reads the data into ArrayLists,
 * formats the date from MM/DD/YYYY to YYYY/MM/DD, creates a new column for the 
 * difference between the first two set of double values, next two sets of double values,
 * and the average of all four sets of double values, and prints all read/created values 
 * into a new csv file.
 */ 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/** Prompts the user for an input to a csv file/path, reads the data into ArrayLists,
 * formats the date from MM/DD/YYYY to YYYY/MM/DD, creates a new column for the 
 * difference between the first two set of double values, next two sets of double values,
 * and the average of all four sets of double values, and prints all read/created values 
 * into a new csv file.
*/ 
public class Project2 {
	public static ArrayList<String> dates = new ArrayList<String>();
	public static ArrayList<String> times = new ArrayList<String>();
	public static ArrayList<Double> sensor2278 = new ArrayList<Double>();
	public static ArrayList<Double> sensor3276 = new ArrayList<Double>();
	public static ArrayList<Double> sensor4689 = new ArrayList<Double>();
	public static ArrayList<Double> sensor5032 = new ArrayList<Double>();
	public static ArrayList<Double> section1Diff = new ArrayList<Double>();
	public static ArrayList<Double> section2Diff = new ArrayList<Double>();
	public static ArrayList<Double> totalAvg = new ArrayList<Double>();
	public static int counter = 0;
	
	public static void main(String[] args) {
		
		boolean flag = false;
		Scanner console = new Scanner(System.in);
		System.out.println("Project 2 Data Preprocessing\n");
		
		while(flag == false){
			try{
				//Prompt user for input
				String fileName = getFile(console);
				
				//Read in and store data from file
				readFile(fileName);
				
				//print file data to new csv file
				printFileData(fileName);
		
				System.out.println("Done! Exiting Program");
				console.close();
			} 
			catch (FileNotFoundException e){
				System.out.println("*Invalid filename. Please try again*");
			}
			catch (NumberFormatException e){
				System.out.println("*Bad number data in CSV file. Please try again*");
				resetArrayLists();
			}
			catch (ParseException e){
				System.out.println("*Bad Date format in CSV file. Please try again*");
				resetArrayLists();
			}
			
			//Stops looping if loop count finishes all 299 lines of the Speed_Data.csv file data without an exception
			//Hard coded for this project but the solution would involve some condition along the lines of
			//"if scanner.nextLine() == null".
			if(dates.size() > 298)
			{
				flag = true;
			}
			
		}//end flag loop
	}//end main
	
	/** 
	 * Formats a MM/DD/YYYY date to YYYY/MM/DD
	 * @param Scanner console is input from user at keyboard
	 * @return String fileName is the name of the file/path for user csv file 
	 */ 
	public static String getFile(Scanner console)
	{
		System.out.println("Please enter file name & location: ");
		String fileName = console.next();
		System.out.printf("Reading in Data from the file: %s\n", fileName);
		
		return fileName;
	}

	/** 
	 * Reads in data from input csv file and stores it into appropriate ArrayLists.
	 * (Skips the first row of column names)
	 * @param String fileName is name of file/path that the user input.
	 */ 
	public static void readFile(String fileName) throws FileNotFoundException
	{
		File inputFile = new File(fileName);
		Scanner lineRead = new Scanner(inputFile);
		
		while(lineRead.hasNextLine())
		{
			String line = lineRead.nextLine();
			String[]lineSplit = line.split(",");
			
			if(counter > 0) {
				
			dates.add(lineSplit[0]);
			times.add(lineSplit[1]);
			sensor2278.add(Double.parseDouble(lineSplit[2]));
			sensor3276.add(Double.parseDouble(lineSplit[3]));
			sensor4689.add(Double.parseDouble(lineSplit[4]));
			sensor5032.add(Double.parseDouble(lineSplit[5]));
			section1Diff.add(sensor2278.get(counter - 1) - sensor3276.get(counter - 1));
			section2Diff.add(sensor4689.get(counter - 1) - sensor5032.get(counter - 1));
			double avg = (sensor2278.get(counter - 1) + sensor3276.get(counter - 1) + sensor4689.get(counter - 1) + sensor5032.get(counter - 1)) / (double)(4);
			totalAvg.add(avg);
			}
			counter++;
		}
		lineRead.close();
	}

	/** 
     * Formats a MM/DD/YYYY date to YYYY/MM/DD
     * @param String date is original date.
     * @return String date is the date newly formatted . 
     */ 
	public static String dateFormat(String date) throws ParseException
	{
		SimpleDateFormat currentFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Date dateObject = currentFormat.parse(date);
		date = newFormat.format(dateObject);
		
		return date;
	}
	
	/** 
     * Prints data read in and stored, out to a new csv file. 
     * @param String fileName is name of file/path that the user input. 
     */ 
	public static void printFileData(String fileName) throws FileNotFoundException, ParseException
	{
		System.out.println("Converting Dates from MM/DD/YYYY to YYYY/MM/DD");
		String outPath = fileName.substring(0, fileName.length() - 4);
		outPath += "_Difference.csv";

		PrintWriter out = new PrintWriter(outPath);
		out.println("Date, Time,Sensor_2278,Sensor_3276,Sensor_4689,Sensor_5032,Section1_Diff,Section2_Diff,Total_Avg");
		for(int i = 0; i < dates.size(); i++)
		{
			String newDate = dateFormat(dates.get(i));
			
			out.print(newDate + ",");
			out.print(times.get(i) + ",");
			out.print(sensor2278.get(i) + ",");
			out.print(sensor3276.get(i) + ",");
			out.print(sensor4689.get(i) + ",");
			out.print(sensor5032.get(i) + ",");
			out.print(section1Diff.get(i) + ",");
			out.print(section2Diff.get(i) + ",");
			out.println(totalAvg.get(i));
		}
		System.out.println("Calculating Speed Difference");
		System.out.println("Calculating Speed Average");
		System.out.println("Writing data to file Speed_Data_Difference.csv");
		out.close();
	}
	
	/** 
     * Resets the contents of all ArrayLists and the global counter variable  
     */ 
	public static void resetArrayLists()
	{
		counter = 0;
		dates.clear();
		times.clear();
		sensor2278.clear();
		sensor3276.clear();
		sensor4689.clear();
		sensor5032.clear();
		section1Diff.clear();
		section2Diff.clear();
		totalAvg.clear();
	}
}//program
