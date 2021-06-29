package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository repository;

    @Override
    public void upload(File file) {
        repository.save(file);
    }

    public void delete(String ID) {
        repository.deleteById(ID);
    }

    @Override
    public void assignTags(String ID, String[] tags) {
        Optional<File> myFile = repository.findById(ID);
        myFile.ifPresent(file -> {
            file.setTags(Arrays.asList(tags));
            repository.save(file);
        });
    }

    @Override
    public void removeTags(String ID, String[] tags) {
        Optional<File> myFile = repository.findById(ID);
        myFile.ifPresent(file -> {
            file.getTags().removeAll(Arrays.asList(tags));
            repository.save(file);
        });
    }

    @Override
    public Page<File> findByTags(List<String> tags, Pageable pageable) {
        return repository.findByTags(tags, pageable);
    }

    @Override
    public boolean isExistById(String ID) {
        return repository.existsById(ID);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}

