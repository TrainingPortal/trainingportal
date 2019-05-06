package trainingportal.utils;

public class SQLUtil {

    public String insert(String table, String values, String ps){
        return "INSERT INTO " + values + " " + table + " VALUES " + ps;
    }

    public String update(String table, String values){
        return "UPDATE " + table +" SET " + values;
    }
}
