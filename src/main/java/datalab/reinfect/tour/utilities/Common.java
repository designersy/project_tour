package datalab.reinfect.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Common {

    public String randomString(int length) {
        StringBuilder builder = new StringBuilder();

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int charsLength = chars.length();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(charsLength)));
        }

        return builder.toString();
    }

    public String readHtmlEmailResource(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/assets/email/" + fileName);
        byte[] fileBytes = Files.readAllBytes(Paths.get(resource.getURI()));

        return new String(fileBytes);
    }

    public Map<String, Object> paginate(int currentPage, Page<?> items, String search, String type) {
        int totalPages = items.getTotalPages();
        int start = Math.max(1, currentPage / 10 * 10 + 1);
        int end = Math.min(start + 9, totalPages);

        Map<String, Object> paginatePosition = new HashMap<>();
        paginatePosition.put("items", items);
        paginatePosition.put("currentPage", currentPage);
        paginatePosition.put("totalPages", totalPages);
        paginatePosition.put("start", start);
        paginatePosition.put("end", end);
        paginatePosition.put("search", search);
        paginatePosition.put("type", type);

        return paginatePosition;
    }

}
