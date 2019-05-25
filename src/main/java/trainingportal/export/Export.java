package trainingportal.export;

import org.springframework.stereotype.Service;
import trainingportal.export.exception.ExportToExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class Export {

    public void exportDataToExcel(String fileName, String labelName, Object[][] data) throws ExportToExcelException {

        if (data[0][0] != null) {

            //Create new workbook and tab
            try (XSSFWorkbook wb = new XSSFWorkbook()) {

                Sheet sheet = wb.createSheet(labelName);

                createFileInside(sheet, data);

                // Write the output to a file
                try (FileOutputStream fileOut = new FileOutputStream(fileName + ".xlsx")) {
                    wb.write(fileOut);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }else {
            throw new ExportToExcelException("Input data is empty");
        }
    }

    public void exportDataToExcel(String fileName, String labelName, List list) throws ExportToExcelException {

        if (!list.isEmpty()) {

            Object[][] objects = new Object[list.size()][1];

            for (int i = 0; i < list.size(); i++) {
                Object s = list.get(i);
                objects[i][0] = s;
            }

            exportDataToExcel(fileName, labelName, objects);

        }else {
            throw new ExportToExcelException("Input List is empty");
        }
    }

    public void exportDataToExcelGenerics(String fileName, String labelName, List<List> list) throws ExportToExcelException {

        if (!list.isEmpty()) {

            Object[][] objects = new Object[list.get(0).size()][list.size()];

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    Object s = list.get(i).get(j);
                    objects[j][i] = s;
                }
            }

            exportDataToExcel(fileName, labelName, objects);

        }else {
            throw new ExportToExcelException("Input List is empty or list's inside List<List list> has different size");
        }
    }

    private void createFileInside(Sheet sheet,Object[][] data){

        //Create 2D Cell Array
        Row[] row = new Row[data.length];
        Cell[][] cell = new Cell[row.length][];

        //Define and Assign Cell Data from Given
        for (int i = 0; i < row.length; i++) {
            row[i] = sheet.createRow(i);
            cell[i] = new Cell[data[i].length];

            for (int j = 0; j < cell[i].length; j++) {
                cell[i][j] = row[i].createCell(j);
                cell[i][j].setCellValue(data[i][j].toString());
            }

        }

    }


}
