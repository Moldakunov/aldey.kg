package kg.aldey.controller;

import kg.aldey.config.globalvariable.GlobalVar;
import kg.aldey.model.forms.UploadForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainRESTController {
    @PostMapping(value = "/admin/uploadSlider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {
        String result;
        try {
            result = this.saveUploadedFiles(form.getFiles());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Успешно загружены следующие файлы: "  + result, HttpStatus.OK);
    }

    // Save Files
    private String saveUploadedFiles(MultipartFile[] files) throws IOException {
        // Make sure directory exists!
        File uploadDir = new File(GlobalVar.BANNER_UPLOAD_DIRECTORY);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();
        String fileName;

        for (int i = 0; i < files.length; i++) {

            if (files[i].isEmpty()) {
                continue;
            }

            fileName = files[i].getOriginalFilename();
            assert fileName != null;
            String uploadFilePath = GlobalVar.BANNER_UPLOAD_DIRECTORY + "/slider-" + (i + 1) + fileName.substring(fileName.lastIndexOf("."));

            byte[] bytes = files[i].getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(fileName).append("<br/>");
        }
        return sb.toString();
    }
}
