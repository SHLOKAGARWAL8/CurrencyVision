package model;

public class ConversionHistory {

    private String time;
    private String from;
    private String to;
    private String amount;
    private String result;

    public ConversionHistory(String time,
                             String from,
                             String to,
                             String amount,
                             String result) {

        this.time = time;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAmount() {
        return amount;
    }

    public String getResult() {
        return result;
    }

}