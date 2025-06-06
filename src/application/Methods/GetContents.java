package application.Methods;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GetContents{
	String[] textFieldTexts;
	public String[] getTextFieldTexts(TextField[] textFields) {
		for(int i = 0 ; i < textFields.length; i++) {
			textFieldTexts[i] = textFields[i].getText();
		} return textFieldTexts;
	}
	
	String[] labelTexts;
	public String[] getLabelTexts(Label[] labels) {
		for(int i = 0; i < labels.length; i++) {
			labelTexts[i] = labels[i].getText();
		} return labelTexts;
	} 
}