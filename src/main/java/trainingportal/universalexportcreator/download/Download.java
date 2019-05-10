package trainingportal.universalexportcreator.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class Download {

    private static final String DIRECTORY = "/Users/mrlova/IdeaProjects/trainingportal";
    //private static final String DEFAULT_FILE_NAME = "";

    @Autowired
    private ServletContext servletContext;

    public ResponseEntity<InputStreamResource> downloadFile(/*@RequestParam(defaultValue = DEFAULT_FILE_NAME)*/ String fileName) {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = null;

        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length())
                .body(resource);
    }

}
