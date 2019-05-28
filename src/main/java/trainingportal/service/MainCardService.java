package trainingportal.service;

import org.springframework.web.multipart.MultipartFile;
import trainingportal.model.MainCardModel;

import java.io.IOException;
import java.util.List;

public interface MainCardService {

    boolean storeData(MultipartFile file, String cardTitle, String cardText, String buttonName, String cardUrl)
            throws IOException;

    List<MainCardModel> getAll();

    void deleteById(Long mainCardId);

    boolean editById(Long mainSliderId, MultipartFile file, String cardTitle, String cardText, String buttonName,
                  String buttonUrl) throws IOException;
}