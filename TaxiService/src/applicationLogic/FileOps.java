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

public class FileOps {
	private String fileName = "";
	private List<String> lines;
	
	public FileOps(String fileName) {
		this.fileName = fileName;
		this.lines = new ArrayList<String>();
	}
	
	public List<String> readLinesFromFile() {
		Path path = Paths.get(getPath(fileName));
		
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public static void writeToFile(String fileName, String textToWrite) {
		fileName += "_";
		fileName += new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		fileName += ".txt";
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		    writer.write(textToWrite);
		    
		    Helpers.println("success");
		} catch (IOException ex) {
		  Helpers.println(ex.getMessage());
		} finally {
		   try {
			   writer.close();
		   } catch (Exception ex) {
			   Helpers.println(ex.getMessage());
		   }
		}
	}
	
	public String getPath(String fileName) {
		URL url = Main.class.getClassLoader().getResource(fileName);
		return url.getPath().substring(1, url.getPath().length());
	}
}
