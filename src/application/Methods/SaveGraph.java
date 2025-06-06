package application.Methods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.image.WritableImage;

public class SaveGraph{

    public void saveChartAsImage(StackedBarChart<String, Number> chart, File file) {
        // ① キャプチャ用画像（WritableImage）を作成
        WritableImage image = chart.snapshot(new SnapshotParameters(), null);

        // ② WritableImage → BufferedImage に変換
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        try {
            // ③ ImageIO を使って PNG 形式で保存
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace(); // エラー処理
        }
    }
}