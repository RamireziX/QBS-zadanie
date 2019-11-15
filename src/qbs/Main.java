package qbs;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class Main {

	/*TODO:
	   add something if user inputs wrong extention (without .) or wrong bytes
	*/
	//replace bytes in given files
	static void replaceBytes(List<File> files, String replaceBytes, String toBeReplacedBytes){
		System.out.println("Replacing bytes in these files:");
		int count = 0;

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

				if(bytesAsString.contains(replaceBytes))
					//replace bytes
					bytesAsString = bytesAsString.replaceAll(replaceBytes, toBeReplacedBytes);
				else
					//does not contain this byte pattern, skip
					continue;

				//put spaces in between bytes
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

				System.out.println(file);
				count ++;

			} catch (IOException e) {
				System.out.println(e);
			}
		}
		System.out.println("Replacement successful for " + count + " files");
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

	//checks if folder exists and returs it
	static File folderExists(String pathToDir, Scanner scan){
		File dir = new File(pathToDir);

		if(dir.exists()) {
			System.out.println("Folder found");
			return dir;
		}
		else {//in case folder is not found
			System.out.println("Folder not found, write the path again:");
			pathToDir = scan.nextLine();
			return folderExists(pathToDir, scan);
		}
	}

    public static void main(String[] args) {
    	//input from user
	    Scanner scan = new Scanner(System.in);

	    System.out.println("Write path to folder and press enter:");
	    String pathToDir = scan.nextLine();
		System.out.println("Write extention (.your_extention) and press enter:");
	    String extention = scan.nextLine();
		System.out.println("Write bytes you want to replace (in decimal, separate each byte by space) and press enter:");
	    String replaceBytes = scan.nextLine();
		System.out.println("Write bytes you want to switch previously found bytes (in decimal, separate each byte by space) and press enter:");
	    String toBeReplacedBytes = scan.nextLine();

	    //create filter
		ExtentionFilter extFilter = new ExtentionFilter(extention);

		//look for folder
		File dir = folderExists(pathToDir, scan);

		//replace bytes
		replaceBytes(findFilesByExt(extFilter, dir), replaceBytes, toBeReplacedBytes);

		scan.close();
    }
}
