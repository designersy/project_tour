package reinfect.datalab.tour.utilities;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class Uploader {

    private static final String UPLOAD_PATH = "/Users/isuyeon/Documents/Project/uploads/project_tour/";

    private static final String[] ALLOW_UPLOAD = {
        "jpg", "gif", "png", "svg"
    };

    public Map<String, Object> uploadFile(MultipartFile file, String directory) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            throw new IOException("첨부할 파일을 등록해 주십시오.");
        }

        String extension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
        String uploadFileName = UUID.randomUUID().toString();

        if (fileName.contains("..")) {
            throw new IOException("올바른 파일을 등록해 주십시오.");
        }

        if (!extensionContains(extension)) {
            throw new IOException("첨부할 수 있는 파일 유형이 아닙니다.");
        }

        Path path = Paths.get(UPLOAD_PATH + directory + "/" + uploadFileName);
        Files.createDirectories(path.getParent());

        response.put("originalFileName", file.getOriginalFilename());
        response.put("uploadedFileName", uploadFileName);
        response.put("path", path + directory);
        response.put("size", file.getSize());

        return response;
    }

    private String getFileExtension(String fileName) {
        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            extension = fileName.substring(index + 1);
        }

        return extension;
    }

    private boolean extensionContains(String extension) {
        if (extension == null) {
            return false;
        }
        for (String s : ALLOW_UPLOAD) {
            if (extension.contains(s)) {
                return true;
            }
        }
        return false;
    }

}
