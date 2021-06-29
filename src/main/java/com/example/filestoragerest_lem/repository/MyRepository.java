package com.example.filestoragerest_lem.repository;

import com.example.filestoragerest_lem.model.MyFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MyRepository extends ElasticsearchRepository<MyFile, String> {

    Page<MyFile> findByTags(List<String> tags, Pageable pageable);

}
