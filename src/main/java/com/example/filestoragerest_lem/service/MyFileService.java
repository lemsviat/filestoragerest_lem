package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.MyFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyFileService {

    void upload(MyFile myFile);

    void delete(String ID);

    void assignTags(String ID, String[] tags);

    void removeTags(String ID, String[] tags);

    Page<MyFile> findByTags(List<String> tags, Pageable pageable);

    boolean isExistById(String ID);

    Page<MyFile> findAll(Pageable pageable);

}
