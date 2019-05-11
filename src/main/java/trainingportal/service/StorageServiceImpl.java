package trainingportal.service;


import trainingportal.model.FilesModel;
import trainingportal.repository.DBFilesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    private DBFilesRepository dbFileRepository;

    public FilesModel storeFile(MultipartFile file) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FilesModel dbFile = new FilesModel(fileName, file.getContentType(), file.getBytes());

        return dbFileRepository.save(dbFile);

    }

    /*public FilesModel getFile(String fileId) {
        return dbFileRepository.findById(fileId);
    }*/
}
