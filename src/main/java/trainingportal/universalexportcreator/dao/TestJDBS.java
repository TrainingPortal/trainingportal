package trainingportal.universalexportcreator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.universalexportcreator.mapper.DataMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestJDBS extends JdbcDaoSupport {

    @Autowired
    public TestJDBS (DataSource dataSource) { this.setDataSource(dataSource); }

    public void getJOINQuery(){

        String sql = "select * From emp join dept on emp.deptno = dept.deptno";

        List<List> allCol = new ArrayList<>();

        List<String> fields = new ArrayList<>();
        fields.add("comm");
        fields.add("dname");

        for (int ii = 0; ii < fields.size(); ii++) {

            int getField = ii;
            List currentFieldsList  =  this.getJdbcTemplate().query(sql, new DataMapper(fields,ii));
            allCol.add(currentFieldsList);

        }

        for (int i = 0; i < allCol.size(); i++) {
            for (int j = 0; j < allCol.get(i).size(); j++) {
                System.out.println(allCol.get(i).get(j));
            }
        }
    }

}
