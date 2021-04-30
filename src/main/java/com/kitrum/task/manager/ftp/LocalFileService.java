package com.kitrum.task.manager.ftp;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileService implements FtpService {

    @SneakyThrows
    @Override
    public String storeFile(InputStream inputStream, String filename) {
        String path = System.getProperty("user.dir") + File.separator + StringUtils.cleanPath(filename);
        Path copyLocation = Paths
                .get(path);
        Files.copy(inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING);

        return path;
    }
}
