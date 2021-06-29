package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository repository;

    @Override
    public void upload(File file) {
        repository.save(file);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void assignTags(String id, String[] tags) {
        Optional<File> myfile = repository.findById(id);
        myfile.ifPresent(file -> {
            file.setTags(Arrays.asList(tags));
            repository.save(file);
        });
    }

    @Override
    public boolean removeTags(String id, String[] tags) {
        final boolean[] succsess = {false};
        Optional<File> optionalFile = repository.findById(id);
        optionalFile.ifPresent(file -> {
            if (!file.getTags().isEmpty() && tags.length != 0
                    && !Collections.disjoint(file.getTags(), Arrays.asList(tags))) {
                file.getTags().removeAll(Arrays.asList(tags));
                repository.save(file);
                succsess[0] =true;
            }
        });
        return succsess[0];
    }

    @Override
    public Page<File> findByTags(List<String> tags, Pageable pageable) {
        return repository.findByTags(tags, pageable);
    }

    @Override
    public boolean isExistById(String id) {
        return repository.existsById(id);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}

