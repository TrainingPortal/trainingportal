package trainingportal.dao;

import trainingportal.model.MainCardModel;

import java.util.List;

public interface MainCardDao {

    List<MainCardModel> getAll();

    MainCardModel findById(Long mainCardId);

    void storeData(MainCardModel card);

    void updateAll(MainCardModel card);

    void updateWithoutFile(MainCardModel card);

    void deleteById(Long mainCardId);

}
