package com.starrydecisis.stardec.repository.immutableRepos;

import com.starrydecisis.stardec.model.immutableLookupTables.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyTypeRepository extends JpaRepository<BodyType, String> {
}
