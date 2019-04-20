package empexcel;

import empexcel.excel.EmpExcel;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class EmpExcelTest {

    @Test
    void testExcelEmpCreation() throws IOException {
        EmpExcel excelEmp = new EmpExcel();
        excelEmp.createEmpExcel();
    }
}