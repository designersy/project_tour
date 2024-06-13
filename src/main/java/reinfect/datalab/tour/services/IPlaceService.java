package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.http.forms.PlaceForm;
import reinfect.datalab.tour.repositories.PlaceRepository;
import reinfect.datalab.tour.utilities.Common;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IPlaceService implements PlaceService {

    private final Common common;

    private final PlaceRepository repository;

    @Override
    public void register(PlaceForm form) {
        String position = "{x:" + form.getPositionX() + ", y:" + form.getPositionY() + "}";

        repository.save(
            Place.builder().name(form.getName())
                           .address(form.getAddress())
                           .newAddress(form.getNewAddress())
                           .access(form.getAccess())
                           .breakDate(form.getBreakDate())
                           .businessDay(form.getBusinessDay())
                           .businessTime(form.getBusinessTime())
                           .language(form.getLanguage())
                           .website(form.getWebsite())
                           .telephone(form.getTelephone())
                           .position(position)
                           .postUrl(form.getPostUrl())
                           .handicap(form.getHandicap())
                           .tags(form.getTags())
                           .build()
        );
    }

    @Override
    public void install() {
        int batchSize = 100;
    }

    @Override
    public void update(PlaceForm form, Long id) throws Exception {
        Place data = currentItem(id);
        String position = "{x:" + form.getPositionX() + ", y:" + form.getPositionY() + "}";

        data.setAccess(form.getAccess());
        data.setBreakDate(form.getBreakDate());
        data.setBusinessDay(form.getBusinessDay());
        data.setBusinessTime(form.getBusinessTime());
        data.setHandicap(form.getHandicap());
        data.setLanguage(form.getLanguage());
        data.setPostUrl(form.getPostUrl());
        data.setName(form.getName());
        data.setAddress(form.getAddress());
        data.setNewAddress(form.getNewAddress());
        data.setPosition(position);
        data.setTelephone(form.getTelephone());
        data.setWebsite(form.getWebsite());
        data.setTags(form.getTags());

        repository.save(data);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public Place currentItem(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("데이터가 없습니다."));
    }

    @Override
    public List<Place> latest(LatestType latestType) {
        if (latestType == LatestType.RANDOM) return repository.findLatestRandom();
        return repository.findTop10OrderByOrderByIdDesc();
    }

    @Override
    public Map<String, Object> paginatedItems(int page, int perPage, String searchType, String searchWord, String sort) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<Place> items;

        if (sort.equals("rating") || sort.equals("review")) {
            if (sort.equals("rating")) {
                items = switch(searchType) {
                    case "name" -> repository.findFilteredRatingsAndSearchName(searchWord, pageable);
                    case "local" -> repository.findFilteredRatingsAndSearchNewAddress(searchWord, pageable);
                    default -> repository.findFilteredRatings(pageable);
                };
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findFilteredReviewsAndSearchName(searchWord, pageable);
                    case "local" -> repository.findFilteredReviewsAndSearchNewAddress(searchWord, pageable);
                    default -> repository.findFilteredReviews(pageable);
                };
            }
        } else {
            items = switch(searchType) {
                case "name" -> repository.findAllByNameContainingOrderByIdDesc(searchWord, pageable);
                case "local" -> repository.findAllByNewAddressContainingOrderByIdDesc(searchWord, pageable);
                default -> repository.findAllByOrderByIdDesc(pageable);
            };
        }

        return common.paginate(page, items, searchWord, searchType, sort);
    }

}
