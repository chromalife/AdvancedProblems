
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**Class: WoTWScanner

* @author Anwar Saleeby

* @collaborators Brock Bearchell

* @version 1.0

* Course : ITEC 3150 Spring 2014

* Written: Thursday February 13 2014

*

* This class – Scans a text file called WoTW.txt for words that have 16 or more characters, Using 

* a regular expression. 

* Purpose: – To show the power of Regular Expressions for algorithmic generation in respect to scanning a file.

*/
public class WoTWScanner
{
	private FileReader fr;
	private Scanner sc;
	private PrintWriter pw;
	
	
	//This boolean is a bit magical. I think that it takes a regex pattern and assigns it to a class that matches the regex with some meaningful data.
	public boolean validateID(String s)
	{
		Pattern p = Pattern.compile("[a-zA-Z]{16,}"); 
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	//This method uses our File Reader, Writer and Scanner to read WoTW.txt and uses the Regex paramaters to find our characters that have 16
	//or characters. Try/Catch for easy debugging. In an earlier version I threw a NoSuchElementException to catch when the program would break
	//from reaching end of file. The fix was to get rid of the extra scanner and use the nextline object of type string.
	public void readFirstTokenandValidate()
	{
		try 
		{
			fr = new FileReader("WoTW.txt");
			sc = new Scanner(fr);
			pw = new PrintWriter("lolz.txt");
			while (sc.hasNext())
				
			{
				String nextLine = sc.next(); //gets the entire next line with junk and all!
				
				
				boolean validID = validateID(nextLine);
				
				if (validID == true)
				{
					
					System.out.println(nextLine);
					pw.println(nextLine);
					
				}
				
				
				
			}
			sc.close();
			
			pw.close();
		
		} 
		catch (NoSuchElementException nsee) {System.out.println("Reached End of File"); nsee.printStackTrace();}
		catch (FileNotFoundException fnfe) { System.out.println("File not found"); fnfe.printStackTrace(); }		
		catch (Exception e) { e.printStackTrace(); }
	}
	//Main Method that runs our application. 
	public static void main(String[] args)
	{
		WoTWScanner szcv = new WoTWScanner(); 
		szcv.readFirstTokenandValidate();
	}
}
