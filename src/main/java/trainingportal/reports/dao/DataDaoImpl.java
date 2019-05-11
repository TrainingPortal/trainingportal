package trainingportal.reports.dao;

import export.Export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.reports.dao.exception.DataDaoExceptions;
import trainingportal.reports.mapper.DataMapper;
import trainingportal.reports.model.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataDaoImpl extends JdbcDaoSupport implements DataDao {

    @Autowired
    public DataDaoImpl (DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<List> findFieldsFromTable(List<String> fields, String tableName, String fileName, String labelName) {

//        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = setSQL(fields, tableName, null);

        List<List> dataList = getFieldsFromTableWhichCreateExcel(fields, fileName,labelName,sql);

        return dataList;
    }

    @Override
    public List<List> findFieldsFromTableWithCondition(List<String> fields, String tableName, String fileName, String labelName, String whereCondition) {

        //Here we use method that form sql string request query and set this string to our variable sql
        String sql = setSQL(fields, tableName, whereCondition);

        List<List> dataList = getFieldsFromTableWhichCreateExcel(fields, fileName,labelName,sql);

        return dataList;
    }

    @Override
    public List<List> findMultiFieldsFromTables(List<String> fields, String sql, String fileName, String labelName) {

        List<List> dataList = getFieldsFromTableWhichCreateExcel(fields, fileName,labelName,sql);

        return dataList;
    }


    private List<List> getFieldsFromTableWhichCreateExcel(List<String> fields, String fileName, String labelName, String sql){

        Data data = new Data();

        //This is our final List with Multiple listColumn's inside Generic <List>
        List<List> allCol = new ArrayList<>();

        if (!fields.isEmpty()) {

            for (int ii = 0; ii < fields.size(); ii++) {

                int getField = ii;

                //Here we get all column from ii field
                List currentFieldsList = this.getJdbcTemplate().query(sql,new DataMapper(fields,getField));

                //Adding to allCol current Fields List
                allCol.add(currentFieldsList);

                //Set our Data Class List variable by adding current Field List
                data.setDataListByAdd(currentFieldsList);
            }
        } else {
                throw new DataDaoExceptions("Input list is Empty");
        }

        //create new List<List> with name of fields
        List<List> allColWithName = addNameToList(allCol,fields);

        //Use Export For our List<List> formed data
        useExport(fileName,labelName,allColWithName);

        return allCol;
    }

    private String setSQL(List<String> fields, String tableName, String whereCondition) {

        if (!fields.isEmpty()) {
            String sql = "SELECT ";

            //Here we form the fields in sql query
            for (int i = 0; i < fields.size(); i++) {
                sql = sql + fields.get(i);
                if (i != fields.size() - 1)
                    sql = sql + ", ";
            }

            if (whereCondition != null ) {
                sql = sql + " FROM " + tableName + " WHERE " + whereCondition;
            }else{
                sql = sql + " FROM " + tableName;
            }

            return sql;
        }else
                throw new DataDaoExceptions("Input list is Empty");
    }

    private void useExport(String fileName, String labelName, List<List> allCol){

        Export export = new Export();
        export.exportDataToExcelGenerics(fileName,labelName,allCol);

    }

    private List<List> addNameToList(List<List> allCol,List<String> fields){

        if (!(allCol.isEmpty()) && !(fields.isEmpty())) {

            List<List> finalAllCol = new ArrayList<>();
            List localCol;

            for (int i = 0; i < allCol.size(); i++) {
                localCol = new ArrayList();
                localCol.add(fields.get(i));
                for (int j = 0; j < allCol.get(i).size(); j++) {
                    localCol.add(allCol.get(i).get(j));
                }
                finalAllCol.add(localCol);
            }
            return finalAllCol;
        }else
                throw new DataDaoExceptions("Input list OR list's is Empty");
    }
}
