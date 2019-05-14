package trainingportal.dao.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.generic.BaseObjectMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public abstract class GenericDaoImpl<T> extends JdbcDaoSupport implements GenericDao<T> {
    private String table;
    private String primaryKey;
    private Map<String, Object> paramsMap;

    @Autowired
    public GenericDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public GenericDaoImpl(){
    }

    @Override
    public T findById(Long id) {
        String sql = "SELECT *" + " FROM " + getTable() + " WHERE " + getPrimaryKey() + " = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, getObjectMapper());
        }
        return null;
    }

    @Override
    public void save(T obj){
        setParamsMap(obj);
        //remove Primary Key because we autoincrement it
        getParamsMap().remove(getPrimaryKey());

        String sql = "INSERT INTO " + getTable() +" "
                + getInsertColumnsNamesAsString()
                +" VALUES " + getInsertValues();
        NamedParameterJdbcTemplate jdbcUpdate = new NamedParameterJdbcTemplate(this.getDataSource());
        jdbcUpdate.update(sql,getSqlParams());
    }

    @Override
    public void update(T entity) {
        setParamsMap(entity);
        Long id = (Long) getParamsMap().remove(getPrimaryKey());

        String sql = "UPDATE " + getTable()
                +" SET " + getColumnsNamesAsString()
                + " WHERE " + getPrimaryKey() +" = "+ id;
        NamedParameterJdbcTemplate jdbcUpdate = new NamedParameterJdbcTemplate(this.getDataSource());
        jdbcUpdate.update(sql,getSqlParams());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM " + getTable() + " WHERE " + getPrimaryKey() + " = ?";
        this.getJdbcTemplate().update(sql, id);
    }

    @Override
    public List<T> findAll() {
        String sql = "SELECT *" + " FROM " + getTable();
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, getObjectMapper());
        }
        return null;
    }

    @Override
    public int countAll() {

        String sql = "SELECT COUNT('"+ getPrimaryKey() + "') FROM" + getTable();

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public List<T> getAllAsPage(int page, int total) {
        String sql = "SELECT " + getInsertColumnsNamesAsString() + " FROM " + getTable() + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, getObjectMapper());
    }

    protected abstract BaseObjectMapper<T> getObjectMapper();

    private String getTable() {
        return table;
    }

    protected void setTable(String table) {
        this.table = table;
    }

    private String getPrimaryKey() {
        return primaryKey;
    }

    protected void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    private Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    private void setParamsMap(T obj) {
        Map<String,Object> res = getObjectMapper().mapObject(obj);
        this.paramsMap  = res;
    }

    private MapSqlParameterSource getSqlParams(){
        String[] colNames = getColumnsNames();
        MapSqlParameterSource params = new MapSqlParameterSource();
        for(String col: colNames){
            params.addValue(col, paramsMap.get(col));
        }

        return params;
    }

    private String[] getColumnsNames(){
        return getParamsMap().keySet().toArray(new String[getParamsMap().keySet().size()]);
    }

    private String getColumnsNamesAsString(){
        StringBuilder res = new StringBuilder();
        for(String column: getColumnsNames()){
            res.append(" " + column + " = :" + column + ",");
        }
        res.deleteCharAt(res.length()-1);

        return res.toString();
    }

    private String getInsertColumnsNamesAsString(){
        StringBuilder res = new StringBuilder();
        res.append("(");
        for(String column: getColumnsNames()){
            res.append(column + ",");
        }
        res.deleteCharAt(res.length()-1);
        res.append(")");

        return res.toString();
    }

    private String getInsertValues(){
        StringBuilder res = new StringBuilder();
        res.append("(");
        for(String column: getColumnsNames()){
            res.append(":"+column + " ,");
        }
        res.deleteCharAt(res.length()-1);
        res.append(")");

        return res.toString();
    }
}
