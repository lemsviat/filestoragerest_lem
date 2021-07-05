package com.example.filestoragerest_lem.dto;

import com.example.filestoragerest_lem.model.File;
import lombok.Data;

import java.util.List;

@Data
public class PageContent {
    private Long total;
    List<File> page;

}
