package qbs;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
    	//input from user
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Wpisz sciezke folderu, rozszerzenie, ciag bitow 1 ciag bitow 2");
	    String pathToDir = scan.nextLine();
	    String extention = scan.nextLine();
	    String replaceBytes = scan.nextLine();
	    String toBeReplacedBytes = scan.nextLine();

		//find folder (for testing dir is now a file, later make a list of files with the same extention)
	    File dir = new File(pathToDir);
	    if(dir.exists())
	    	System.out.println("folder found");
	    else
	    	System.out.println("not found");

	    //find files with given extention
		/*ExtentionFilter extFilter = new ExtentionFilter(extention);
		String[] filesWithExt;
		filesWithExt = dir.list(extFilter);
		for(String name:filesWithExt)
			System.out.println(name);
		*/

		//get byte array of a file
		try {
			byte[] fileBytes = Files.readAllBytes(dir.toPath());

			/*//print those bytes
			for(byte b:fileBytes){
				String s = String.valueOf(b);
				System.out.print(s + " ");
			}*/
		}
		catch(IOException e){
			System.out.println("Error reading bytes from file");
		}

		//debug check input
	    //System.out.printf("%s, %s, %s, %s", folder, extention, replaceBytes, toBeReplacedBytes);
    }
}
