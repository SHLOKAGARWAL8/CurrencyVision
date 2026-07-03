package model;

import java.math.BigDecimal;

public class ExchangeRateData {

    private final String date;
    private final BigDecimal rate;

    public ExchangeRateData(String date, BigDecimal rate) {
        this.date = date;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }

}