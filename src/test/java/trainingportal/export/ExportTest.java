package trainingportal.export;

import trainingportal.export.exception.ExportToExcelException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ExportTest {

    @Test
    void testExportDataToExcel() throws IOException, ExportToExcelException {

        //Create 2D Array of Data
        Object[][] value = new Object[5][2];

        for(int i = 0; i < value.length; i++)
            for(int j = 0; j < value[i].length; j++)
                value[i][j] = i+j;

        Export export = new Export();
        export.exportDataToExcel("testFile","Label Name",value);

    }
}