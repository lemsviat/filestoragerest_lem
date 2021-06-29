package com.example.filestoragerest_lem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private boolean success;
    private String error;
}
