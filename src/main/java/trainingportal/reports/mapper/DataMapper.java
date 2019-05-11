package trainingportal.reports.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataMapper implements RowMapper<List> {

    private List<String> fields;
    private int getFieldNumber;

    @Override
    public List mapRow(ResultSet resultSet, int i){

        List localList = new ArrayList();

        try {

            if (resultSet.getObject(fields.get(getFieldNumber)) != null){

                Object cName = resultSet.getObject(fields.get(getFieldNumber));
                localList.add(cName.toString());

            }else {
                localList.add("null");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }

    public DataMapper(List<String> fields, int getFieldNumber){
        this.fields = fields;
        this.getFieldNumber = getFieldNumber;
    }
}
