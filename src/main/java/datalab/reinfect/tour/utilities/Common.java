package datalab.reinfect.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Common {

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
