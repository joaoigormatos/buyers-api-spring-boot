package com.jimds.buyers.storage;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

public interface IStorage {
    public String storeFile(MultipartFile file) throws IOException;

    public Resource loadFileAsResource(String fileName);
}
