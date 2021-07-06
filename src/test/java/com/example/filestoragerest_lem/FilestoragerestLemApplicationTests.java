package com.example.filestoragerest_lem;

import com.example.filestoragerest_lem.model.File;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;

@SpringBootTest
class FilestoragerestLemApplicationTests {

    @Before
    public RestHighLevelClient setUp() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration)
                .rest();
    }

    @Test
    public void givenJsonString_whenJavaObject_thenIndexDocument() throws IOException {
        String jsonObject = "{\"name\":\"Natasha2.flac\",\"size\":1,\"tags\":[\"tag4\"]}";
        IndexRequest request = new IndexRequest("myfiles");
        request.source(jsonObject, XContentType.JSON);

        IndexResponse response = setUp().index(request, RequestOptions.DEFAULT);
        String index = response.getIndex();
        long version = response.getVersion();

        Assertions.assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
        Assertions.assertEquals(1, version);
        Assertions.assertEquals("myfiles", index);
    }

    @Test
    public void givenSearchRequest_whenMatchAll_thenReturnAllResults() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse response = setUp().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits()
                .getHits();
        List<File> results = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            File parseObject = JSON.parseObject(hit.getSourceAsString(), File.class);
            results.add(parseObject);
        }

        results.forEach(System.out::println);
    }
}
