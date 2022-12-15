package com.iapps.epapers.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    private FileUtils() {
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File convertToFileAndSave(MultipartFile multipartFile) throws IOException {
        File file = new File("/app/epaper-requests/" + UUID.randomUUID() + "-" + System.currentTimeMillis() + ".xml");
        file.getParentFile().mkdirs();
        file.createNewFile();
        multipartFile.transferTo(file);
        return file;
    }

}
