package com.example.filestoragerest_lem.converter;

import java.util.Arrays;

public class FileExtensionChecker {
    public static boolean checkIfFileHasExtension(String s, String[] extn) {
        return Arrays.stream(extn).anyMatch(s::endsWith);
    }
}
