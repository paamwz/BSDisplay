package application.Methods;

import javafx.scene.control.TextField;

public class RateCalc{
	
	//incomeの各割合を計算
	public double[] calcOperationRate(double[] inputData, TextField[] operationList) {
		double operationSum = 0.0;
		for(int i = 0; i < operationList.length; i++) {
			operationSum += inputData[i];
		} 
		double[] operationRates = {inputData[0], inputData[1], 0.0 ,0.0, 0.0};
		
		for(int i = 0; i < operationList.length ; i++) {
			operationRates[i] = operationRates[i]*10000/operationSum;
		}
		return operationRates;
	}
	
	
	
	//expensesの各割合を計算
	public double[] calcProcurementRate(double[] inputData, TextField[] operationeList) {
		double procurementsSum = 0.0;
		for(int i = operationeList.length; i < inputData.length; i++) {
			procurementsSum += inputData[i];			
		}
		
		double[] procurementRates = { 0.0, 0.0, inputData[2], inputData[3], inputData[4]};
		for(int i = operationeList.length; i < inputData.length; i++ ) {
			procurementRates[i] = procurementRates[i]*10000/procurementsSum;
		}
		return procurementRates;
	}
	
	
}