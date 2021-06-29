package com.example.filestoragerest_lem;

import org.elasticsearch.client.RestHighLevelClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.filestoragerest_lem.repository")
@ComponentScan(basePackages = { "com.example.filestoragerest_lem.service" })
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public @NotNull RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
               /* .connectedTo("lem-deployment.es.europe-west3.gcp.cloud.es.io:9243")
                .withBasicAuth("elastic",
                        "m7LK5cwkkhS9BT8K1aL2bb0H")
                .build();*/
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}




