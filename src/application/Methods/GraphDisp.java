package application.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class GraphDisp {

    private StackedBarChart<String, Number> chart;

    public GraphDisp(StackedBarChart<String, Number> chart) {
        this.chart = chart;
    }

    public void updateChart(double[] inputNumberList, TextField corpName) {
        final String operation = "運用";
        final String procurement = "調達";
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        // グラフをクリア
        chart.getData().clear();
        List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();

        String[] labels = {
        		"流動資産", "固定資産", "流動負債",
        		"固定負債", "純資産"
        };
        // 9つのシリーズを作成
        for (int i = 0; i < labels.length; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("series" + String.valueOf(i));
            seriesList.add(series);
        }

        // 支出と収入のデータをセット
        Double[] procurementList = {0.0, 0.0, inputNumberList[2], inputNumberList[3], inputNumberList[4]};
        Double[] operationList = {inputNumberList[0], inputNumberList[1], 0.0, 0.0, 0.0};

        // タイトルと軸ラベルの設定
        chart.setTitle(corpName.getText()+"-BS");
        xAxis.setLabel("Category");
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList(operation, procurement)));
        yAxis.setLabel("Value");

        // ラベル

        // シリーズにデータを追加
        for (int i = 0; i < seriesList.size(); i++) {
            XYChart.Series<String, Number> series = seriesList.get(i);
            series.setName(labels[i]);
            series.getData().add(new XYChart.Data<>(operation, operationList[i]));
            series.getData().add(new XYChart.Data<>(procurement, procurementList[i]));
        }

        // グラフに追加（逆順で追加）
        for (int i = seriesList.size() - 1; i >= 0; i--) {
            chart.getData().add(seriesList.get(i));
        }
    }
}