package application.Methods;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import javafx.scene.control.TextField;

public class WriteContents{
	public void exportCSV(TextField saveFolderName, TextField corpName, Path path, String[] corpLabels, String[] titleIndex, String[] priceParameters, String[] rateOutPuts) {
		String fileName;
		if(saveFolderName.getText().isEmpty() && corpName.getText().isEmpty()) {
			fileName = "SampleCorp-BS";
		} else if(saveFolderName.getText().isEmpty()) {
			fileName = corpName.getText() + "-BS";
		} else {
			fileName = saveFolderName.getText();
		}
		Path filePath = path.resolve(fileName + ".csv");
		try (Writer writer = new BufferedWriter(
		        new OutputStreamWriter(
		            new FileOutputStream(filePath.toFile()), StandardCharsets.UTF_8
		        ))) {

			// BOMを出力
		    writer.write('\uFEFF'); 

		    writer.write(String.join(",", corpLabels[0], corpLabels[1]) + System.lineSeparator());

		    for (int i = 0; i < titleIndex.length; i++) {
		        if(i < 6) {
		        	writer.write(String.join(",", titleIndex[i], priceParameters[i], rateOutPuts[i]) + System.lineSeparator());		        	
		        } else if (i >= 6) {
		        	writer.write(String.join(",", titleIndex[i], priceParameters[i]) + System.lineSeparator());
		        }
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}