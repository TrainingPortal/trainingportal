package trainingportal.dao;

import trainingportal.model.MainSliderModel;

import java.util.List;

public interface MainSliderDao {

    List<MainSliderModel> getAll();

    MainSliderModel findById(Long mainSliderId);

    void storeData(MainSliderModel slide);

    void updateAll(MainSliderModel slide);

    void updateWithoutFile(MainSliderModel slide);

    void deleteById(Long mainSliderId);

    int countAll();

}
