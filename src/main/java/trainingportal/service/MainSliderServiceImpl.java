package trainingportal.service;

import trainingportal.model.MainSliderModel;
import trainingportal.repository.MainSliderModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class MainSliderServiceImpl implements MainSliderService {

    @Autowired
    private MainSliderModelRepository mainSliderRepository;

    @Override
    public MainSliderModel storeData(MultipartFile file, String buttonName, String buttonUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        MainSliderModel data = new MainSliderModel(fileName, file.getContentType(), file.getBytes(), buttonName, buttonUrl);

        return mainSliderRepository.saveAndFlush(data);

    }

    @Override
    public List<MainSliderModel> getAll(){
        List<MainSliderModel> dataList = mainSliderRepository.findAll();
        for (MainSliderModel data : dataList){
            data.setFilesDataString(Base64.getEncoder().encodeToString(data.getFilesData()));
        }
        return dataList;
    }

    @Override
    public void deleteById(Long mainSliderId){
        mainSliderRepository.deleteById(mainSliderId);
    }

}
