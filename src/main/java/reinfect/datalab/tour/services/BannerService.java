package reinfect.datalab.tour.services;

import org.springframework.web.multipart.MultipartFile;
import reinfect.datalab.tour.entities.Banner;
import reinfect.datalab.tour.http.forms.BannerForm;

import java.util.List;
import java.util.Map;

public interface BannerService {

    void register(MultipartFile banner, BannerForm form) throws Exception;
    void update(BannerForm form, Long id) throws Exception;
    void delete(BannerForm form, Long id);

    Banner currentItem(Long id) throws Exception;

    List<Banner> allItems();

    Map<String, Object> paginatedItems(int page, int perPage);

}
