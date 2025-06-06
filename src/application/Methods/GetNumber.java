package application.Methods;

import javafx.scene.control.TextField;

public class GetNumber {

    public double[] getDataFromTextFields(TextField[] textFields) {
        double[] data = new double[textFields.length];

        for (int i = 0; i < textFields.length; i++) {
            String text = textFields[i].getText();
            if (text.isEmpty()) {
                text = "0";
            }
            // 数値に変換
            data[i] = Double.parseDouble(text.replace(",", ""));
        }

        return data;
    }
}
