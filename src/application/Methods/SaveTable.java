package application.Methods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.scene.control.TextField;

public class SaveTable{
	
	public void saveTableAsCSV(Path path, File file, TextField saveFolderName, TextField corpName) {
		try {
			String fileName;
			if(saveFolderName.getText().isEmpty() && corpName.getText().isEmpty()) {
				fileName = "SampleCorp-BS";
			} else if(saveFolderName.getText().isEmpty()) {
				fileName = corpName.getText() + "-BS";
			} else {
				fileName = saveFolderName.getText();
			}
			Path filePath = path.resolve(fileName + ".csv");
			Files.createFile(filePath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}