package service;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import model.ExchangeRateData;

import java.util.List;

public class ChartManager {

    private final ApiService apiService;

    public ChartManager() {

        apiService = new ApiService();

    }

    /**
     * Loads historical exchange-rate data
     * and displays it on the LineChart.
     */
    public void loadTrend(LineChart<String, Number> chart,
                          String from,
                          String to,
                          int days) {

        chart.getData().clear();

        List<ExchangeRateData> data =
                apiService.getTrendData(from, to, days);

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        for (ExchangeRateData point : data) {

            series.getData().add(

                    new XYChart.Data<>(

                            point.getDate(),

                            point.getRate()

                    )

            );

        }

        chart.getData().add(series);

    }

}