#  CurrencyVision

<div align="center">

# 💱 CurrencyVision

### Real-Time Currency Converter built with JavaFX

<p>

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![JavaFX](https://img.shields.io/badge/JavaFX-Desktop-blue?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
![API](https://img.shields.io/badge/API-Frankfurter-success?style=for-the-badge)
![MVC](https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge)

</p>

<p>

![Stars](https://img.shields.io/github/stars/SHLOKAGARWAL8/CurrencyVision?style=for-the-badge)
![Forks](https://img.shields.io/github/forks/SHLOKAGARWAL8/CurrencyVision?style=for-the-badge)
![Last Commit](https://img.shields.io/github/last-commit/SHLOKAGARWAL8/CurrencyVision?style=for-the-badge)
![Repo Size](https://img.shields.io/github/repo-size/SHLOKAGARWAL8/CurrencyVision?style=for-the-badge)

</p>

</div>

CurrencyVision is a modern JavaFX-based desktop application that provides real-time currency conversion using live exchange rates from the Frankfurter API. It features an intuitive user interface, historical exchange rate visualization, conversion history tracking, dark mode support, and robust input validation, making it a practical and user-friendly currency converter.

---

# Application Preview

![Home](screenshots/home.png)

---

# Features

- Real-time currency conversion
- Live exchange rates using Frankfurter API
- Historical exchange rate trend graph
- Currency swap functionality
- Dark Mode / Light Mode
- Conversion history
- Input validation
- Internet and API error handling
- Responsive JavaFX interface
- Clean MVC architecture

---

# Technologies Used

- Java 21
- JavaFX
- Maven
- Gson
- Frankfurter REST API
- CSS
- Git
- GitHub

---

# Project Structure

```text
CurrencyVision
│
├── src
│   ├── controller
│   ├── model
│   ├── service
│   ├── util
│   └── resources
│       ├── css
│       └── fxml
│
├── screenshots
│
├── data
│
├── pom.xml
├── README.md
└── LICENSE
```

---

# Installation

Clone the repository

```bash
git clone https://github.com/SHLOKAGARWAL8/CurrencyVision.git
```

Open the project in Visual Studio Code or IntelliJ IDEA.

Run the application

```bash
mvn clean javafx:run
```

---

# Screenshots

## Light Mode

![Light Mode](screenshots/light.png)

## Dark Mode

![Dark Mode](screenshots/dark.png)

## Exchange Rate Trend

![Trend Chart](screenshots/chart.png)

## Conversion History

![History](screenshots/history.png)

---

# How It Works

1. Enter the amount to convert.
2. Select the source currency.
3. Select the target currency.
4. Click **Convert**.
5. The application fetches the latest exchange rate from the Frankfurter API.
6. The converted amount is displayed instantly.
7. Historical exchange rate trends are shown using an interactive line chart.
8. Every successful conversion is stored in the conversion history.

---

# API Used

This project uses the free **Frankfurter API** for fetching live and historical exchange rates.

Website:

https://www.frankfurter.app/

---

# Future Improvements

- Offline exchange rate caching
- Favorite currency pairs
- Multiple exchange rate providers
- Export conversion history
- Multi-language support

---

# Author

**Shlok Agarwal**

GitHub:

https://github.com/SHLOKAGARWAL8

---

# License

This project is licensed under the MIT License.