package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ApiService {

    private static final String BASE_URL = "https://api.frankfurter.app";

    public String getLatestRates() {

        return sendRequest(BASE_URL + "/latest");
    }

    public String getLatestRate(String from, String to) {

        return sendRequest(BASE_URL +
                "/latest?from=" + from +
                "&to=" + to);
    }

    public String getHistoricalRates(String startDate,
                                     String endDate,
                                     String from,
                                     String to) {

        return sendRequest(BASE_URL + "/"
                + startDate + ".." + endDate
                + "?from=" + from
                + "&to=" + to);
    }

    private String sendRequest(String urlString) {

        try {

            URL url = URI.create(urlString).toURL();

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);

            connection.setReadTimeout(5000);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));

            StringBuilder response = new StringBuilder();

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