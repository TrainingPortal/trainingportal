package trainingportal.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.reports.dao.ReportsDao;

import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private ReportsDao reportsDao;

    @Override
    public List<List> getFieldsFromTable(List<String> fields, String tableName) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = reportsDao.setSQL(fields, tableName, null);

        return reportsDao.getFieldsFromTable(fields, sql);
    }

    @Override
    public List<List> getFieldsFromTableWithCondition(List<String> fields, String tableName, String whereCondition) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = reportsDao.setSQL(fields, tableName, whereCondition);

        return reportsDao.getFieldsFromTable(fields, sql);
    }

    @Override
    public List<List> getMultiFieldsFromTables(List<String> fields, String sql) {
        return reportsDao.getFieldsFromTable(fields, sql);
    }
}
