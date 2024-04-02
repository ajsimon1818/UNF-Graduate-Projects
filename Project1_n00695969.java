/* 
 * Author:    Andrew Simon (n00695969)
 * Course:    COP3503 
 * Project #: 1 
 * Title  :   Number Statistics Menu
 * Due Date:  6/13/2022 
 * 
 * - Prompts the user for a list of numbers separated by a space.
 * - Provides a menu of options including:
 * 		. Display Number Statistics
 * 		. Order List (Least to Greatest)
 * 		. Count Number of Even/Odd
 * 		. Count Number of Prime
 * 		. Enter New List
 * 		. Quit Program
 */ 

import java.util.Arrays;
import java.util.Scanner;

/** - Prompts the user for a list of numbers separated by a space.
* - Provides a menu of options including:
* 		. Display Number Statistics
* 		. Order List (Least to Greatest)
* 		. Count Number of Even/Odd
* 		. Count Number of Prime
* 		. Enter New List
* 		. Quit Program
*/ 
public class Number_Stats {
	
	public static void main(String[] args) {
			displayMenu();
		}
	
	/** 
     * Get's user input and runs the menu for user. 
     */ 
	public static void displayMenu()
	{
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		int[]userNums = getInput(scan);
		
		do 
		{
			
			printOptions();
			String choice = scan.nextLine();

			switch (choice)
			{
			case "1":
				
				printStats(userNums);
				System.out.println();
				break;
				
			case "2":
				
				orderedNums(userNums);
				System.out.println();
				break;
				
			case "3":
				
				evenOdd(userNums);
				System.out.println();
				break;
				
			case "4":

				primeAry(userNums);
				System.out.println();
				break;
				
			case "5":

				userNums = getInput(scan);
				break;
				
			case "quit":
				
				System.out.println("Quiting Program");
				flag = false;
				break;
				
			default:
				System.out.println("Wrong Selection");
				System.out.println();
				break;
			}
			
		}while(flag);
		scan.close();
	}
	
	/** 
     * Prints menu options to screen. 
     */ 
	public static void printOptions()
	{
		System.out.println("Please make a selection:");
		System.out.println("1: Display List Statistics");
		System.out.println("2: Display List Ordered");
		System.out.println("3: Number of Odd/Even");
		System.out.println("4: Check for Prime Numbers");
		System.out.println("5: Enter New List");
		System.out.println("quit: Quit Program");
		System.out.println();
	}
	
	/** 
     * Scans in a number list from user. 
     * @param scan Scanner initialized in displayMenu().
     * @return Integer array of input numbers. 
     */ 
	public static int[] getInput(Scanner scan)
	{
		
		System.out.println("Enter List of Integers Separated by Spaces");
	
		String userString = scan.nextLine();
		String[] splitInput = userString.split(" ");
		int[] userNums = new int[splitInput.length];
		for(int i = 0;i < splitInput.length;i++)
		{
		   userNums[i] = Integer.parseInt(splitInput[i]);
		}
		
		return userNums;
	}
	
	/** 
	 * Prints out the statistics of numbers in an array.
	 * Includes: minimum, maximum, total count, range, median, mean, mode, population variance, and standard deviation
	 * @param useNums Array of user input numbers.
	 */ 
	public static void printStats(int[] userNums)
	{
		System.out.println("Min: " + minNum(userNums));
		System.out.println("Max: " + maxNum(userNums));
		System.out.println("Count: " + userNums.length);
		System.out.println("Range: " + (maxNum(userNums) - minNum(userNums)));
		medianNum(userNums);
		System.out.printf("Mean: %.1f\n", meanOfNums(userNums));
		mode(userNums);
		varAndDev(userNums);
	}

	/** 
     * Calculates the minimum number in an array.
     * @param userNums Array of user input numbers. 
     * @return Minimum number in array.
     */ 
	public static int minNum(int[] userNums)
	{
		int min = userNums[0];
		for (int num : userNums)
		{
			if(num <= min)
			{
				min = num;
			}
		}
		return min;
	}
	
	/** 
     * Calculates the maximum number in an array. 
     * @param useNums Array of user input numbers. 
     * @return Maximum number in array .
     */ 
	public static int maxNum(int[] userNums)
	{
		int max = userNums[0];
		for (int num : userNums)
		{
			if(num >= max)
			{
				max = num;
			}
		}
		return max;
	}
	
	/** 
     * Calculates the median of numbers in an array.
     * @param useNums Array of user input numbers.
     */ 
	public static void medianNum(int[] userNums)
	{
		double median = 0;
		Arrays.sort(userNums);
		if(userNums.length % 2 == 0)
		{
			int sumOfNums = userNums[userNums.length / 2] + userNums[userNums.length / 2 - 1];
			 median = (double) sumOfNums / 2;
		}
		else
		{
			median = (double) userNums[userNums.length / 2];
		}
		
		System.out.println("Median: " + median);
	}
	
	/** 
     * Calculates the mode of numbers in an array.
     * @param useNums Array of user input numbers.
     */ 
	public static void mode(int[] userNums) {
	      int maxValue = 0;
	      int maxCount = 0;
	
	      for (int i = 0; i < userNums.length; ++i) {
	         int count = 0;
	         for (int j = 0; j < userNums.length; ++j) {
	            if (userNums[j] == userNums[i])
	            ++count;
	         }
	
	         if (count > maxCount) {
	            maxCount = count;
	            maxValue = userNums[i];
	         }
	      }
	      if(maxCount > 1)
	      {
	      System.out.println("Mode: " + maxValue);
	      }
	      else
	      {
	    	  System.out.println("Mode: No Mode");
	      }
	   }

	/** 
     * Calculates the mean of numbers in an array.
     * @param useNums Array of user input numbers.
     * @return Mean of numbers in the array
     */ 
	public static double meanOfNums(int[] userNums)
	{
		double mean = 0;
		double sum = 0;
		for(int num : userNums)
		{
			sum += num;
		}
		mean = sum / (double)userNums.length;
		return mean;
	}
	
	/** 
     * Calculates the population variance and standard deviation of numbers in an array.
     * @param useNums Array of user input numbers.
     */ 
	public static void varAndDev(int[] userNums)
	{
		double popVar = 0;
		double[] varNums = new double[userNums.length];
		double varSum = 0;
		
		for(int i = 0; i < userNums.length; i++)
		{
			varNums[i] = Math.pow(userNums[i] - meanOfNums(userNums), 2);
			varSum += varNums[i];
		}
		popVar = varSum / (userNums.length);
		System.out.printf("Population Variance: %.2f\n", popVar);
		System.out.printf("Standard Deviation: %.2f\n", Math.sqrt(popVar));
	}
	
	/** 
     * Orders the numbers in an array from least to greatest.
     * @param useNums Array of user input numbers.
     */ 
	public static void orderedNums(int[] userNums)
	{
		System.out.println("Numbers Ordered From Least to Greatest");
		Arrays.sort(userNums);
		
		for (int num : userNums)
		{
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	/** 
     * Calculates the number of odd and even numbers in an array.
     * @param useNums Array of user input numbers.
     */ 
	public static void evenOdd(int[]userNums)
	{
		int even = 0;
		int odd = 0;
		
		for(int num : userNums)
		{
			if( num % 2 == 0)
			{
				even++;
			}
			else
			{
				odd++;
			}
		}
		System.out.println("Number of Evens/Odds");
		System.out.println("Evens: " + even);
		System.out.println("Odd: " + odd);
	}
	
	/** 
     * Calculates if a given integer is prime.
     * @param n An integer to be tested
     * @return true The given number is prime.
     * @return false The given number is not prime.
     */ 
	public static boolean isPrime(int n)
	{
		if( n <= 1)
		{
			return false;
		}
		if (n <= 3)
		{
			return true;
		}
		
		if( n % 2 == 0 || n % 3 == 0) 
		{
			return false;
		}
		
		for(int i = 5; i * i <= n; i = i + 6)
		{
			if( n % i == 0 || n % (i + 2) == 0)
			{
				return false;
			}
		}
		
		
		return true;
	}
	
	/** 
     * Runs an array of numbers through the isPrime() method to test if they are prime
     * @param useNums Array of user input numbers.
     */ 
	public static void primeAry(int[] userNums)
	{
		int primeCount = 0;
		for(int num : userNums)
		{
			if(isPrime(num))
			{
				primeCount++;
			}
		}
		System.out.println("Primes: " + primeCount);
	}
}
