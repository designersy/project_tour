package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.entities.PlaceRating;
import reinfect.datalab.tour.entities.PlaceReview;
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
    public void update(PlaceReview review, PlaceRating rating, long id) throws Exception {
        Place data = currentItem(id);
        List<PlaceReview> reviewList = data.getPlaceReviews();
        List<PlaceRating> ratingList = data.getPlaceRatings();

        ratingList.add(rating);
        reviewList.add(review);

        data.setPlaceReviews(reviewList);
        data.setPlaceRatings(ratingList);

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
    public List<Place> findAll() {
        return repository.findAll();
    }

    @Override
    public Map<String, Object> paginatedItems(int page, int perPage, String searchType, String searchWord, String sort, String lang) {
        Pageable pageable = PageRequest.of(page-1, perPage);
        Page<Place> items = null;

        if (lang.equals("ko")){
            if (sort.equals("rating") || sort.equals("review")) {
                if (sort.equals("rating")) {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredRatingsAndSearchNameSpecificLanguageKo(searchWord, pageable);
                        case "local" -> repository.findFilteredRatingsAndSearchNewAddressSpecificLanguageKo(searchWord, pageable);
                        default -> repository.findFilteredRatingsSpecificLanguageKo(pageable);
                    };
                } else {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredReviewsAndSearchNameSpecificLanguageKo(searchWord, pageable);
                        case "local" -> repository.findFilteredReviewsAndSearchNewAddressSpecificLanguageKo(searchWord, pageable);
                        default -> repository.findFilteredReviewsSpecificLanguageKo(pageable);
                    };
                }
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findAllByNameContainingOrderByIdDescKo(searchWord, pageable);
                    case "local" -> repository.findAllByNewAddressContainingOrderByIdDescKo(searchWord, pageable);
                    default -> repository.findAllByOrderByIdDescKo(pageable);
                };
            }
        } else if(lang.equals("en")){
            if (sort.equals("rating") || sort.equals("review")) {
                if (sort.equals("rating")) {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredRatingsAndSearchNameSpecificLanguageEn(searchWord, pageable);
                        case "local" -> repository.findFilteredRatingsAndSearchNewAddressSpecificLanguageEn(searchWord, pageable);
                        default -> repository.findFilteredRatingsSpecificLanguageEn(pageable);
                    };
                } else {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredReviewsAndSearchNameSpecificLanguageEn(searchWord, pageable);
                        case "local" -> repository.findFilteredReviewsAndSearchNewAddressSpecificLanguageEn(searchWord, pageable);
                        default -> repository.findFilteredReviewsSpecificLanguageEn(pageable);
                    };
                }
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findAllByNameContainingOrderByIdDescEn(searchWord, pageable);
                    case "local" -> repository.findAllByNewAddressContainingOrderByIdDescEn(searchWord, pageable);
                    default -> repository.findAllByOrderByIdDescEn(pageable);
                };
            }
        } else if(lang.equals("zh")){
            if (sort.equals("rating") || sort.equals("review")) {
                if (sort.equals("rating")) {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredRatingsAndSearchNameSpecificLanguageZhCN(searchWord, pageable);
                        case "local" -> repository.findFilteredRatingsAndSearchNewAddressSpecificLanguageZhCN(searchWord, pageable);
                        default -> repository.findFilteredRatingsSpecificLanguageZhCN(pageable);
                    };
                } else {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredReviewsAndSearchNameSpecificLanguageZhCN(searchWord, pageable);
                        case "local" -> repository.findFilteredReviewsAndSearchNewAddressSpecificLanguageZhCN(searchWord, pageable);
                        default -> repository.findFilteredReviewsSpecificLanguageZhCN(pageable);
                    };
                }
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findAllByNameContainingOrderByIdDescZhCN(searchWord, pageable);
                    case "local" -> repository.findAllByNewAddressContainingOrderByIdDescZhCN(searchWord, pageable);
                    default -> repository.findAllByOrderByIdDescZhCN(pageable);
                };
            }
        } else if(lang.equals("ja")){
            if (sort.equals("rating") || sort.equals("review")) {
                if (sort.equals("rating")) {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredRatingsAndSearchNameSpecificLanguageJa(searchWord, pageable);
                        case "local" -> repository.findFilteredRatingsAndSearchNewAddressSpecificLanguageJa(searchWord, pageable);
                        default -> repository.findFilteredRatingsSpecificLanguageJa(pageable);
                    };
                } else {
                    items = switch(searchType) {
                        case "name" -> repository.findFilteredReviewsAndSearchNameSpecificLanguageJa(searchWord, pageable);
                        case "local" -> repository.findFilteredReviewsAndSearchNewAddressSpecificLanguageJa(searchWord, pageable);
                        default -> repository.findFilteredReviewsSpecificLanguageJa(pageable);
                    };
                }
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findAllByNameContainingOrderByIdDescJa(searchWord, pageable);
                    case "local" -> repository.findAllByNewAddressContainingOrderByIdDescJa(searchWord, pageable);
                    default -> repository.findAllByOrderByIdDescJa(pageable);
                };
            }
        }

        return common.paginate(page, items, searchWord, searchType, sort);
    }

}
