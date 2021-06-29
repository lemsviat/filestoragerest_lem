package com.example.filestoragerest_lem.controller;

import com.example.filestoragerest_lem.dto.ApiError;
import com.example.filestoragerest_lem.dto.ApiMessage;
import com.example.filestoragerest_lem.dto.ApiSuccess;
import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.service.FileServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/")
public class FileController {
    private final FileServiceImpl myFileService;

    public FileController(FileServiceImpl myFileService) {
        this.myFileService = myFileService;
    }

    @PostMapping("/file")
    public ResponseEntity upload(@Valid @RequestBody File file) {
        myFileService.upload(file);
        return new ResponseEntity<>(new ApiMessage(file.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/file/{ID}")
    public ResponseEntity delete(@PathVariable("ID") String id) {
        if (myFileService.isExistById(id)) {
            myFileService.delete(id);
            return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiError(false, "file not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/file/{ID}/tags")
    public ResponseEntity<ApiSuccess> assignTags(@PathVariable("ID") String id, @Valid @RequestBody String[] tags) {
        myFileService.assignTags(id, tags);
        return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
    }

    @DeleteMapping(value = "/file/{ID}/tags")
    public ResponseEntity removeTags(@PathVariable("ID") String id, @Valid @RequestBody String[] tags) {
        if (myFileService.removeTags(id, tags))
            return new ResponseEntity<>(new ApiSuccess(true), HttpStatus.OK);
        else return new ResponseEntity<>(new ApiError(false, "tag not found on file"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/file")
    public Page<File> getByTags(@RequestParam(defaultValue = "") String[] tags, @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        if (tags.length != 0) return myFileService.findByTags(Arrays.asList(tags), pageable);
        else return myFileService.findAll(pageable);
    }


}
