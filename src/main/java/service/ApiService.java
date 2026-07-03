package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.ExchangeRateData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    private static final String BASE_URL = "https://api.frankfurter.app";

    /**
     * Returns the latest exchange rate.
     */
    public BigDecimal getExchangeRate(String from, String to) {

        try {

            String json = sendRequest(
                    BASE_URL
                            + "/latest?from="
                            + from
                            + "&to="
                            + to);

            if (json == null) {
                return null;
            }

            JsonObject root =
                    JsonParser.parseString(json).getAsJsonObject();

            JsonObject rates =
                    root.getAsJsonObject("rates");

            if (rates == null || !rates.has(to)) {
                return null;
            }

            return rates.get(to).getAsBigDecimal();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    /**
     * Returns raw historical JSON.
     */
    public String getHistoricalRates(String startDate,
                                     String endDate,
                                     String from,
                                     String to) {

        return sendRequest(
                BASE_URL + "/"
                        + startDate
                        + ".."
                        + endDate
                        + "?from="
                        + from
                        + "&to="
                        + to);

    }

    /**
     * Returns historical exchange-rate data for graphs.
     */
    public List<ExchangeRateData> getTrendData(String from,
                                               String to,
                                               int days) {

        List<ExchangeRateData> trendData =
                new ArrayList<>();

        try {

            LocalDate endDate = LocalDate.now();

            LocalDate startDate =
                    endDate.minusDays(days);

            String url =
                    BASE_URL + "/"
                            + startDate
                            + ".."
                            + endDate
                            + "?from="
                            + from
                            + "&to="
                            + to;

            String json =
                    sendRequest(url);

            if (json == null) {
                return trendData;
            }

            JsonObject root =
                    JsonParser.parseString(json)
                            .getAsJsonObject();

            JsonObject rates =
                    root.getAsJsonObject("rates");

            for (String date : rates.keySet()) {

                JsonObject day =
                        rates.getAsJsonObject(date);

                BigDecimal rate =
                        day.get(to).getAsBigDecimal();

                trendData.add(
                        new ExchangeRateData(
                                date,
                                rate
                        )
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return trendData;

    }

    /**
     * Sends HTTP GET request.
     */
    private String sendRequest(String urlString) {

        try {

            URL url =
                    URI.create(urlString).toURL();

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);

            connection.setReadTimeout(5000);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));

            StringBuilder response =
                    new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                response.append(line);

            }

            reader.close();

            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}