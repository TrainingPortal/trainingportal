package trainingportal.service;

import trainingportal.model.CarouselData;
import trainingportal.repository.CarouselDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class StorageServiceImpl {

    @Autowired
    private CarouselDataRepository carRepos;

    public DBFile storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        /*try {

            CarouselData carData = new CarouselData(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }*/

}
