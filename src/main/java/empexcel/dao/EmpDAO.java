package empexcel.dao;

import empexcel.model.Emp;

import java.sql.*;

public class EmpDAO {

    private final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private final String user_name = "system";
    private final String password = "oracle";

    public void executeEmp(){

        try {

            Connection connection = DriverManager.getConnection(DB_URL, user_name, password);//соединениесБД

            PreparedStatement stmnt = connection.prepareStatement("SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM emp");
            ResultSet resultSet = stmnt.executeQuery();

            while (resultSet.next()) {

                Emp.empno.add(resultSet.getInt(1));
                Emp.ename.add(resultSet.getString(2));
                Emp.job.add(resultSet.getString(3));
                Emp.mrg.add(resultSet.getInt(4));
                Emp.hrdate.add(resultSet.getDate(5).toString());
                Emp.sal.add(resultSet.getInt(6));
                Emp.comm.add(resultSet.getInt(7));
                Emp.deptno.add(resultSet.getInt(8));

                System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2)
                        + "  " + resultSet.getString(3) + "  " + resultSet.getInt(4)
                        + "  " + resultSet.getDate(5) + "  " + resultSet.getInt(6)
                        + "  " + resultSet.getInt(7) + "  " + resultSet.getInt(8));
            }

            System.out.println("Соединение с СУБД выполнено.");
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");


        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

}
