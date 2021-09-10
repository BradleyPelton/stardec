package com.starrydecisis.stardec.repository.immutableRepos;

import com.starrydecisis.stardec.model.immutableLookupTables.BodyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyNameRepository extends JpaRepository<BodyName, String> {
}
