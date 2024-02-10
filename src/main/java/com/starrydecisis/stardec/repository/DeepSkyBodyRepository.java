package com.starrydecisis.stardec.repository;

import com.starrydecisis.stardec.model.DeepSkyBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeepSkyBodyRepository extends JpaRepository<DeepSkyBody,Long> {


    @Modifying
    @Query("update DeepSkyBody d set d.otherName = ?3 , d.bodyType = ?4 , d.constellation = ?5 , d.description = ?6 , d.notes = ?7  where d.id = ?1 and d.bodyName = ?2")
    void updateDeepSkyBodyByBodyName(Long bodyId,
                                     String bodyName,
                                     String otherName,
                                     String bodyType,
                                     String constellation,
                                     String description,
                                     String notes);

    Optional<DeepSkyBody> findDeepSkyBodyByBodyName(String bodyName);

//    Explicit JPQL Query (works). Note: "TableName" not "table_name"
//    @Query("select d from DeepSkyBody d where d.constellation = ?1")
    List<DeepSkyBody> findDeepSkyBodiesByConstellation(String constellation);

    List<DeepSkyBody> findDeepSkyBodiesByBodyType(String bodyType);
}
