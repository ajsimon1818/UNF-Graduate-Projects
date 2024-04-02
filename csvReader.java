import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class csvReader {

	public static void filePrint(String[] fullFile) {
		System.out.printf(" %-14s %-15s %-9s %-1s\n", fullFile[0], fullFile[1], fullFile[2],fullFile[3]);
	}
	
	public static void main(String[] args) {
		
		
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Please enter csv file path:");
		
		String path = scnr.next();
		String line = "";
		
		System.out.println("Attempting to open: " + path);
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			System.out.println("File successfully read.");
			System.out.println();
			while(((line = br.readLine()) != null))
			{
				String [] fullFile = line.split(",");
				filePrint(fullFile);
			}
			br.close();
		} catch (FileNotFoundException e) {
			
			System.out.println("ERROR: File not found");
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
