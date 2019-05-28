package trainingportal.dao;

import trainingportal.model.MainCardModel;

import java.util.List;

public interface MainCardDao {

    List<MainCardModel> getAll();

    void storeData(MainCardModel card);

    void updateAll(MainCardModel card);

    void updateWithoutFile(MainCardModel card);

    void deleteById(Long mainCardId);

}
