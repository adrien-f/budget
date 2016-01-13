package com.adrienf.budget.web;

import com.adrienf.budget.domain.Operation;
import com.adrienf.budget.service.OperationRepository;
import com.adrienf.budget.service.importation.BPCEOperationsImporter;
import com.google.common.base.Splitter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private enum ImporterType {BPCE}

    @Autowired
    protected OperationRepository operationRepository;

    @RequestMapping(value="/import", method= RequestMethod.POST)
    public ResponseEntity doImport(@RequestParam("file") MultipartFile file, @RequestParam("importerType") ImporterType importerType) {
        ArrayList<Operation> operations;
        switch (importerType) {
            case BPCE:
                try {
                    Reader in = new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-1"));
                    BPCEOperationsImporter bpceOperationsImporter = new BPCEOperationsImporter();
                     operations = bpceOperationsImporter.parseOperations(in);
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
                }
                break;
            default:
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        operationRepository.save(operations);
        return new ResponseEntity(HttpStatus.OK);
    }
}
