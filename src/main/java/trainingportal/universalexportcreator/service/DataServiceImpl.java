package trainingportal.universalexportcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.universalexportcreator.dao.DataDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    DataDaoImpl dataRepository;

    @Override
    public List<List> getFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName) {
        return dataRepository.findFieldsFromTable(fields, tableName, fileName, labelName);
    }

    @Override
    public List<List> getFieldsFromTableWithCondition(List<String> fields, String tableName, String fileName, String labelName, String whereCondition) {
        return dataRepository.findFieldsFromTableWithCondition(fields, tableName, fileName, labelName, whereCondition);
    }

    @Override
    public List<List> getMultiFieldsFromTables(List<String> fields, String sql, String fileName, String labelName) {
        return dataRepository.findMultiFieldsFromTables(fields, sql, fileName, labelName);
    }
}
