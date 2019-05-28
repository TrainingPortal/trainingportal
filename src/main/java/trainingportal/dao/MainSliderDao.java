package trainingportal.dao;

import trainingportal.model.MainSliderModel;

import java.util.List;

public interface MainSliderDao {

    List<MainSliderModel> getAll();

    void storeData(MainSliderModel slide);

    void updateAll(MainSliderModel slide);

    void updateWithoutFile(MainSliderModel slide);

    void deleteById(Long mainSliderId);


}
