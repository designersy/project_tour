package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reinfect.datalab.tour.entities.Banner;
import reinfect.datalab.tour.entities.Upload;
import reinfect.datalab.tour.http.forms.BannerForm;
import reinfect.datalab.tour.repositories.BannerRepository;
import reinfect.datalab.tour.utilities.Common;
import reinfect.datalab.tour.utilities.Uploader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IBannerService implements BannerService {

    private final Common common;
    private final Uploader uploader;

    private final UploadService uploadService;

    private final BannerRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(MultipartFile banner, BannerForm form) throws Exception {
        try {
            Map<String, Object> uploaded = uploader.uploadS3(banner, "/banners");

            uploadService.register(
                Upload.builder().original(uploaded.get("originalFileName").toString())
                                .uploaded(uploaded.get("uploadedFileName").toString())
                                .path(uploaded.get("path").toString())
                                .size((Long) uploaded.get("size"))
                                .build()
            );

            repository.save(
                    Banner.builder().subject(form.getSubject())
                                    .link(form.getLink())
                                    .comment(form.getComment())
                                    .image(uploaded.get("uploadedFileName").toString())
                                    .build()
            );
        } catch (IOException exception) {
            throw new Exception(exception.getMessage());
        } catch (DataAccessException exception) {
            throw new Exception("배너 정보 등록 중 문제가 발생했습니다.");
        }
    }

    @Override
    public void update(MultipartFile file, BannerForm form, Long id) throws Exception {
        String uploadedFileName = "";

        if (!file.isEmpty()) {
            Map<String, Object> uploaded = uploader.uploadS3(file, "/banners");
            uploadedFileName = uploaded.get("uploadedFileName").toString();

            uploadService.register(
                    Upload.builder().original(uploaded.get("originalFileName").toString())
                                    .uploaded(uploaded.get("uploadedFileName").toString())
                                    .path(uploaded.get("path").toString())
                                    .size((Long) uploaded.get("size"))
                                    .build()
            );
        }

        Banner data = currentItem(id);

        data.setSubject(form.getSubject());
        data.setComment(form.getComment());
        data.setLink(form.getLink());

        if (!uploadedFileName.isBlank()) data.setImage(uploadedFileName);

        repository.save(data);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public Banner currentItem(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("데이터가 없습니다."));
    }

    @Override
    public List<Banner> allItems() {
        return repository.findAll();
    }

    @Override
    public Map<String, Object> paginatedItems(int page, int perPage) {
        Pageable pageable = PageRequest.of((page - 1), perPage);
        Page<Banner> items = repository.findAllByOrderByIdDesc(pageable);

        return common.paginate(page, items, "", "", "");
    }

}
