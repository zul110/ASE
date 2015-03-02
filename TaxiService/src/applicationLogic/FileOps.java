/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe methods for FileOps
 */
package applicationLogic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileNotFoundException;
public class FileOps {
	private String fileName = "";
	protected List<String> lines;
	
	/**
	 * Constructor for all file operations
	 * @param fileName
	 */
	public FileOps(String fileName) 
	{
		this.fileName = fileName;
		this.lines = new ArrayList<String>();
	}
	
	/**
	 * Read data from file
	 * In case of any errors during, the error message is thrown
	 * 
	 * @return A List of Strings containing lines read from the input file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws Exception
	 */
	public List<String> readLinesFromFile() throws FileNotFoundException, IOException, IllegalStateException, Exception 
	{
		try {
			Path path = Paths.get(getPath(fileName));
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			
			if(lines.size() == 0) {
				throw new IllegalStateException(fileName + ": Invalid file format, or empty file.");
			}
			
		} catch(FileNotFoundException fileEx) {
			throw fileEx;
		} catch(IOException ioEx) {
			throw ioEx;
		} catch(Exception ex) {
			throw ex;
		}
		
		return lines;
	}
	
	/**
	 * Write data to a text file
	 * @param fileName
	 * @param textToWrite
	 * @throws Exception
	 */
	public static void writeToFile(String fileName, String textToWrite) throws Exception 
	{
		fileName += "_";
		fileName += new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		fileName += ".txt";
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		    writer.write(textToWrite);
		   
		    Helpers.println("Added data to file \"" + fileName + "\" successfully.");
		} catch (IOException ex) {
			throw new IOException();
		} finally {
			try {
			   writer.close();
			} catch(Exception ex) {
			   Helpers.println("Error in closing file.");
			   Helpers.println("More info:");
			   Helpers.println(ex.getMessage());
			}
		}
	}
	
	/**
	 * Return path of the file 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public String getPath(String fileName) throws FileNotFoundException {
		URL url = Main.class.getClassLoader().getResource(fileName);
		
		if(url == null) {
			throw new FileNotFoundException(fileName + " not found. Please make sure that " + fileName + " exists.");
		}
		
		return url.getPath().substring(1, url.getPath().length());
	}
}
