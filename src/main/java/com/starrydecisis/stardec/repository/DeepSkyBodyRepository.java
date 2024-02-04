package com.starrydecisis.stardec.repository;

import com.starrydecisis.stardec.model.DeepSkyBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeepSkyBodyRepository extends JpaRepository<DeepSkyBody,Long> {


    Optional<DeepSkyBody> findDeepSkyBodyByBodyName(String bodyName);

//    Explicit JPQL Query (works). Note: TableName not table_name
//    @Query("select d from DeepSkyBody d where d.constellation = ?1")
    List<DeepSkyBody> findDeepSkyBodiesByConstellation(String constellation);

    List<DeepSkyBody> findDeepSkyBodiesByBodyType(String bodyType);
}
