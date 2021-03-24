package com.example.work1_1.myFileService.serviceImpl;

import com.example.work1_1.myFileDao.MyFile;
import com.example.work1_1.myFileDao.repository.MyFileRepository;
import com.example.work1_1.myFileService.MyFileServiceLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyFileServiceLocalImpl implements MyFileServiceLocal {

    @Value("${upload.path}")
    String fileUploadPath;

    private final MyFileRepository fileRepository;

    @Override
    public List<MyFile> findAll() {
        return null;
    }

    @Override
    public Optional<MyFile> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID id) {
        File localFile = new File(findLocalFile(id));
        localFile.delete();
    }

    @Override
    public void upload(MultipartFile file, UUID id) {


        try {
            byte[] data = file.getBytes();
            Path path =  Paths.get(findLocalFile(id));
            Files.write(path,data);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] downloadFile(UUID id) throws IOException {
        Path path = Paths.get(findLocalFile(id));
        return Files.readAllBytes(path);

    }

    public String findLocalFile(UUID id){
        return fileUploadPath + "/" + id.toString();
    }
}
