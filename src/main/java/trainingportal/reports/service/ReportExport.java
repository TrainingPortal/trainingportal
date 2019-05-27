package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.export.Export;
import trainingportal.export.exception.ExportToExcelException;

import java.util.List;

@Service
public class ReportExport {

    @Autowired
    private Export export;

    public void useExport(String fileName, String labelName, List<List> allCol) throws ExportToExcelException {
        export.exportDataToExcelGenerics(fileName, labelName, allCol);
    }

}
