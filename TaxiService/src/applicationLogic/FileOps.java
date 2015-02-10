package applicationLogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOps {
	private String fileName = "";
	private List<String> lines;
	
	public FileOps(String fileName) {
		this.fileName = fileName;
		this.lines = new ArrayList<String>();
	}
	
	public List<String> readLinesFromFile() {
		Path path = Paths.get(fileName);
		
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
}
