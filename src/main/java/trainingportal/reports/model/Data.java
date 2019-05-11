package trainingportal.reports.model;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<List> dataList = new ArrayList<>();

    public List<List> getDataList() {
        return dataList;
    }

    public void setDataListByAdd(List dataList) {
        this.dataList.add(dataList);
    }


    public Data(){}
}
