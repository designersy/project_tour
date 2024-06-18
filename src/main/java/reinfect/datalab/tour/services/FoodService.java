package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.Food;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.http.forms.FoodForm;

import java.util.List;
import java.util.Map;

public interface FoodService {

    void register(FoodForm form);
    void update(FoodForm form, Long id) throws Exception;
    void delete(Long id);

    Food currentItem(Long id) throws Exception;

    List<Food> latest(LatestType latestType);

    Map<String, Object> paginatedItems(int page, int perPage, String searchType, String searchWord, String sort);

    List<Food> findAll();
}
