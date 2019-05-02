package trainingportal.universalexportcreator.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataMapper implements RowMapper<List> {

    private List<String> fields;
    private int getField;

    @Override
    public List mapRow(ResultSet resultSet, int i){

        List localList = new ArrayList();

        try {

            if (resultSet.getObject(fields.get(getField)) != null){

                Object cName = resultSet.getObject(fields.get(getField));
                localList.add(cName.toString());

            }else {
                localList.add("null");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }

    public DataMapper(List<String> fields, int getField){
        this.fields = fields;
        this.getField = getField;
    }
}
