package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Upload;
import reinfect.datalab.tour.repositories.UploadRepository;

@Service
@RequiredArgsConstructor
public class IUploadService implements UploadService {

    private final UploadRepository repository;

    @Override
    public void register(Upload upload) {
        repository.save(upload);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

}
