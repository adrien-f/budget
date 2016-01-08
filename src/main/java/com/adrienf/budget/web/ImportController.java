package com.adrienf.budget.web;

import com.google.common.base.Splitter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
public class ImportController {
    @RequestMapping(value="/import", method= RequestMethod.POST)
    public @ResponseBody String doImport(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            ArrayList<CSVOperation> operations = new ArrayList<>();
            try {
                Reader in = new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-1"));
                Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').withHeader("Date", "Code", "Label", "Debit", "Credit", "Detail").parse(in);
                int line = 0;
                for (CSVRecord record : records) {
                    if (line > 4 && line < 160) {
                        operations.add(CSVOperation.fromCSVRecord(record));
                    }
                    line++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Hello";
    }
}
