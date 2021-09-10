package com.starrydecisis.stardec.service.immutableServices;

import com.starrydecisis.stardec.model.immutableLookupTables.BodyName;
import com.starrydecisis.stardec.repository.immutableRepos.BodyNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyNameService {

    private static final Logger logger = LoggerFactory.getLogger(BodyNameService.class);

    private final BodyNameRepository bodyNameRepository;

    @Autowired
    public BodyNameService(BodyNameRepository bodyNameRepository) {
        this.bodyNameRepository = bodyNameRepository;
    }

    public List<BodyName> getAllBodyNames() {
        return bodyNameRepository.findAll();
    }
}
