package com.starrydecisis.stardec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeepSkyBodyConfig {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyConfig.class);


//    @Bean
//    CommandLineRunner commandLineRunner(DeepSkyBodyRepository repository) {
//        return args -> {
//            DeepSkyBody andromeda = new DeepSkyBody(
//                "NGC  224",    // NGC 224 = Andromeda
//                "M  31",
//                "GALXY",
//                "AND"
//            );
//            DeepSkyBody Orion = new DeepSkyBody(
//                    "NGC 1976",    // NGC 1976 = Orion Nebula
//                    "M  42",
//                    "CL+NB",
//                    "ORI"
//            );
//            DeepSkyBody Crab = new DeepSkyBody(
//                    "NGC 1952",  // NGC 1952 = Crab Nebula
//                    "M   1",
//                    "SNREM",
//                    "TAU"
//            );
//
//            repository.saveAll(   // TODO remove me
//                    List.of(andromeda,Orion,Crab)
//            );
//
//        };
//    }
}
