package qbs;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class Main {

	/*TODO:
	   wrap up
	*/
	//replace bytes in given files
	static void replaceBytes(List<File> files, String replaceBytes, String toBeReplacedBytes){
		for(File file:files) {
			try {
				//get bytes from 1 file
				byte[] fileBytes = Files.readAllBytes(file.toPath());
				String bytesAsString = "";

				//change them into string with spaces in between bytes
				for (byte b : fileBytes) {
					String s = String.valueOf(b);
					bytesAsString = bytesAsString.concat(s + " ");
				}

				//replace bytes
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
				os.close();

			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	//returns array of files with given extention
	static List<File> findFilesByExt(ExtentionFilter extFilter, File mainDir){
		File[] allFilesAndDirs;
		File[] tempFiles;
		List<File> filesWithExt = new ArrayList<>();

		allFilesAndDirs = mainDir.listFiles();
		tempFiles = mainDir.listFiles(extFilter);

		filesWithExt.addAll(Arrays.asList(tempFiles));

		//look if a file or dir
		for (File file : allFilesAndDirs) {
			//if dir, look for files in that dir
			 if (file.isDirectory()) {
				filesWithExt.addAll(findFilesByExt(extFilter, file));
			}
		}
		return filesWithExt;
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

	    //create filter
		ExtentionFilter extFilter = new ExtentionFilter(extention);

		//find folder
	    File dir = new File(pathToDir);
	    if(dir.exists())
	    	System.out.println("folder found");
	    else
	    	System.out.println("not found");

		//replace bytes
		replaceBytes(findFilesByExt(extFilter, dir), replaceBytes, toBeReplacedBytes);
    }
}
