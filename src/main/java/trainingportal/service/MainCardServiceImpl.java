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

    @Autowired
    private MainCardDao mainCardDao;

    @Override
    public boolean storeData(MultipartFile file,  String cardTitle, String cardText, String buttonName, String cardUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long mainCardId = 0L;
        String fileType = file.getContentType();
        boolean message;

        MainCardModel data = new MainCardModel(mainCardId, fileName, file.getContentType(), file.getBytes(), cardTitle, cardText, buttonName, cardUrl);

        if(fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/tiff")){
            mainCardDao.storeData(data);
            return message = true;
        } else {
            return message = false;
        }

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

    @Override
    public boolean editById(Long mainCardId, MultipartFile file, String cardTitle, String cardText, String buttonName, String buttonUrl) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        boolean message;

        MainCardModel data = new MainCardModel(mainCardId, fileName, file.getContentType(), file.getBytes(), cardTitle, cardText, buttonName, buttonUrl);

        if(file.getContentType().equals("application/octet-stream")){
            mainCardDao.updateWithoutFile(data);
            return message = true;
        } else if(fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/tiff")){
            mainCardDao.updateAll(data);
            return message = true;
        } else {
            return message = false;
        }


    }

}
