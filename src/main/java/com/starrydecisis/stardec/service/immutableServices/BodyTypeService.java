package com.starrydecisis.stardec.service.immutableServices;

import com.starrydecisis.stardec.model.immutableLookupTables.BodyType;
import com.starrydecisis.stardec.repository.immutableRepos.BodyTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyTypeService {

    private static final Logger logger = LoggerFactory.getLogger(BodyTypeService.class);

    private final BodyTypeRepository bodyTypeRepository;

    @Autowired
    public BodyTypeService(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    public List<BodyType> getAllBodyTypes() {
        return bodyTypeRepository.findAll();
    }
}
