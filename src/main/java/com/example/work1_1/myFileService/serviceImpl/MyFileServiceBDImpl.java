package com.example.work1_1.myFileService.serviceImpl;

import com.example.work1_1.myFileDao.MyFile;
import com.example.work1_1.myFileDao.repository.MyFileRepository;
import com.example.work1_1.myFileService.MyFileServiceDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyFileServiceBDImpl implements MyFileServiceDB {

    private final MyFileRepository fileRepository;

    private final MyFileServiceLocalImpl local;

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
        fileRepository.deleteById(id);
    }

    @Override
    public void upload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        String fileUploadTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        MyFile myFile = new MyFile(fileName,file.getContentType(),file.getSize(),fileUploadTime);

        MyFile save = fileRepository.save(myFile);

        local.upload(file,save.getId());

    }

    @Override
    public byte[] downloadFile(UUID id) throws IOException {
        return local.downloadFile(id);
    }

    @Override
    public MyFile getFile(UUID id) {
        return fileRepository.getOne(id);
    }


}
