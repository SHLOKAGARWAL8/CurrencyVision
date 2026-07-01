package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {

    private static final String DATA_FOLDER = "data";
    private static final String HISTORY_FILE = DATA_FOLDER + "/history.csv";
    private static final String FAVORITES_FILE = DATA_FOLDER + "/favorites.csv";
    private static final String SETTINGS_FILE = DATA_FOLDER + "/settings.properties";
    private static final String CACHE_FILE = DATA_FOLDER + "/cachedRates.json";

    public FileManager() {
        createFiles();
    }

    private void createFiles() {

        try {

            Files.createDirectories(Paths.get(DATA_FOLDER));

            createIfMissing(HISTORY_FILE);
            createIfMissing(FAVORITES_FILE);
            createIfMissing(SETTINGS_FILE);
            createIfMissing(CACHE_FILE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createIfMissing(String fileName) throws IOException {

        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void saveHistory(String record) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(HISTORY_FILE, true))) {

            writer.write(record);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadHistory() {

        List<String> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(HISTORY_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                history.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return history;
    }

    public void saveFavorite(String pair) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FAVORITES_FILE, true))) {

            writer.write(pair);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadFavorites() {

        List<String> favorites = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(FAVORITES_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                favorites.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return favorites;
    }

    public String getSettingsFile() {
        return SETTINGS_FILE;
    }

    public String getCacheFile() {
        return CACHE_FILE;
    }
}