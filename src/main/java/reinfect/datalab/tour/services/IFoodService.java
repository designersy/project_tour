package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Food;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.http.forms.FoodForm;
import reinfect.datalab.tour.repositories.FoodRepository;
import reinfect.datalab.tour.utilities.Common;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IFoodService implements FoodService {

    private final Common common;

    private final FoodRepository repository;

    @Override
    public void register(FoodForm form) {
        String position = "{x:" + form.getPositionX() + ", y:" + form.getPositionY() + "}";

        repository.save(
            Food.builder().name(form.getName())
                          .language(form.getLanguage())
                          .contentUrl(form.getContentUrl())
                          .address(form.getAddress())
                          .newAddress(form.getNewAddress())
                          .telephone(form.getTelephone())
                          .website(form.getWebsite())
                          .businessTime(form.getBusinessTime())
                          .access(form.getAccess())
                          .mainFood(form.getMainFood())
                          .position(position)
                          .build()
        );
    }

    @Override
    public void update(FoodForm form, Long id) throws Exception {
        Food food = currentItem(id);

        food.setName(form.getName());
        food.setLanguage(form.getLanguage());
        food.setContentUrl(form.getContentUrl());
        food.setAddress(form.getAddress());
        food.setNewAddress(form.getNewAddress());
        food.setTelephone(form.getTelephone());
        food.setWebsite(form.getWebsite());
        food.setBusinessTime(form.getBusinessTime());
        food.setAccess(form.getAccess());
        food.setMainFood(form.getMainFood());

        repository.save(food);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public Food currentItem(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("데이터가 없습니다."));
    }

    @Override
    public List<Food> latest(LatestType latestType) {
        if (latestType == LatestType.RANDOM) return repository.findLatestRandom();
        return repository.findTop10OrderByOrderByIdDesc();
    }

    @Override
    public Map<String, Object> paginatedItems(int page, int perPage, String searchType, String searchWord, String sort) {
        Pageable pageable = PageRequest.of(page-1, perPage);
        Page<Food> items;

        if (sort.equals("rating") || sort.equals("review")) {
            if (sort.equals("rating")) {
                items = switch(searchType) {
                    case "name" -> repository.findFilteredRatingsAndSearchName(searchWord, pageable);
                    case "local" -> repository.findFilteredRatingsAndSearchNewAddress(searchWord, pageable);
                    case "food" -> repository.findFilteredRatingsAndSearchMainFood(searchWord, pageable);
                    default -> repository.findFilteredRatings(pageable);
                };
            } else {
                items = switch(searchType) {
                    case "name" -> repository.findFilteredReviewsAndSearchName(searchWord, pageable);
                    case "local" -> repository.findFilteredReviewsAndSearchNewAddress(searchWord, pageable);
                    case "food" -> repository.findFilteredReviewsAndSearchMainFood(searchWord, pageable);
                    default -> repository.findFilteredReviews(pageable);
                };
            }
        } else {
            items = switch(searchType) {
                case "name" -> repository.findAllByNameContainingOrderByIdDesc(searchWord, pageable);
                case "local" -> repository.findAllByNewAddressContainingOrderByIdDesc(searchWord, pageable);
                case "food" -> repository.findAllByMainFoodContainingOrderByIdDesc(searchWord, pageable);
                default -> repository.findAllByOrderByIdDesc(pageable);
            };
        }

        return common.paginate(page, items, searchWord, searchType, sort);
    }

    @Override
    public List<Food> findAll() {
        List<Food> foodList = repository.findAll();
        return foodList;
    }

}
