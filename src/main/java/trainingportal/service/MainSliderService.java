package trainingportal.service;

import org.springframework.web.multipart.MultipartFile;
import trainingportal.model.MainSliderModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MainSliderService {

    boolean storeData(MultipartFile file, String buttonName, String buttonUrl, String slHeader, String slText,
                      int interval) throws IOException;

    List<MainSliderModel> getAll();

    void deleteById(Long mainSliderId);

    int countAll();

    boolean editById(Long mainSliderId, MultipartFile file, String buttonName, String buttonUrl,
                     String slHeader, String slText, int interval) throws IOException;

}
