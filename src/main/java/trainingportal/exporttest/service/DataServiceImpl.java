package trainingportal.exporttest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.exporttest.dao.DataDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    DataDaoImpl dataRepository;

    @Override
    public List<List> getFieldsFromTable(List<String> fields, String tableName) {
        return dataRepository.findFieldsFromTable(new ArrayList<>(),"fdsf");
    }
}
