package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileService {

    void upload(File file);

    void delete(String ID);

    void assignTags(String ID, String[] tags);

    void removeTags(String ID, String[] tags);

    Page<File> findByTags(List<String> tags, Pageable pageable);

    boolean isExistById(String ID);

    Page<File> findAll(Pageable pageable);

}
