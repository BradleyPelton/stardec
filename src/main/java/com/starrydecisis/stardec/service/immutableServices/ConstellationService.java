package com.starrydecisis.stardec.service.immutableServices;

import com.starrydecisis.stardec.model.immutableLookupTables.Constellation;
import com.starrydecisis.stardec.repository.immutableRepos.ConstellationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstellationService {

    private static final Logger logger = LoggerFactory.getLogger(ConstellationService.class);

    private final ConstellationRepository constellationRepository;

    @Autowired
    public ConstellationService(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public List<Constellation> getAllConstellations() {
        return constellationRepository.findAll();
    }
}
