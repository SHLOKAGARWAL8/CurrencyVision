package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyService {

    private final ApiService apiService;

    public CurrencyService() {
        apiService = new ApiService();
    }

    /**
     * Gets the latest exchange rate.
     */
    public BigDecimal getExchangeRate(String from, String to) {

        return apiService.getExchangeRate(from, to);

    }

    /**
     * Converts currency.
     */
    public BigDecimal convert(BigDecimal amount,
                              BigDecimal exchangeRate) {

        if (amount == null || exchangeRate == null) {
            return BigDecimal.ZERO;
        }

        return amount.multiply(exchangeRate)
                     .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Formats the converted amount.
     */
    public String formatAmount(BigDecimal amount) {

        if (amount == null) {
            return "0.00";
        }

        return amount.setScale(2, RoundingMode.HALF_UP)
                     .toPlainString();
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

        } catch (Exception e) {

            return false;

        }

    }

}