package com.adrienf.budget.web;

import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class CSVOperation {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    private LocalDate date;
    private String code;
    private String label;
    private BigDecimal amount;
    private String detail;

    public CSVOperation(LocalDate date, String code, String label, BigDecimal amount, String detail) {
        this.date = date;
        this.code = code;
        this.label = label;
        this.amount = amount;
        this.detail = detail;
    }

    public static CSVOperation fromCSVRecord(CSVRecord record) {
        BigDecimal amount;
        if (!record.get(3).isEmpty()) {
            amount = new BigDecimal(record.get(3).replace(",", "."));
        } else {
            amount = new BigDecimal(record.get(4).replace(",", "."));
        }
        return new CSVOperation(
                LocalDate.parse(record.get("Date"), formatter),
                record.get("Code"),
                record.get("Label"),
                amount,
                record.get("Detail"));
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
