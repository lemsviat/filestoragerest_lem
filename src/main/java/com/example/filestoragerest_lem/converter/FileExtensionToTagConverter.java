package com.example.filestoragerest_lem.converter;

import com.example.filestoragerest_lem.model.File;

import java.util.Arrays;

public class FileExtensionToTagConverter {
    public static String[] getTagByExtension(File file) {
        String[] tags = new String[0];
        String[] musicExtension = new String[]{".mp3", ".aac", ".wav", ".flac"};
        String[] videoExtension = new String[]{".mp4", ".avi", ".mov", ".wmv"};
        String[] documentExtension = new String[]{".pdf", ".doc", ".txt", ".epub"};
        String[] imageExtension = new String[]{".jpeg", ".tiff", ".gif", ".png"};
        String[] music = new String[]{"music"};
        String[] video = new String[]{"video"};
        String[] document = new String[]{"document"};
        String[] image = new String[]{"image"};

        if (FileExtensionToTagConverter.convertExtensionToTag(file.getName(), musicExtension)) {
            tags = music;
        } else if (FileExtensionToTagConverter.convertExtensionToTag(file.getName(), videoExtension)) {
            tags = video;
        } else if (FileExtensionToTagConverter.convertExtensionToTag(file.getName(), documentExtension)) {
            tags = document;
        } else if (FileExtensionToTagConverter.convertExtensionToTag(file.getName(), imageExtension)) {
            tags = image;
        }
        return tags;
    }

    private static boolean convertExtensionToTag(String name, String[] extn) {
        return Arrays.stream(extn).anyMatch(name::endsWith);
    }

}
