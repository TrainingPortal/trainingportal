package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.export.Export;
import trainingportal.reports.dao.ReportsDao;
import trainingportal.reports.dao.exception.DataDaoExceptions;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsServiceExcel {

    @Autowired
    private ReportsDao reportsDao;

    @Autowired
    private Export export;

    public List<List> findFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = reportsDao.setSQL(fields, tableName, null);

        List<List> allCol = reportsDao.getFieldsFromTable(fields, fileName, labelName, sql);

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allCol, fields);

        //Use Export For our List<List> formed data
        useExport(fileName, labelName, allColWithName);

        return allCol;
    }

    public List<List> findFieldsFromTableWithCondition(List<String> fields, String tableName, String fileName, String labelName, String whereCondition) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = reportsDao.setSQL(fields, tableName, whereCondition);

        List<List> allCol = reportsDao.getFieldsFromTable(fields, fileName, labelName, sql);

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allCol, fields);

        //Use Export For our List<List> formed data
        useExport(fileName, labelName, allColWithName);

        return allCol;
    }

    public List<List> findMultiFieldsFromTables(List<String> fields, String sql, String fileName, String labelName) {

        List<List> allCol = reportsDao.getFieldsFromTable(fields, fileName, labelName, sql);

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allCol, fields);

        //Use Export For our List<List> formed data
        useExport(fileName, labelName, allColWithName);

        return allCol;
    }

    private void useExport(String fileName, String labelName, List<List> allCol) {
        export.exportDataToExcelGenerics(fileName, labelName, allCol);
    }

    private List<List> addNameToList(List<List> allCol, List<String> fields) {

        if (!(allCol.isEmpty()) && !(fields.isEmpty())) {

            List<List> finalAllCol = new ArrayList<>();
            List localCol;

            for (int i = 0; i < allCol.size(); i++) {
                localCol = new ArrayList();
                localCol.add(fields.get(i));
                for (int j = 0; j < allCol.get(i).size(); j++) {
                    localCol.add(allCol.get(i).get(j));
                }
                finalAllCol.add(localCol);
            }
            return finalAllCol;
        } else
            throw new DataDaoExceptions("Input list OR list's is Empty");
    }

}
