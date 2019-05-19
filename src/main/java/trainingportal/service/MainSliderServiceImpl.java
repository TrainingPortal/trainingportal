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
    public void storeData(MultipartFile file, String buttonName, String buttonUrl) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long mainSliderId = 0L;

        MainSliderModel data = new MainSliderModel(mainSliderId, fileName, file.getContentType(), file.getBytes(), buttonName, buttonUrl);

        mainSliderDao.storeData(data);

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

    // Needed for carousel indicators
    @Override
    public ArrayList<Integer> getSlideIndicators(){
        int number;
        number = countAll();
        ArrayList<Integer> indicatorList = new ArrayList<Integer>();
        for (int i = 0; i <= number; i++){
            indicatorList.add(i);
        }
        return indicatorList;
    }

}
