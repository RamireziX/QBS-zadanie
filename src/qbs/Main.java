package qbs;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class Main {

	/*TODO:
	   *search for files in subfolders also
	   *maybe add something if bytes were not found
	*/
	static void replaceBytes(File[] files, String replaceBytes, String toBeReplacedBytes){
		//get byte array for each file
		int noOfFiles = 0;
		for(File file:files) {
			try {
				byte[] fileBytes = Files.readAllBytes(file.toPath());
				String bytesAsString = "";

				//print those bytes
			/*for(byte b:fileBytes){
				String s = String.valueOf(b);
				System.out.print(s + " ");
			}*/

				//change them into string with spaces in between bytes
				for (byte b : fileBytes) {
					String s = String.valueOf(b);
					bytesAsString = bytesAsString.concat(s + " ");
				}

				//replace bytes as strings
				bytesAsString = bytesAsString.replaceAll(replaceBytes, toBeReplacedBytes);
				String[] oneByte = bytesAsString.split(" ");

				//parse strings as bytes
				for (int i = 0; i < oneByte.length; i++) {
					try {
						fileBytes[i] = Byte.parseByte(oneByte[i], 10);//handle exception too big number
					} catch (NumberFormatException e) {
						System.out.println(e);
						System.out.println("Will not be replaced");
					}
				}
				//put the replaced bytes into the file
				OutputStream os = new FileOutputStream(file);

				os.write(fileBytes);
				noOfFiles ++;

				os.close();

				//debug:
				//System.out.println(bytesAsString);
				//byte decByte = Byte.parseByte(oneByte[0], 10);
			/*for(String s:oneByte){
				System.out.print(s + "&");
			}*/
				//System.out.println(decByte);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		System.out.println("Byte replacement succesfull for " + noOfFiles + " files");
	}

    public static void main(String[] args) {
    	//input from user
	    Scanner scan = new Scanner(System.in);

	    System.out.println("Wpisz sciezke folderu, rozszerzenie, ciag bitow 1 ciag bitow 2");
	    String pathToDir = scan.nextLine();
	    String extention = scan.nextLine();
	    String replaceBytes = scan.nextLine();
	    String toBeReplacedBytes = scan.nextLine();

	    scan.close();

		//find folder
	    File dir = new File(pathToDir);
	    if(dir.exists())
	    	System.out.println("folder found");
	    else
	    	System.out.println("not found");

	    //find files with given extention
		ExtentionFilter extFilter = new ExtentionFilter(extention);
		File[] filesWithExt;
		filesWithExt = dir.listFiles(extFilter);

		/*for(int i=0;i<filesWithExt.length;i++)
			System.out.println(filesWithExt[i].getName());*/

		replaceBytes(filesWithExt, replaceBytes, toBeReplacedBytes);

		//debug check input
	    //System.out.printf("%s, %s, %s, %s", pathToDir, extention, replaceBytes, toBeReplacedBytes);
    }
}
