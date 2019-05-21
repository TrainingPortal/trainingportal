package trainingportal.mapper.generic;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public interface BaseObjectMapper<T> extends RowMapper<T>{
    Map<String, Object> mapObject(T obj);
    String getSelectSql();
}