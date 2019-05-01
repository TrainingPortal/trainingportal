package trainingportal.exporttest.service;

import java.util.List;

public interface DataService {

    List<List> getFieldsFromTable(List<String> fields, String tableName);

}
