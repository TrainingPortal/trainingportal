package trainingportal.service;

import org.springframework.web.multipart.MultipartFile;
import trainingportal.model.MainSliderModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MainSliderService {

    void storeData(MultipartFile file, String buttonName, String buttonUrl) throws IOException;

    List<MainSliderModel> getAll();

    void deleteById(Long mainSliderId);

    int countAll();

    void editById(Long mainSliderId, MultipartFile file, String buttonName, String buttonUrl) throws IOException;

}
