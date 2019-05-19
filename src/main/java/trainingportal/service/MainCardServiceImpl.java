package trainingportal.service;

import trainingportal.model.MainCardModel;
import trainingportal.dao.MainCardDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class MainCardServiceImpl implements MainCardService {

    private final MainCardDao mainCardDao;

    @Autowired
    public MainCardServiceImpl(MainCardDao mainCardDao) {
        this.mainCardDao = mainCardDao;
    }

    @Override
    public void storeData(MultipartFile file,  String cardTitle, String cardText, String buttonName, String cardUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long mainCardId = 0L;

        MainCardModel data = new MainCardModel(mainCardId, fileName, file.getContentType(), file.getBytes(), cardTitle, cardText, buttonName, cardUrl);

        mainCardDao.storeData(data);

    }

    @Override
    public List<MainCardModel> getAll(){
        List<MainCardModel> dataList = mainCardDao.getAll();
        for (MainCardModel data : dataList){
            data.setFilesDataString(Base64.getEncoder().encodeToString(data.getFilesData()));
        }
        return dataList;
    }

    @Override
    public void deleteById(Long mainCardId){
        mainCardDao.deleteById(mainCardId);
    }

}
