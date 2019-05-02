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
        //todo set your parameters to use this method
        return dataRepository.findFieldsFromTable(new ArrayList<>(),"emp","TestFile","table");
    }
}
