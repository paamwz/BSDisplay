package application.Methods;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DispRate{
	
	public void setRateLabel(Label[] labelFields, double[] inputData,double[] operationRates, double[] procurementRates, TextField[] textFieldsIncome) {
		for(int i = 0; i < textFieldsIncome.length; i++) {
			labelFields[i].setText(String.format("%.1f%%", operationRates[i]/100));
		}
		for(int i = textFieldsIncome.length; i < inputData.length; i++) {
			labelFields[i].setText(String.format("%.1f%%", procurementRates[i]/100));
		}
	}
}