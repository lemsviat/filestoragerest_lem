package com.example.filestoragerest_lem;

import com.example.filestoragerest_lem.model.MyFile;
import com.example.filestoragerest_lem.service.MyFileServiceImpl;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class FilestoragerestLemApplicationTests {

    @Test
    void contextLoads() {
    }

    private final List<String> tags = new ArrayList<>();

    @Before
    public void setUp() {

       /* ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("lem-deployment.es.europe-west3.gcp.cloud.es.io:9243")
                .withBasicAuth("elastic",
                        "m7LK5cwkkhS9BT8K1aL2bb0H")
                .build();*/
                /*.connectedTo("localhost:9200")
                .build();*/
        /*RestHighLevelClient client = RestClients.create(clientConfiguration)
                .rest();

        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(client);
        elasticsearchRestTemplate.indexOps(MyFile.class).create();*/

    }

    @Test
    public void givenUploadedFile() {

        MyFileServiceImpl myFileService = new MyFileServiceImpl();
        tags.add(0, "tag1");
        MyFile myFile1 = new MyFile("2", "Document_1", 122334, tags);
        myFileService.upload(myFile1);
        //Assertions.assertEquals("tag1", tags.get(0));
    }
}
