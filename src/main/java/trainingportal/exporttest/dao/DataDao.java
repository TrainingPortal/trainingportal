package trainingportal.exporttest.dao;

import java.util.List;

public interface DataDao {

    List<List> findFieldsFromTable(List<String> fields, String tableName);

}
