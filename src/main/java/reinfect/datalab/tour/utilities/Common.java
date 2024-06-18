package reinfect.datalab.tour.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
@RequiredArgsConstructor
public class Common {

    private final ResourceBundleMessageSource messageSource;

    public String isEmpty(String word) {
        return Objects.requireNonNullElse(word, "데이터 없음");
    }

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

    public Map<String, Object> paginate(int currentPage, Page<?> items, String search, String type, String sort) {
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
        paginatePosition.put("sort", sort);

        return paginatePosition;
    }

    public String getRestApi(String apiUrl, int timeout, String headerName ,String header) throws Exception {
        URL url = null;
        String readLine = null;
        StringBuilder builder = null;
        BufferedReader reader = null;
        HttpURLConnection connection;

        try {
            url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestProperty("Accept", "application/json");

            if (!headerName.isBlank()){
                connection.setRequestProperty(headerName, header);
            }

            builder = new StringBuilder();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                while ((readLine = reader.readLine()) != null) {
                    builder.append(readLine).append("\n");
                }
            } else {
                builder.append("code: ");
                builder.append(connection.getResponseCode()).append("\n");
                builder.append("message: ");
                builder.append(connection.getResponseMessage()).append("\n");
            }
        } catch (Exception exception) {
            return "";
        } finally {
            if (reader != null) reader.close();
        }

        return builder.toString();
    }

    public Map<String, Object> convertJsonData(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();

        return  gson.fromJson(json, type);
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String getLocale() {
        return String.valueOf(LocaleContextHolder.getLocale());
    }

    public String getLoginUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) return ((UserDetails) principal).getUsername();
            else return principal.toString();
        }

        return null;
    }

}
