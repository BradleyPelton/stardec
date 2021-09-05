package com.starrydecisis.stardec;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(System.getenv("starDecElasticUrl"))
                .build();

        return RestClients.create(clientConfiguration).rest();
//        final String stringUrl = System.getenv("starDecElasticUrl");
//        final URI uri = URI.create(stringUrl);
//
//        String host = uri.getHost();
//        int port = uri.getPort() == -1 ? 9200 : uri.getPort();
//        final ClientConfiguration.MaybeSecureClientConfigurationBuilder builder = ClientConfiguration.builder().connectedTo(host + ":" + port);

        // enable SSL if https is being used in the URL
//        boolean isSsl = "https".equals(uri.getScheme());
//        if (isSsl) {
//            builder.usingSsl();
//        }

        // enable basic auth if specified
//        String userInfo = uri.getUserInfo();
//        if (userInfo != null) {
//            final String[] userPass = userInfo.split(":", 2);
//            builder.withBasicAuth(userPass[0], userPass[1]);
//        }

//        logger.info("Elasticsearch server [{}:{}] ssl[{}] auth[{}]", host, port, isSsl, userInfo != null);
//        return RestClients.create(builder.build()).rest();
    }



//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {  // discoverable because bean. Thread safe. Only one client needed

//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(System.getenv("starDecElasticUrl"))
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }

//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
}