package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyBox;

    @FXML
    private ComboBox<String> toCurrencyBox;

    @FXML
    private Button convertButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button themeButton;

    @FXML
    private Label resultLabel;

    @FXML
    private LineChart<String, Number> trendChart;

    @FXML
    public void initialize() {

        fromCurrencyBox.getItems().addAll(
                "USD",
                "EUR",
                "GBP",
                "INR",
                "JPY",
                "AUD",
                "CAD"
        );

        toCurrencyBox.getItems().addAll(
                "USD",
                "EUR",
                "GBP",
                "INR",
                "JPY",
                "AUD",
                "CAD"
        );

        fromCurrencyBox.setValue("USD");
        toCurrencyBox.setValue("INR");

        resultLabel.setText("Ready to Convert");
    }
}