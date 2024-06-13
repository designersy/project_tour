package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.Food;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.http.forms.PlaceForm;

import java.util.List;
import java.util.Map;

public interface PlaceService {

    void register(PlaceForm form);
    void install();
    void update(PlaceForm form, Long id) throws Exception;
    void delete(Long id);

    Place currentItem(Long id) throws Exception;

    List<Place> latest(LatestType latestType);

    Map<String, Object> paginatedItems(int page, int perPage, String searchType, String searchWord, String sort);

}
