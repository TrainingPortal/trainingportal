package trainingportal;

import export.Export;
import export.exception.ExportToExcelException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ExportToExcelException {

        List<List> list = new ArrayList<>();

        List lists = new ArrayList();

        lists.add("aa");
        lists.add("aa");
        lists.add("aa");
        lists.add("aa");

        list.add(lists);

        Export export = new Export();
        export.exportDataToExcel("Te","l",lists);

    }

}
