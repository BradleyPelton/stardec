package com.starrydecisis.stardec.repository.immutableRepos;

import com.starrydecisis.stardec.model.immutableLookupTables.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, String> {
}
