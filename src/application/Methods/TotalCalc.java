package application.Methods;

import javafx.scene.control.TextField;

public class TotalCalc{
	double totalAssets = 0.0;
	public double totalOperationCalc(double[] inputData, TextField[] textFieldlsOperation) {
		for(int i = 0; i < textFieldlsOperation.length; i++) {
			totalAssets += inputData[i];
		}
		return totalAssets;	
	}
	double totalLiabilities = 0.0;
	public double totalProcurementCalc(double[] inputData, TextField[] textFieldlOperation) {
		for(int i = textFieldlOperation.length; i < inputData.length; i++) {
			totalLiabilities += inputData[i];
		}
		return totalLiabilities;	
	}
}