package trainingportal.exceltest.dao;

import export.Export;
import export.exception.ExportToExcelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.exceltest.model.Trainer;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainerDaoImpl extends JdbcDaoSupport implements TrainerDao {

    @Autowired
    public TrainerDaoImpl (DataSource dataSource) { this.setDataSource(dataSource); }

    public TrainerDaoImpl(){}

    @Override
    public List<Trainer> findAllTrainerCourse() {

        String sql = "SELECT c.name FROM cource c, users u WHERE u.userid = c.trainerid";
        ArrayList<String> courseName = new ArrayList<>();

        List<Trainer> allCourse = this.getJdbcTemplate().query(sql, new Object[]{}, new RowMapper<Trainer>() {
            @Override
            public Trainer mapRow(ResultSet resultSet, int i) throws SQLException {

                String cName = resultSet.getString("name");
                courseName.add(cName);

                return new Trainer(courseName);
            }
        });

        Export export = new Export();

        try {
            export.exportDataToExcel("allCourses","trainer",allCourse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExportToExcelException e) {
            e.printStackTrace();
        }

        return allCourse;
    }

}
