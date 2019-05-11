package trainingportal.universalexportcreator.service;

import java.util.List;

public interface DataService {

    List<List> getFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName);

    List<List> getFieldsFromTableWithCondition(List<String> fields, String tableName, String fileName, String labelName, String whereCondition);

    List<List> getMultiFieldsFromTables(List<String> fields, String sql, String fileName, String labelName);

}
