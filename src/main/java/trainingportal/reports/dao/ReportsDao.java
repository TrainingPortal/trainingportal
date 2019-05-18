package trainingportal.reports.dao;

import trainingportal.reports.dao.exception.DataDaoExceptions;

import java.util.List;

public interface ReportsDao {

    List<List> getFieldsFromTable(List<String> fields, String fileName, String labelName, String sql);

    default String setSQL(List<String> fields, String tableName, String whereCondition) {

        if (!fields.isEmpty()) {
            String sql = "SELECT ";

            //Here we form the fields in sql query
            for (int i = 0; i < fields.size(); i++) {
                sql = sql + fields.get(i);
                if (i != fields.size() - 1)
                    sql = sql + ", ";
            }

            if (whereCondition != null) {
                sql = sql + " FROM " + tableName + " WHERE " + whereCondition;
            } else {
                sql = sql + " FROM " + tableName;
            }

            return sql;
        } else
            throw new DataDaoExceptions("Input list is Empty");
    }
}
