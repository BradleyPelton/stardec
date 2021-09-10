package com.starrydecisis.stardec.service.immutableServices;

import com.starrydecisis.stardec.model.immutableLookupTables.Description;
import com.starrydecisis.stardec.repository.immutableRepos.DescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionService {

    private static final Logger logger = LoggerFactory.getLogger(DescriptionService.class);

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public List<Description> getAllDescriptions() {
        return descriptionRepository.findAll();
    }
}
