package trainingportal.service;

import trainingportal.model.MainCardModel;
import trainingportal.repository.MainCardModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class MainCardServiceImpl implements MainCardService {

    @Autowired
    private MainCardModelRepository mainCardRepository;

    @Override
    public MainCardModel storeData(MultipartFile file,  String cardTitle, String cardText, String buttonName, String cardUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        MainCardModel data = new MainCardModel(fileName, file.getContentType(), file.getBytes(), cardTitle, cardText, buttonName, cardUrl);

        return mainCardRepository.saveAndFlush(data);

    }

    @Override
    public List<MainCardModel> getAll(){
        List<MainCardModel> dataList = mainCardRepository.findAll();
        for (MainCardModel data : dataList){
            data.setFilesDataString(Base64.getEncoder().encodeToString(data.getFilesData()));
        }
        return dataList;
    }

    @Override
    public void deleteById(Long mainSliderId){
        mainCardRepository.deleteById(mainSliderId);
    }

}
