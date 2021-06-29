package com.example.filestoragerest_lem.repository;

import com.example.filestoragerest_lem.model.File;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FileRepository extends ElasticsearchRepository<File, String> {

    Page<File> findByTags(List<String> tags, Pageable pageable);
    @NotNull Page<File> findAll(@NotNull Pageable pageable);

}
