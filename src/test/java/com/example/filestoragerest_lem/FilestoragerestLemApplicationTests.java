package com.example.filestoragerest_lem;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.service.FileServiceImpl;
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

        FileServiceImpl myFileService = new FileServiceImpl();
        tags.add(0, "tag1");
        File file1 = new File("2", "Document_1", 122334, tags);
        myFileService.upload(file1);
        //Assertions.assertEquals(.......);
    }
}
