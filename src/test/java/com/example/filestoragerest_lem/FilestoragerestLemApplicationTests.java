package com.example.filestoragerest_lem;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.service.FileServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

        FileServiceImpl myFileService = new FileServiceImpl();
        tags.add(0, "tag1");
        File file1 = new File("2", "Document_1", 122334, tags);
        myFileService.upload(file1);
        //Assertions.assertEquals("tag1", tags.get(0));
    }
}
