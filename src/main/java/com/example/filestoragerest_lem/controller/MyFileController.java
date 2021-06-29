package com.example.filestoragerest_lem.controller;

import com.example.filestoragerest_lem.model.MyFile;
import com.example.filestoragerest_lem.model.Tags;
import com.example.filestoragerest_lem.service.MyFileServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class MyFileController {
    private final MyFileServiceImpl myFileService;

    public MyFileController(MyFileServiceImpl myFileService) {
        this.myFileService = myFileService;
    }

    @PostMapping("/file")
    public String upload(@Valid @RequestBody MyFile myFile) {
        myFileService.upload(myFile);
        return "ID: " + myFile.getId();
    }

    @DeleteMapping("/file/{ID}")
    public HttpStatus delete(@PathVariable("ID") String id) {
        if (myFileService.isExistById(id)) {
            myFileService.delete(id);
            return HttpStatus.OK;
        } else return HttpStatus.NOT_FOUND;

       /*     return "success : true";
        } else return "success : false,\n" +
                "error : file not found";*/
    }

    @PostMapping(value = "/file/{ID}/tags")
    public String assignTags(@PathVariable("ID") String id, @Valid @RequestBody Tags tagsIn) {
        myFileService.assignTags(id, tagsIn);
        return "success : true";
    }

    /*@PostMapping(value = "/file/{ID}/tags2")
    public String assignTags2(@PathVariable("ID") String id, @Valid @RequestBody String [] tagsIn) {
        //myFileService.assignTags(id, tagsIn);
        return "success : true";
    }*/

    @DeleteMapping(value = "/file/{ID}/tags")
    public String removeTags(@PathVariable("ID") String id, @Valid @RequestBody Tags tagsIn) {
        myFileService.removeTags(id, tagsIn);
        return "success : true";
    }

    @GetMapping (value = "/file?tags={tags}&page={page}&size={size}")
    public Page<MyFile> getByTags(@PathVariable List<String> tags, @PathVariable Integer page,
                                  @PathVariable Integer size) {
    //public Page<MyFile> getByTags (@PageableDefault(value = 10, page = 0) Pageable pageable) {
        Pageable pageable = PageRequest.of(page, size);
        return myFileService.findByTags(tags, pageable);
    }


}
