package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.MyFile;
import com.example.filestoragerest_lem.model.Tags;
import com.example.filestoragerest_lem.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyFileServiceImpl implements MyFileService {
    @Autowired
    private MyRepository repository;

    @Override
    public void upload(MyFile myFile) {
        repository.save(myFile);
    }

    public void delete(String ID) {
        repository.deleteById(ID);
    }

    @Override
    public void assignTags(String ID, String[] tags) {
        Optional<MyFile> myFile = repository.findById(ID);
        myFile.ifPresent(file -> {
            file.setTags(Arrays.asList(tags));
            repository.save(file);
        });
    }

    @Override
    public void removeTags(String ID, String[] tags) {
        Optional<MyFile> myFile = repository.findById(ID);
        myFile.ifPresent(file -> {
            file.getTags().removeAll(Arrays.asList(tags));
            repository.save(file);
        });
    }

    @Override
    public Page<MyFile> findByTags(List<String> tags, Pageable pageable) {
        return repository.findByTags(tags, pageable);
    }

    @Override
    public boolean isExistById(String ID) {
        return repository.existsById(ID);
    }

    @Override
    public Page<MyFile> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}

