package com.example.filestoragerest_lem;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FilestoragerestLemApplicationTests {

    private final List<String> tags = new ArrayList<>();

    @Before
    public void setUp() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        RestHighLevelClient client = RestClients.create(clientConfiguration)
                .rest();
    }

    @Test
    public void givenUploadedFile() {
        //Assertions.assertEquals(.......);
    }
}
