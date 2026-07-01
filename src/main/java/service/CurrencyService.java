package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyService {

    private final ApiService apiService;

    public CurrencyService() {
        apiService = new ApiService();
    }

    /**
     * Returns the JSON response from the API.
     */
    public String getExchangeRate(String from, String to) {
        return apiService.getLatestRate(from, to);
    }

    /**
     * Converts an amount using a given exchange rate.
     */
    public BigDecimal convert(BigDecimal amount, BigDecimal exchangeRate) {

        if (amount == null || exchangeRate == null) {
            return BigDecimal.ZERO;
        }

        return amount.multiply(exchangeRate)
                     .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Formats currency values.
     */
    public String formatAmount(BigDecimal amount) {

        if (amount == null) {
            return "0.00";
        }

        return amount.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    /**
     * Checks whether the entered amount is valid.
     */
    public boolean isValidAmount(String value) {

        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        try {
            BigDecimal amount = new BigDecimal(value);

            return amount.compareTo(BigDecimal.ZERO) > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}