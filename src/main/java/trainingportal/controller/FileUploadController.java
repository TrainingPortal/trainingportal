package trainingportal.controller;

import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.FilesModel;
import trainingportal.service.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private StorageService storageService;


    @GetMapping("/upload_form")
    public String uploadForm(Model model){
        return "/uploadData/upload_form";
    }

    @PostMapping("/file-save")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, ModelAndView modelAndView) throws IOException {
        storageService.storeFile(file);


        modelAndView.setViewName("redirect:/upload_form");
        return modelAndView;
    }


}