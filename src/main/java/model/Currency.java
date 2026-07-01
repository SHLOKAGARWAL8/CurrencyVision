package model;

public class Currency {

    private String code;
    private String name;
    private String symbol;
    private String country;

    public Currency() {
    }

    public Currency(String code, String name, String symbol, String country) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}