package com.starrydecisis.stardec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@ComponentScan("com.starrydecisis.stardec.deepskybodyRestClientConfig")
//@ComponentScan({"com.starrydecisis.stardec.bodysearch",
//		"com.starrydecisis.stardec.deepskybody",
//		"com.starrydecisis.stardec.deepskybodyRestClientConfig"})
//@ComponentScan(basePackageClasses = {ExampleController.class, ExampleModel.class, ExmapleView.class})
//@EnableJpaRepositories(basePackages = { "com"})
//@EnableJpaRepositories("com.starrydecisis.stardec.deepskybody.DeepSkyBodyRepository")
//@EnableElasticsearchRepositories("com.starrydecisis.stardec.bodysearch.BodySearchRepository")



@SpringBootApplication
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchRepository.class))
@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchRepository.class))
public class StardecApplication {

	public static void main(String[] args) {
		SpringApplication.run(StardecApplication.class, args);
	}
}
