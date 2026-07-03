package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ConversionHistory;
import model.CurrencyData;
import service.ApiService;
import service.ChartManager;
import service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainController {

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyBox;

    @FXML
    private ComboBox<String> toCurrencyBox;

    @FXML
    private ComboBox<String> periodBox;

    @FXML
    private Button convertButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button swapButton;

    @FXML
    private Button themeButton;

    @FXML
    private Button clearHistoryButton;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Label resultLabel;

    @FXML
    private Label exchangeRateLabel;

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label connectionStatusLabel;

    @FXML
    private LineChart<String, Number> trendChart;

    @FXML
    private TableView<ConversionHistory> historyTable;

    @FXML
    private TableColumn<ConversionHistory, String> timeColumn;

    @FXML
    private TableColumn<ConversionHistory, String> fromColumn;

    @FXML
    private TableColumn<ConversionHistory, String> toColumn;

    @FXML
    private TableColumn<ConversionHistory, String> amountColumn;

    @FXML
    private TableColumn<ConversionHistory, String> resultColumn;

    private final ApiService apiService = new ApiService();
    private final CurrencyService currencyService = new CurrencyService();
    private final ChartManager chartManager = new ChartManager();

    private final ObservableList<ConversionHistory> historyList =
            FXCollections.observableArrayList();

    private boolean darkMode = false;

    @FXML
    public void initialize() {

        loadCurrencies();

        loadPeriods();

        configureTable();

        loadingIndicator.setVisible(false);

        resultLabel.setText("Ready");

        exchangeRateLabel.setText("--");

        lastUpdatedLabel.setText("--");

        statusLabel.setText("Waiting");

        connectionStatusLabel.setText("Ready");

        convertButton.setOnAction(e -> convertCurrency());

        refreshButton.setOnAction(e -> refreshRates());

        swapButton.setOnAction(e -> swapCurrencies());

        themeButton.setOnAction(e -> toggleTheme());

        clearHistoryButton.setOnAction(e -> clearHistory());

        fromCurrencyBox.setOnAction(e -> autoConvert());

        toCurrencyBox.setOnAction(e -> autoConvert());

        periodBox.setOnAction(e -> loadGraph());
    }

    private void loadCurrencies() {

    ObservableList<String> currencies = FXCollections.observableArrayList(

            "🇺🇸 USD",
            "🇪🇺 EUR",
            "🇮🇳 INR",
            "🇬🇧 GBP",
            "🇯🇵 JPY",
            "🇨🇦 CAD",
            "🇦🇺 AUD",
            "🇨🇭 CHF",
            "🇨🇳 CNY",
            "🇸🇬 SGD"

    );

    fromCurrencyBox.setItems(currencies);
    toCurrencyBox.setItems(currencies);

    fromCurrencyBox.setValue("🇺🇸 USD");
    toCurrencyBox.setValue("🇮🇳 INR");
}

    private void loadPeriods() {

        periodBox.setItems(
                FXCollections.observableArrayList(
                        "7 Days",
                        "30 Days",
                        "90 Days",
                        "1 Year"
                )
        );

        periodBox.getSelectionModel().select("30 Days");

    }

    private void configureTable() {

        timeColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        fromColumn.setCellValueFactory(
                new PropertyValueFactory<>("from"));

        toColumn.setCellValueFactory(
                new PropertyValueFactory<>("to"));

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("amount"));

        resultColumn.setCellValueFactory(
                new PropertyValueFactory<>("result"));

        historyTable.setItems(historyList);

    }

    private void autoConvert() {

        if (amountField.getText().isBlank()) {
            return;
        }

        convertCurrency();

    }

        private void convertCurrency() {

        try {

            if (fromCurrencyBox.getValue() == null ||
                    toCurrencyBox.getValue() == null) {
                return;
            }

            String amountText = amountField.getText().trim();

            if (amountText.isEmpty()) {
                return;
            }

            loadingIndicator.setVisible(true);

            double amount = Double.parseDouble(amountText);

            String from = fromCurrencyBox.getValue().substring(5, 8);

            String to = toCurrencyBox.getValue().substring(5, 8);

            BigDecimal rate =
        currencyService.getExchangeRate(from, to);

BigDecimal convertedAmount =
        currencyService.convert(
                BigDecimal.valueOf(amount),
                rate
        );

            resultLabel.setText(
                    String.format("%.2f", convertedAmount.doubleValue())
            );


            exchangeRateLabel.setText(rate.toPlainString());

            lastUpdatedLabel.setText(
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy HH:mm:ss"
                            )
                    )
            );

            statusLabel.setText("Success");

            addHistory(
                    from,
                    to,
                    amount,
                    convertedAmount.doubleValue()
            );

            loadGraph();

        } catch (Exception e) {

            statusLabel.setText("Failed");

            resultLabel.setText("Error");

            e.printStackTrace();

        } finally {

            loadingIndicator.setVisible(false);

        }

    }

    private void swapCurrencies() {

        String temp = fromCurrencyBox.getValue();

        fromCurrencyBox.setValue(
                toCurrencyBox.getValue());

        toCurrencyBox.setValue(temp);

        autoConvert();

    }

    private void refreshRates() {

        autoConvert();

    }

    private void addHistory(String from,
                            String to,
                            double amount,
                            double result) {

        String time =
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd MMM yyyy HH:mm:ss"
                        )
                );

        historyList.add(
                0,
                new ConversionHistory(
                        time,
                        from,
                        to,
                        String.format("%.2f", amount),
                        String.format("%.2f", result)
                )
        );

    }

    private void clearHistory() {

        historyList.clear();

    }

        private void loadGraph() {

        if (fromCurrencyBox.getValue() == null ||
                toCurrencyBox.getValue() == null) {
            return;
        }

        int days;

        switch (periodBox.getValue()) {

            case "7 Days":
                days = 7;
                break;

            case "90 Days":
                days = 90;
                break;

            case "1 Year":
                days = 365;
                break;

            default:
                days = 30;
                break;
        }

        String from = fromCurrencyBox.getValue().substring(5, 8);

        String to = toCurrencyBox.getValue().substring(5, 8);

        chartManager.loadTrend(
                trendChart,
                from,
                to,
                days
        );

    }

    private void toggleTheme() {

        darkMode = !darkMode;

        if (darkMode) {

            themeButton.setText("Light Mode");

        } else {

            themeButton.setText("Dark Mode");

        }

    }

}