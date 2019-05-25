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
    public boolean storeData(MultipartFile file, String buttonName, String buttonUrl, String slHeader, String slText,
                             int interval) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long mainSliderId = 0L;
        String fileType = file.getContentType();
        boolean message;

        MainSliderModel data = new MainSliderModel(mainSliderId, fileName, fileType, file.getBytes(), buttonName,
                buttonUrl, slHeader, slText, interval);


        if(fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/tiff")){
            mainSliderDao.storeData(data);
            return message = true;
        } else {
            return message = false;
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
    public boolean editById(Long mainSliderId, MultipartFile file, String buttonName, String buttonUrl,
                            String slHeader, String slText, int interval) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        boolean message;

        MainSliderModel data = new MainSliderModel(mainSliderId, fileName, fileType, file.getBytes(), buttonName,
                buttonUrl, slHeader, slText, interval);

        if(fileType.equals("application/octet-stream")){
            mainSliderDao.updateWithoutFile(data);
            return message = true;
        } else if(fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/tiff")){
            mainSliderDao.updateAll(data);
            return message = true;
        } else {
            return message = false;
        }


    }

}
