package com.kitrum.task.manager.ftp;

import java.io.InputStream;

public interface FtpService {

    String storeFile(InputStream inputStream, String filename);
}
