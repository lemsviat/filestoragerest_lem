package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.repository.FileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public boolean assignTags(String id, String[] tags) {
        final boolean[] success = {false};
        Optional<File> myfile = repository.findById(id);
        myfile.ifPresent(file -> {
            file.setTags(Stream.of(file.getTags(), Arrays.asList(tags))
                    .flatMap(Collection::stream).distinct()
                    .collect(Collectors.toList()));
            repository.save(file);
            success[0] =true;
        });
        return success[0];
    }

    @Override
    public boolean removeTags(String id, String[] tags) {
        final boolean[] success = {false};
        Optional<File> optionalFile = repository.findById(id);
        optionalFile.ifPresent(file -> {
            if (!file.getTags().isEmpty() && tags.length != 0
                    && !Collections.disjoint(file.getTags(), Arrays.asList(tags))) {
                file.getTags().removeAll(Arrays.asList(tags));
                repository.save(file);
                success[0] = true;
            }
        });
        return success[0];
    }

    @Override
    public Page<File> findByTags(List<String> tags, Pageable pageable) {
        return repository.findByTags(tags, pageable);
    }

    @Override
    public boolean isExistById(@NonNull String id) {
        return repository.existsById(id);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}

