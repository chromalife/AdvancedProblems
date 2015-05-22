import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Class: StudentIDMatcher

* @author Anwar Saleeby

* @collaborators Brock Bearchell

* @version final

* Course : ITEC 3150 Spring 2014

* Written: Saturday February 16 2014

*

* This class – Reads a text file of numbers and compares them to a 9000 number( studentID) via Regular Expressions. Then writes to a file which are valid and invalid.

* Purpose: – To make sure that no invalid StudentID's are passed through a text file. 

*/
public class StudentIDMatcher
{
	private FileReader fr;
	private Scanner sc;
	private Scanner sc2;
	private PrintWriter pw;

	//This boolean is a bit magical. I think that it takes a regex pattern and assigns it to a class that matches the regex with some meaningful data.
	public boolean validateID(String s)
	{
		Pattern p = Pattern.compile("[9]{1}[0]{3}[0-9]{5}"); 
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	//Try catch for debuggins that passes our textfile to the scanner for analysis. 
	public void readFirstTokenandValidate()
	{
		try 
		{
			fr = new FileReader("Zipcodes.txt");
			sc = new Scanner(fr);
			pw = new PrintWriter("Invalidzipcodes.txt");
			
			// Would like clarification on the purpose of boolean below.
			while (sc.hasNextLine())
			{
				String nextLine = sc.nextLine(); 
				sc2 = new Scanner(nextLine); 
				String nextToken = sc2.next();
				boolean validID = validateID(nextToken);
				if (!validID) 
				{
					System.out.println(nextToken + " is not valid" );
					pw.println(nextToken + " is not valid");
				}
				else
				{
					System.out.println(nextToken +" is valid");
					pw.println(nextToken + " is valid");
				}

			}
			sc.close();
			sc2.close();
			pw.close();				
		} 
		catch (FileNotFoundException fnfe) { System.out.println("File not found"); fnfe.printStackTrace(); }		
		catch (Exception e) { e.printStackTrace(); }
	}
	//Main method to "run" our matcher class. 
	public static void main(String[] args)
	{
		StudentIDMatcher szcv = new StudentIDMatcher(); 
		szcv.readFirstTokenandValidate();
	}
}
