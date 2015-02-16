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
	private List<String> lines;
	
	public FileOps(String fileName) 
	{
		this.fileName = fileName;
		this.lines = new ArrayList<String>();
	}
	
	/*Read data from file*/
	public List<String> readLinesFromFile() 
	{
		Path path = Paths.get(getPath(fileName));	
		try 
		{
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} 
			
		//Exception -message and stop if file not found
		catch (FileNotFoundException fnf)
		{
			 System.out.println(fileName + " not found ");
			 System.exit(0);
		 }	
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return lines;
	}
	
	/*Write data to a text file*/
	public static void writeToFile(String fileName, String textToWrite) 
	{
		fileName += "_";
		fileName += new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		fileName += ".txt";
		
		Writer writer = null;

		try 
		{
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		    writer.write(textToWrite);
		   
		    Helpers.println("success");
		}
		//Exception -message and stop if file not found
		catch (FileNotFoundException fnf)
		{
			System.out.println(fileName + " not found ");
			System.exit(0);
		}	
		catch (IOException ex) 
		{
		  Helpers.println(ex.getMessage());
		} 
		finally 
		{
		   
			try 
			{
			   writer.close();
		   } 
			catch (Exception ex) 
			{
			   Helpers.println(ex.getMessage());
		   }
		}
	}
	/*Return path of the file*/
	public String getPath(String fileName) {
		URL url = Main.class.getClassLoader().getResource(fileName);
		return url.getPath().substring(1, url.getPath().length());
	}
}
