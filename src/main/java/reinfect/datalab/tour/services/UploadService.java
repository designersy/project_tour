package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.Upload;

public interface UploadService {

    void register(Upload upload);
    void delete(Long id);

}
