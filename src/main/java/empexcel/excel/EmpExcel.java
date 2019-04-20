package empexcel.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import empexcel.dao.EmpDAO;
import empexcel.model.Emp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Line chart example.
 */
public class EmpExcel {

    public void createEmpExcel() throws IOException {

        EmpDAO empDAO = new EmpDAO();
        empDAO.executeEmp();

        ArrayList empnoCopy = Emp.empno;
        ArrayList<String> enameCopy = Emp.ename;
        ArrayList<String> jobCopy = Emp.job;
        ArrayList mrgCopy = Emp.mrg;
        ArrayList hrdateCopy = Emp.hrdate;
        ArrayList salCopy = Emp.sal;
        ArrayList commCopy = Emp.comm;
        ArrayList deptnoCopy = Emp.deptno;

        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            XSSFSheet sheet = wb.createSheet("EMP table from DATABASE");

            int rownum = 1;
            int startCellNum = 0;

            Row row;
            Cell cell;

            while (!empnoCopy.isEmpty()) {

                row = sheet.createRow(rownum);

                cell = row.createCell(startCellNum);
                cell.setCellValue((int) empnoCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((String) enameCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((String) jobCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((int) mrgCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((String) hrdateCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((int) salCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((int) commCopy.get(0));

                cell = row.createCell(++startCellNum);
                cell.setCellValue((int) deptnoCopy.get(0));

                --startCellNum;
                --startCellNum;
                --startCellNum;
                --startCellNum;
                --startCellNum;
                --startCellNum;
                --startCellNum;

                rownum++;

                empnoCopy.remove(0);
                enameCopy.remove(0);
                jobCopy.remove(0);
                mrgCopy.remove(0);
                hrdateCopy.remove(0);
                salCopy.remove(0);
                commCopy.remove(0);
                deptnoCopy.remove(0);

            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("empTableFromDatabase.xlsx")) {
                wb.write(fileOut);
            }
        }
    }
}


