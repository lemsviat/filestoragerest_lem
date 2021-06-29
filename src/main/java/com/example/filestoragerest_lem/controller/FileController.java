package com.example.filestoragerest_lem.controller;
import com.example.filestoragerest_lem.model.File;
import com.example.filestoragerest_lem.service.FileServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public String upload(@Valid @RequestBody File file) {
        myFileService.upload(file);
        return "ID: " + file.getId();
    }

    @DeleteMapping("/file/{ID}")
    public HttpStatus delete(@PathVariable("ID") String id) {
        if (myFileService.isExistById(id)) {
            myFileService.delete(id);
            return HttpStatus.OK;
        } else return HttpStatus.NOT_FOUND;
    }

    @PostMapping(value = "/file/{ID}/tags")
    public String assignTags(@PathVariable("ID") String id, @Valid @RequestBody String [] tagsIn) {
        myFileService.assignTags(id, tagsIn);
        return "success : true";
    }

    @DeleteMapping(value = "/file/{ID}/tags")
    public String removeTags(@PathVariable("ID") String id, @Valid @RequestBody String[] tagsIn) {
        myFileService.removeTags(id, tagsIn);
        return "success : true";

    }

    @GetMapping (value = "/file")
    public Page<File> getByTags(@RequestParam (defaultValue = "") String [] tags, @RequestParam (defaultValue = "0") Integer page,
                                @RequestParam (defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        if(tags.length!=0) return myFileService.findByTags(Arrays.asList(tags), pageable);
        else return myFileService.findAll(pageable);
    }


}
