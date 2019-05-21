package trainingportal.reports.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsMapper implements RowMapper<List<Object>> {

    private List<String> fields;
    private int getFieldNumber;

    @Override
    public List<Object> mapRow(ResultSet resultSet, int i){

        List<Object> localList = new ArrayList();

        try {
            if (resultSet.getObject(fields.get(getFieldNumber)) != null){
                Object cName = resultSet.getObject(fields.get(getFieldNumber));
                localList.add(cName.toString());
            }else
                localList.add("null");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }

    public ReportsMapper(List<String> fields, int getFieldNumber){
        this.fields = fields;
        this.getFieldNumber = getFieldNumber;
    }
}
