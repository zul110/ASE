package applicationLogic;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
		Path path = Paths.get(getPath(fileName));
		
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public String getPath(String fileName) {
		URL url = Main.class.getClassLoader().getResource(fileName);
		return url.getPath().substring(1, url.getPath().length());
	}
}
