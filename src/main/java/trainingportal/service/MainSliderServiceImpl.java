package trainingportal.service;

import trainingportal.model.MainSliderModel;
import trainingportal.dao.MainSliderDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MainSliderServiceImpl implements MainSliderService {

    @Autowired
    private MainSliderDao mainSliderDao;

    @Override
    public String storeData(MultipartFile file, String buttonName, String buttonUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long mainSliderId = 0L;
        String fileType = file.getContentType();
        String message;

        MainSliderModel data = new MainSliderModel(mainSliderId, fileName, fileType, file.getBytes(), buttonName, buttonUrl);


        if(fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/tiff")){
            mainSliderDao.storeData(data);
            return message = "Successfully stored!";
        } else {
            mainSliderDao.updateAll(data);
            return message = "File must be an image! Data was not stored!";
        }

    }

    @Override
    public List<MainSliderModel> getAll(){
        List<MainSliderModel> dataList = mainSliderDao.getAll();
        for (MainSliderModel data : dataList){
            data.setFilesDataString(Base64.getEncoder().encodeToString(data.getFilesData()));
        }
        return dataList;
    }

    @Override
    public void deleteById(Long mainSliderId){
        mainSliderDao.deleteById(mainSliderId);
    }

    @Override
    public int countAll(){
        return mainSliderDao.countAll();
    }

    @Override
    public void editById(Long mainSliderId, MultipartFile file, String buttonName, String buttonUrl) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        MainSliderModel data = new MainSliderModel(mainSliderId, fileName, file.getContentType(), file.getBytes(), buttonName, buttonUrl);

        if(file.getContentType().equals("application/octet-stream")){
            mainSliderDao.updateWithoutFile(data);
        } else {
            mainSliderDao.updateAll(data);
        }


    }

}
