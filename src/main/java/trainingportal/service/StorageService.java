package trainingportal.service;

import org.springframework.web.multipart.MultipartFile;
import trainingportal.model.FilesModel;

import java.io.IOException;


public interface StorageService {

    FilesModel storeFile(MultipartFile file) throws IOException;

}
