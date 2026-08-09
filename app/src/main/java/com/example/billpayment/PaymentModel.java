package com.example.billpayment;

public class PaymentModel {

    public String billType;
    public int amount;
    public String status;
    public String date;

    public PaymentModel(String billType, int amount, String status, String date) {
        this.billType = billType;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }
}
