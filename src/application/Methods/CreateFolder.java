package application.Methods;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.scene.control.TextField;

public class CreateFolder{
	public Path createFolder(TextField saveFolderName, TextField corpName, Path path) {
		String folderName;
		if(saveFolderName.getText().isEmpty() && corpName.getText().isEmpty()) {
			folderName = "SampleCorp-BS";
		} else if(saveFolderName.getText().isEmpty()) {
			folderName = corpName.getText() + "-BS";
		} else {
			folderName = saveFolderName.getText();
		}
		
		
		Path folderPath = path.resolve(folderName);
		try {
			Files.createDirectory(folderPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return folderPath;
	}
	
}