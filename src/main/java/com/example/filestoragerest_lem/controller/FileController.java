package com.example.filestoragerest_lem.controller;

import com.example.filestoragerest_lem.converter.FileExtensionToTagConverter;
import com.example.filestoragerest_lem.converter.PageFileToPageContentConverter;
import com.example.filestoragerest_lem.dto.ApiError;
import com.example.filestoragerest_lem.dto.ApiMessage;
import com.example.filestoragerest_lem.dto.ApiSuccess;
import com.example.filestoragerest_lem.dto.PageContent;
import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class FileController {
    private final FileService fileService;
    private final PageFileToPageContentConverter converter;

    @PostMapping("/file")
    public ResponseEntity upload(@Valid @RequestBody File file) {
        fileService.upload(file);
        fileService.assignTags(file.getId(), FileExtensionToTagConverter.getTagByExtension(file));
        return new ResponseEntity<>(new ApiMessage(file.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/file/{ID}")
    public ResponseEntity delete(@PathVariable("ID") String id) {
        if (fileService.isExistById(id)) {
            fileService.delete(id);
            return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiError(false, "file not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/file/{ID}/tags")
    public ResponseEntity assignTags(@PathVariable("ID") String id, @Valid @RequestBody String[] tags) {
        if (fileService.assignTags(id, tags)) {
            return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiError(false, "file not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/file/{ID}/tags")
    public ResponseEntity removeTags(@PathVariable("ID") String id, @Valid @RequestBody String[] tags) {
        if (fileService.removeTags(id, tags)) {
            return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiError(false, "tag not found on file"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/file")
    public PageContent getByTags(@RequestParam(defaultValue = "") String[] tags, @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String q) {
        Pageable pageable = PageRequest.of(page, size);
        if (q != null) {
            return converter.convert(fileService.findByName(q, pageable));
        }
        if (tags.length != 0) {
            return converter.convert(fileService.findByTags(Arrays.asList(tags), pageable));
        } else {
            return converter.convert(fileService.findAll(pageable));
        }
    }
}
