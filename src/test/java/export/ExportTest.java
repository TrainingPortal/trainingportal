package export;

import export.exception.ExportToExcelException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ExportTest {

    @Test
    void testExportDataToExcelArray() throws IOException, ExportToExcelException {

        //Create 2D Array of Data
        Object[][] value = new Object[5][2];

        for(int i = 0; i < value.length; i++)
            for(int j = 0; j < value[i].length; j++)
                value[i][j] = i+j;

        Export export = new Export();
        export.exportDataToExcel("ArrayTestFile","Label Name",value);

    }

    @Test
    void testExportDataToExcelList() throws IOException, ExportToExcelException {

        //Create List
        List list = new ArrayList();

        //set list
        list.add("a");
        list.add(11);
        list.add(33.33);
        list.add("this is String data");

        Export export = new Export();
        export.exportDataToExcel("ListTestFile","Label Name",list);

    }
}