package com.starrydecisis.stardec.repository;

import com.starrydecisis.stardec.model.DeepSkyBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeepSkyBodyRepository extends JpaRepository<DeepSkyBody,Long> {


//    @Query("SELECT dsb FROM deep_sky_body dsb WHERE dsb.bodyName = ?1")
    Optional<DeepSkyBody> findDeepSkyBodyByBodyName(String bodyName);

}
