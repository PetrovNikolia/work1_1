package com.example.work1_1.myFileService;

import com.example.work1_1.myFileDao.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MyFileServiceLocal {
    List<MyFile> findAll();

    Optional<MyFile> findById(UUID id);

    void deleteById(UUID id);

    void upload(MultipartFile file, UUID id) throws IOException;

    byte[] downloadFile(UUID id) throws IOException;
}
