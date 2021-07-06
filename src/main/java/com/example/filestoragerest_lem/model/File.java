package com.example.filestoragerest_lem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "files")
public class File {
    @Id
    private String id;

    @NotBlank
    @Field(type = FieldType.Text)
    private String name;

    @Positive
    @Field(type = FieldType.Integer)
    private int size;

    @Field(type = FieldType.Text)
    private List<String> tags;
}
