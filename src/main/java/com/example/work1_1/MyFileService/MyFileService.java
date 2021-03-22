package com.example.work1_1.MyFileService;

import com.example.work1_1.Dto.MyFileDto;
import com.example.work1_1.MyFileDao.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MyFileService {

    List<MyFile> findAll();

    Optional<MyFile> findById(UUID id);

    void deleteById(UUID id);

    void upload(MultipartFile file) throws IOException;
}
