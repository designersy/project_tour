package reinfect.datalab.tour.utilities;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
public class Uploader {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private static final String[] ALLOW_UPLOAD = {
        "jpg", "gif", "png", "svg"
    };

    public Map<String, Object> uploadS3(MultipartFile file, String directory) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("파일을 첨부해 주십시오.");
        }

        if (Objects.equals(file.getOriginalFilename(), "..")) {
            throw new IOException("유효한 파일을 첨부해 주십시오.");
        }

        Map<String, Object> response = new HashMap<>();

        String originalFileName = file.getOriginalFilename();
        String extension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
        String uploadFileName = directory + "/" + UUID.randomUUID().toString() + "." + extension;

        if (extensionContains(extension)) {
            throw new IOException("첨부 가능한 파일 유형이 아닙니다.");
        }

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, uploadFileName, file.getInputStream(), metadata);

        response.put("originalFileName", file.getOriginalFilename());
        response.put("uploadedFileName", amazonS3.getUrl(bucket, uploadFileName).toString());
        response.put("path", "");
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
