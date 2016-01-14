package com.adrienf.budget.service.importation;

import com.adrienf.budget.domain.Operation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BPCEOperationsImporter implements OperationsImporter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Override
    public ArrayList<Operation> parseOperations(Object object) throws IOException {
        ArrayList<Operation> operations = new ArrayList<>();
        Reader in = (Reader) object;
        Iterable<CSVRecord> records = null;
        records = CSVFormat.EXCEL.withDelimiter(';').withHeader("Date", "Code", "Label", "Debit", "Credit", "Detail").parse(in);
        int line = 0;
        for (CSVRecord record : records) {
            if (line > 4 && line < 160) {
                operations.add(fromCSVRecord(record));
            }
            line++;
        }
        return operations;
    }

    private Operation fromCSVRecord(CSVRecord record) {
        BigDecimal amount;
        if (!record.get(3).isEmpty()) {
            amount = new BigDecimal(record.get(3).replace(",", "."));
        } else {
            amount = new BigDecimal(record.get(4).replace(",", "."));
        }

        LocalDate parsedDate = LocalDate.parse(record.get("Date"), formatter);
        LocalDateTime parsedLocalDateTime = LocalDateTime.of(parsedDate.getYear(), parsedDate.getMonth(), parsedDate.getDayOfMonth(), 0, 0);

        String label = record.get("Label");
        if (label.contains("FACT  ")) {
            String[] facts = label.split("FACT ");
            parsedLocalDateTime = LocalDateTime.of(Integer.parseInt("20" + facts[1].substring(4, 6)), Integer.parseInt(facts[1].substring(2, 4)), Integer.parseInt(facts[1].substring(0, 2)), 0, 0);
        }
        return new Operation(null, Money.of(CurrencyUnit.EUR, amount), parsedLocalDateTime);
    }

}
