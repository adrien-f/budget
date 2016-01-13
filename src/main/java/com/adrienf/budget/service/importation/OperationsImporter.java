package com.adrienf.budget.service.importation;

import com.adrienf.budget.domain.Operation;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public interface OperationsImporter {
    public ArrayList<Operation> parseOperations(Object object) throws IOException;
}
