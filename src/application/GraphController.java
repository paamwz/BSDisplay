package application;

import java.io.File;
import java.nio.file.Path;

import application.Methods.CreateFolder;
import application.Methods.DispRate;
import application.Methods.GetNumber;
import application.Methods.GraphDisp;
import application.Methods.RateCalc;
import application.Methods.SaveFileName;
import application.Methods.SaveGraph;
import application.Methods.SaveTable;
import application.Methods.TotalCalc;
import application.Methods.WriteContents;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class GraphController {
	
	@FXML
	public Label corpNameIndex;
	@FXML
	public TextField corpName;
	
	@FXML 
	public Label curAssetsIndex;
    @FXML
    public TextField curAssets;
    @FXML
    public Label fixAssetsIndex;
    @FXML
    public TextField fixAssets;
    @FXML
    public Label curLiaIndex;
    @FXML
    public TextField curLia;
    @FXML
    public Label fixLiaIndex;
    @FXML
    public TextField fixLia;
    @FXML
    public Label netWorthIndex;
    @FXML
    public TextField netWorth;
    
    @FXML
    public Label totalOpeIndex;
    @FXML
    public Label totalOpeLabel;
    @FXML
    public Label totalProcureIndex;
    @FXML
    public Label totalProcureLabel;
    
    
    @FXML
    public Label curAssetsRate;
    @FXML
    public Label fixAssetsRate;
    @FXML
    public Label curLiasRate;
    @FXML
    public Label fixLiasRate;
    @FXML
    public Label netWorthRate;
    
    
    @FXML
    public TextField saveFolderName;
    
    
    
    

    @FXML
    private StackedBarChart<String, Number> controlledGraph;
    
    @FXML
    void onGetButtonClick(ActionEvent event) {
        // TextField 配列を作成
        TextField[] textFieldsOperation = {
        		curAssets, 
        		fixAssets, 
        };
        
        TextField[] textFields = {
        		curAssets, 
        		fixAssets, 
        		curLia, 
        		fixLia, 
        		netWorth, 
        };
        
        Label[] labelFields = {
        		curAssetsRate,
        		fixAssetsRate,
        		curLiasRate,
        		fixLiasRate,
        		netWorthRate,
        };
        // GraphDataHandler を使ってデータを取得
        GetNumber getNumber = new GetNumber();
        double[] inputData = getNumber.getDataFromTextFields(textFields);
        // TotalCalcを使ってRevenueとCostの総額をそれぞれ計算
        TotalCalc totalCalc = new TotalCalc();
        double totalAssets = totalCalc.totalOperationCalc(inputData, textFieldsOperation);
        double totalLiabilities = totalCalc.totalProcurementCalc(inputData, textFieldsOperation);
        totalOpeLabel.setText(String.valueOf(totalAssets));
        totalProcureLabel.setText(String.valueOf(totalLiabilities));
        
        // ChartDataUpdater を使ってグラフを更新
        GraphDisp graphDisp = new GraphDisp(controlledGraph);
        graphDisp.updateChart(inputData, corpName);
        
        // RateCalcを使って割合の計算
        
        RateCalc rateCalc = new RateCalc();
        double[] operationRates = rateCalc.calcOperationRate(inputData, textFieldsOperation);
        double[] procurementRates = rateCalc.calcProcurementRate(inputData, textFieldsOperation);
        //incomeとexpensesの各割合を計算してラベルに出力(小数第3位を四捨五入)
        DispRate dispRate = new DispRate();
        dispRate.setRateLabel(labelFields, inputData, operationRates, procurementRates, textFieldsOperation);
    }
    @FXML
    void onSaveButtonClick(ActionEvent event) {
    	String[] corpLabels = {corpNameIndex.getText(), corpName.getText()};
    	String[] titleIndex = {
    			"項目", 
    			curAssetsIndex.getText(), 
    			fixAssetsIndex.getText(), 
    			curLiaIndex.getText(),
    			fixLiaIndex.getText(),
    			netWorthIndex.getText(),
    			totalOpeIndex.getText(),
    			totalProcureIndex.getText()
    			};
    	String[] priceParameters = {
    			"金額",
    			curAssets.getText(),
    			fixAssets.getText(),
    			curLia.getText(),
    			fixLia.getText(),
    			netWorth.getText(),
    			totalOpeLabel.getText(),
    			totalProcureLabel.getText()
    			};
    	String[] rateOutPuts = {
    			"割合",
    			curAssetsRate.getText(),
    			fixAssetsRate.getText(),
    			curLiasRate.getText(),
    			fixLiasRate.getText(),
    			netWorthRate.getText(),
    			};
    	System.out.println("e");
    	try {
    		//pathに
    		DirectoryChooser directoryChooser = new DirectoryChooser();
    		directoryChooser.setTitle("出力先のファイルを選択");
    		Path choosedFolder = directoryChooser.showDialog(corpName.getScene().getWindow()).toPath();
    		CreateFolder createFolder = new CreateFolder();
    		Path folderPath = createFolder.createFolder(saveFolderName, corpName, choosedFolder);
    		SaveFileName saveFileName = new SaveFileName();
    		//saveFileNameという名前でグラフ保存
    		File file = saveFileName.saveFileName(folderPath, saveFolderName, corpName);
    		SaveGraph saveGraph = new SaveGraph();
    		saveGraph.saveChartAsImage(controlledGraph, file);    
    		SaveTable saveTable = new SaveTable();
    		saveTable.saveTableAsCSV(folderPath, file, saveFolderName, corpName);
    		WriteContents writeContents = new WriteContents();
    		writeContents.exportCSV(saveFolderName, corpName, folderPath, corpLabels, titleIndex, priceParameters, rateOutPuts);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}