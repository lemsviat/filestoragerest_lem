package com.example.filestoragerest_lem.converter;

import com.example.filestoragerest_lem.dto.PageContent;
import com.example.filestoragerest_lem.model.File;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageFileToPageContentConverter implements Converter<Page<File>, PageContent> {

    @Override
    public PageContent convert(@NotNull Page<File> files) {
        PageContent pageContent = new PageContent();
        pageContent.setTotal(files.getTotalElements());
        pageContent.setPage(files.getContent());
        return pageContent;
    }
}
