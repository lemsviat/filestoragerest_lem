package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.MyFile;
import com.example.filestoragerest_lem.model.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyFileService {

    void upload(MyFile myFile);

    void delete(String ID);

    void assignTags(String ID, Tags tags);

    void removeTags(String ID, Tags tags);

    Page<MyFile> findByTags(List<String> tags, Pageable pageable);

    boolean isExistById(String ID);

}
