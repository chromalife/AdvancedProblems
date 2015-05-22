import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Class: RegexColor

* @author Anwar Saleeby

* @collaborators: Brock Bearchell 

* @version final

* Course : ITEC 3150 Spring 2014

* Written: Thursday February 13 2014

*

* This class – Reads a string of hexadecimal from a text file called hexcode.txt and makes sure that it is a valid hex.

* DISCLAIMER: I am not sure that you can have a hexcode of all numbers and no letters, so if you cannot have that then the regex is incorrect. :(

* Purpose: – To show us the scalability of regex to meet demands of complex operations. We tried our best to get it to be as optimal as possible.

*/
public class RegexColor
// Declares our scanners and printwriters / readers. 
{
	private FileReader fr;
	private Scanner sc;
	private Scanner sc2;
	private PrintWriter pw;
	
	//This boolean is a bit magical. I think that it takes a regex pattern and assigns it to a class that matches the regex with some meaningful data.
	public boolean validateID(String s)
	{
		Pattern p = Pattern.compile("#(([0-9a-fA-F]{2}){3}|([0-9a-fA-F]){3})"); 
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	//Method in which we pass through our text file to our scanner.
	public void readFirstTokenandValidate()
	{
		try 
		{
			fr = new FileReader("hexcode.txt");
			sc = new Scanner(fr);
			pw = new PrintWriter("Invalidzipcodes.txt");
			
			// This loop has two scanners, one to read the nextline(nextLine) and one to read the next bit of uninterrupted text(nextToken)
			// Borrowed from Dr. B.
			while (sc.hasNextLine())
			{
				String nextLine = sc.nextLine(); //gets the entire next line with junk and all!
				sc2 = new Scanner(nextLine); //we want to find only the first token in the line
				String nextToken = sc2.next();
				
				// Would like clarification on the purpose of boolean below.
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
		//Catches any exceptions that we may encounter.
		catch (FileNotFoundException fnfe) 
				{ System.out.println("File not found"); fnfe.printStackTrace(); }		
		catch (Exception e) 
			{ e.printStackTrace(); }
	}
	// Main method to "run" our RegexColor class. 
	public static void main(String[] args)
	{
		RegexColor szcv = new RegexColor(); 
		szcv.readFirstTokenandValidate();
	}
}
