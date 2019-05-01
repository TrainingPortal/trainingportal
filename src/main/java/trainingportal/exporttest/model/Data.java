package trainingportal.exporttest.model;

import java.util.List;

public class Data {

    private List<List> dataList;

    public List<List> getDataList() {
        return dataList;
    }

    public void setDataListByAdd(List<String> dataList) {
        this.dataList.add(dataList);
    }


    public Data(){}
}
