package trainingportal.exporttest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.exporttest.model.Data;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataDaoImpl extends JdbcDaoSupport implements DataDao {

    @Autowired
    public DataDaoImpl (DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<List> findFieldsFromTable(List<String> fields, String tableName) {

        String sql = setSQL(fields, tableName);
        List<List> finalDataList = new ArrayList<>();
        Data data = new Data();

        if (!fields.isEmpty()) {

            for (int ii = 0; ii < fields.size(); ii++) {

                List allDataList = this.getJdbcTemplate().query(sql, new Object[]{}, new RowMapper<Data>() {
                    @Override
                    public Data mapRow(ResultSet resultSet, int i) throws SQLException {

                        Data data = new Data();

                        while (resultSet.next()) {

                            List<String> courseName;

                            courseName = new ArrayList<>();

                            Object cName = resultSet.getObject(fields.get(i));
                            courseName.add(cName.toString());

                        }
                        return data;
                    }
                });

                finalDataList.add(allDataList);

                data.setDataListByAdd(allDataList);
            }

        }

        return finalDataList;
    }

    private String setSQL(List<String> fields, String tableName) throws ArrayStoreException {

        if (!fields.isEmpty()) {
            String sql = "SELECT ";

            for (int i = 0; i < fields.size(); i++) {
                sql = sql + fields.get(i);
                if (i != fields.size() - 1)
                    sql = sql + ", ";
            }
            sql = sql + " FROM " + tableName;

            return sql;
        }else
            throw new ArrayStoreException();
    }
}
