package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileService {

    void upload(File file);

    void delete(String id);

    void assignTags(String id, String[] tags);

    boolean removeTags(String id, String[] tags);

    Page<File> findByTags(List<String> tags, Pageable pageable);

    boolean isExistById(String id);

    Page<File> findAll(Pageable pageable);

}
