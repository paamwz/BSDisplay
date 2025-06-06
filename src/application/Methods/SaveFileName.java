package application.Methods;

import java.io.File;
import java.nio.file.Path;

import javafx.scene.control.TextField;

public class SaveFileName{
	public File saveFileName(Path path, TextField saveFolderName, TextField corpName) {
		String filename;
		if(saveFolderName.getText().isEmpty() && corpName.getText().isEmpty()) {
			filename = "SampleCorp-BS";
		} else if(saveFolderName.getText().isEmpty()) {
			filename = corpName.getText() + "-BS";
		} else {
			filename = saveFolderName.getText();
		}
		File file = path.resolve(filename + ".png").toFile();	
		return file;
	}
}