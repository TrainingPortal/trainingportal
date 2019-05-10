/*
package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDAOImpl implements FileUploadDao {
    private JdbcTemplate jdbcTemplate;

    public void FileDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int inserRecords(int carousel_id, MultipartFile photo) throws IOException {

        byte[] photoBytes = photo.getBytes();

        String sql = "INSERT INTO carousel_data(carousel_id, carousel_image) VALUES (?,?)";

        return jdbcTemplate.update(sql, new Object[]{carousel_id, photoBytes});
    }
}
*/
