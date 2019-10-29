package qbs;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
    	//input from user
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Wpisz sciezke folderu, rozszerzenie, ciag bitow 1 ciag bitow 2");
	    String pathToDir = scan.nextLine();
	    String extention = scan.nextLine();
	    String replaceBytes = scan.nextLine();
	    String toBeReplacedBytes = scan.nextLine();

	    File dir = new File(pathToDir);

		//find folder
	    if(dir.exists())
	    	System.out.println("folder found");
	    else
	    	System.out.println("not found");

	    //find files with given extention
		ExtentionFilter extFilter = new ExtentionFilter(extention);
		String[] filesWithExt;
		filesWithExt = dir.list(extFilter);
		for(String name:filesWithExt)
			System.out.println(name);

	    //System.out.printf("%s, %s, %s, %s", folder, extention, replaceBytes, toBeReplacedBytes);
    }
}
