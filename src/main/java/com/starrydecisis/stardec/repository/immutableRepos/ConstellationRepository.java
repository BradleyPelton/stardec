package com.starrydecisis.stardec.repository.immutableRepos;

import com.starrydecisis.stardec.model.immutableLookupTables.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConstellationRepository extends JpaRepository<Constellation,String> {
}

