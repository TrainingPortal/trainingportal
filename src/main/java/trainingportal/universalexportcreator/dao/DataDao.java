package trainingportal.universalexportcreator.dao;

import java.util.List;

public interface DataDao {

    List<List> findFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName);

    List<List> findFieldsFromTableWithCondition(List<String> fields, String tableName, String fileName, String labelName, String whereCondition);

    List<List> findMultiFieldsFromTables(List<String> fields, String sql, String fileName, String labelName);
}
