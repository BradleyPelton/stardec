package com.starrydecisis.stardec.bodysearch;

import com.starrydecisis.stardec.deepskybody.DeepSkyBody;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySearchRepository extends ElasticsearchRepository<DeepSkyBody, Long> {
}
