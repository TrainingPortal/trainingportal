package trainingportal.reports.service;

import trainingportal.reports.dao.exception.DataDaoExceptions;

import java.util.List;

public interface ReportsService {

    List<List> getFieldsFromTable(List<String> fields, String tableName) throws DataDaoExceptions;

    List<List> getFieldsFromTableWithCondition(List<String> fields, String tableName, String whereCondition) throws DataDaoExceptions;

    List<List> getMultiFieldsFromTables(List<String> fields, String sql) throws DataDaoExceptions;
}
