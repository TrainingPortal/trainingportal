package trainingportal.universalexportcreator.dao;

import export.Export;
import export.exception.ExportToExcelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.universalexportcreator.mapper.DataMapper;
import trainingportal.universalexportcreator.model.Data;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataDaoImpl extends JdbcDaoSupport implements DataDao {

    @Autowired
    public DataDaoImpl (DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<List> findFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = setSQL(fields, tableName);

        Data data = new Data();

        //This is our final List with Multiple listColumn's inside Generic <List>
        List<List> allCol = new ArrayList<>();

        if (!fields.isEmpty()) {

            for (int ii = 0; ii < fields.size(); ii++) {

                int getField = ii;

                //Here we get all column from ii field
                List currentFieldsList = this.getJdbcTemplate().query(sql, new Object[]{}, new DataMapper(fields,getField));

                //Adding to allCol current Fields List
                allCol.add(currentFieldsList);

                //Set our Data Class List variable by adding current Field List
                data.setDataListByAdd(currentFieldsList);
            }
        }

        //Use Export For our List<List> formed data
        useExport(fileName,labelName,allCol);

        return allCol;
    }

    private String setSQL(List<String> fields, String tableName) throws ArrayStoreException {

        if (!fields.isEmpty()) {
            String sql = "SELECT ";

            //Here we form the fields in sql query
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

    private void useExport(String fileName, String labelName, List<List> allCol){

        Export export = new Export();
        try {
            export.exportDataToExcelGenerics(fileName,labelName,allCol);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExportToExcelException e) {
            e.printStackTrace();
        }

    }
}
