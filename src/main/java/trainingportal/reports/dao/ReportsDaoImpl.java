package trainingportal.reports.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.reports.dao.exception.DataDaoExceptions;
import trainingportal.reports.mapper.ReportsMapper;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportsDaoImpl extends JdbcDaoSupport implements ReportsDao {

    @Autowired
    public ReportsDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<List> getFieldsFromTable(List<String> fields, String fileName, String labelName, String sql) {

        //This is our final List with Multiple listColumn's inside Generic <List>
        List<List> allCol = new ArrayList<>();

        if (!fields.isEmpty()) {

            for (int ii = 0; ii < fields.size(); ii++) {

                //Here we get all column from ii field
                List currentFieldsList = this.getJdbcTemplate().query(sql, new ReportsMapper(fields, ii));

                //Adding to allCol current Fields List
                allCol.add(currentFieldsList);

            }
        } else
            throw new DataDaoExceptions("Input list is Empty");

        return allCol;
    }
}
