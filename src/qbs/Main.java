package qbs;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Wpisz sciezke folderu, rozszerzenie, ciag bitow 1 ciag bitow 2");
	    String pathToFolder = scan.nextLine();
	    String extention = scan.nextLine();
	    String replaceBytes = scan.nextLine();
	    String toBeReplacedBytes = scan.nextLine();

	    File dir = new File(pathToFolder);

	    if(dir.exists())
	    	System.out.println("folder found");
	    else
	    	System.out.println("not found");

	    //System.out.printf("%s, %s, %s, %s", folder, extention, replaceBytes, toBeReplacedBytes);
    }
}
