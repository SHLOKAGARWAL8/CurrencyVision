package util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class ChartManager {


    public void clearChart(LineChart<String, Number> chart) {

        if (chart != null) {
            chart.getData().clear();
        }
    }

    /**
     * Displays historical exchange rates on the graph.
     *
     * Key   -> Date
     * Value -> Exchange Rate
     */
    public void loadTrend(LineChart<String, Number> chart,
                          Map<String, Double> history,
                          String title) {

        if (chart == null || history == null || history.isEmpty()) {
            return;
        }

        chart.getData().clear();

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.setName(title);

        for (Map.Entry<String, Double> entry : history.entrySet()) {

            series.getData().add(
                    new XYChart.Data<>(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        chart.getData().add(series);

        chart.setAnimated(true);

        chart.setCreateSymbols(true);

        chart.setLegendVisible(true);
    }
}