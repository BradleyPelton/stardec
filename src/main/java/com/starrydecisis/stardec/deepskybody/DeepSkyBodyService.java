package com.starrydecisis.stardec.deepskybody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DeepSkyBodyService {

    private final DeepSkyBodyRepository deepSkyBodyRepository;

    @Autowired
    public DeepSkyBodyService(DeepSkyBodyRepository deepSkyBodyRepository) {
        this.deepSkyBodyRepository = deepSkyBodyRepository;
    }

    public List<DeepSkyBody> getDeepSkyBodies() {
//        Long id = 0L;  // auto increment?
//        LocalDate nowDate = LocalDate.now();
        return deepSkyBodyRepository.findAll();
    }

    public void addNewBody(DeepSkyBody newBody) {
        Optional<DeepSkyBody> bodyOptional =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(newBody.getBodyName());

        if (bodyOptional.isPresent()) {
            throw new IllegalStateException("body name already taken"); // TODO - Create custom exception
        }
        deepSkyBodyRepository.save(newBody);
    }

    public void deleteDeepSkyBody(Long bodyId) {
        boolean exists = deepSkyBodyRepository.existsById(bodyId);
        if (!exists) {
            throw new IllegalStateException("cannot delete body with bodyId = " + bodyId + " , body id does not exist");
        } else {
            deepSkyBodyRepository.deleteById(bodyId);
        }
    }

    @Transactional
    public void updateDeepSkyBody(DeepSkyBody apiBody) {
        Optional<DeepSkyBody> bodyOptional =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(apiBody.getBodyName());

        if (!bodyOptional.isPresent()) {
            throw new IllegalStateException("cannot update body with bodyId = " + apiBody.getId() + " , body id does not exist");
        } else {
            DeepSkyBody databaseBody = bodyOptional.get();
            databaseBody.setBodyName(apiBody.getBodyName());
            databaseBody.setBodyType(apiBody.getBodyType());
        }
    }
}
