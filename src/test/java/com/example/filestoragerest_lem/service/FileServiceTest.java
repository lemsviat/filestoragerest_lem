package com.example.filestoragerest_lem.service;

import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.repository.FileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileServiceImpl fileService;


    @Test
    public void shouldReturnFileIfTheyPresent() {
        File file = new File();
        Page<File> files = new PageImpl<>(List.of(file));
        Pageable pageable = PageRequest.of(0, 1);
        when(fileRepository.findAll(pageable)).thenReturn(files);
        Page<File> foundFiles = fileService.findAll(pageable);
        assertEquals(files, foundFiles);
    }

    @Test
    public void shouldReturnTrueIfFileExist() {
        String fileId = "5";
        when(fileRepository.existsById(fileId)).thenReturn(true);
        boolean existById = fileService.isExistById(fileId);
        assertTrue(existById);
    }
}
