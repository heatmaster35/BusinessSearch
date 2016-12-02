//#################################
//  Name: Leo Gomez               #
//# CruzID: legomez               #
//# Class: CMPS-12B               #
//# Date: Oct 21, 2014            #
//# filename: BusinessSearch.java #
//# Details: Stores and searches a#
//# a store name for a phone num. #
//#################################
//
import java.io.File;
import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;


class BusinessSearch {
	public static int size = 0;
	public static String Searchkey = "";
	public static int found1 = 0;
	public static int notfound = 0;
	public static void main( String[] args )throws IOException {
		// if args.length == < 2 then print out usage
		// else read from file, sort elements, and ask for query
//------------------------------------------------------------------------------
		//File Transfer Info
		//stores the information into two arrays respectfully.
		if (args.length != 1){ 
			System.out.println("Usage: BusinessSearch businessDB");
			System.exit(1);
		}

		File InFile = new File(args[0]);
                BufferedReader in = new BufferedReader(new FileReader(InFile));
                Scanner scan = new Scanner(System.in);
		
		try{
			String input = "";
			Scanner c = new Scanner(InFile);
                        if (c.hasNextLine()) {
				String que = "";
                                que = c.nextLine();
				size = Integer.parseInt(que);
                                }
                        String [] businessesnames = new String[size];
                        String [] businessesnumbers = new String[size];

			for ( int i = 0; i < size ; i++)
				{
                        	if (c.hasNextLine()) {
                                	input = c.nextLine();
                                	String[] stuff = input.split(",");
					businessesnames[i] = stuff[0];
					businessesnumbers[i] = stuff[1];
                                }
			}
//--------------------------------------------------------------------------------------
			// Merge Sort
			mergesort(businessesnames, businessesnumbers); 
//--------------------------------------------------	if(c.hasNextLine())
		    {
			String que = "";
			que = c.nextLine();
		    ------------------------------------
			// Binary Search
			while(true) {
				String Searchkey = scan.nextLine();
				if (Searchkey.compareTo("") == 0  ) break;
				int found = binarysearch(businessesnames,Searchkey);
				if( found < 0 ) {
					System.out.println("NOT FOUND");
					notfound++;
				} else {
					System.out.println(businessesnumbers[found]);
					found1++;
				}
			}
			System.out.println(found1+notfound + " total queries, " + notfound + " not found");
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in !=null)in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	//Binary Search code
	public static int binarysearch(String[] names, String key)
		{
		int mid = 0;
		int low = 0;
		int high = size-1;
                while (low <= high){
                        mid = (low + high)/2;
                        if (names[mid].compareTo(key) > 0)
                                high = mid-1;
                        else if (names[mid].compareTo(key) < 0)
                                low = mid+1;
			else
				return mid;
                }
		return -1;
	}

	//Merge Sorting code
	public static void mergesort(String[] business, String[] numbers)
		{
		if (business.length >= 2)
			{
			String[] leftb = new String[business.length/2];
			String[] rightb = new String[business.length - (business.length/2)];
			String[] leftn = new String[business.length/2];
			String[] rightn = new String[business.length - (business.length/2)];
			for (int a = 0; a < leftb.length; a++) {
				leftb[a] = business[a];
				leftn[a] = numbers[a];
				
			}
			
			for (int b = 0; b < rightb.length; b++) {
				rightb[b] = business[b + (business.length/2)];
				rightn[b] = numbers[b + (numbers.length/2)];
			}
			
			mergesort(leftb,leftn);
			mergesort(rightb,rightn);
			merge(business,numbers, leftb, leftn, rightb, rightn);
		}
	}
	
	public static void merge(String[] business,String[] numbers, String[] leftb,String[] leftn,String[] rightb, String[] rightn)
		{
		int counter1 = 0;
		int counter2 = 0;
		for (int i = 0; i < business.length; i++) {
			if ((counter2 >= rightb.length) || ((counter1 < leftb.length) && (leftb[counter1].compareToIgnoreCase(rightb[counter2]) < 0)))
				{
				business[i] = leftb[counter1];
				numbers[i] = leftn[counter1];
				counter1++;
			} else {
				business[i] = rightb[counter2];
				numbers[i] = rightn[counter2];
				counter2++;
			}
		}
	}
}
