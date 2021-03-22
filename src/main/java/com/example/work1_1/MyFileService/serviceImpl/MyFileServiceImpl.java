package com.example.work1_1.MyFileService.serviceImpl;

import com.example.work1_1.MyFileDao.MyFile;
import com.example.work1_1.MyFileDao.repository.MyFileRepository;
import com.example.work1_1.MyFileService.MyFileService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyFileServiceImpl implements MyFileService {

    @Value("${upload.path}")
    String fileUploadPath;

    List

    private final MyFileRepository fileRepository;

    @Override
    public List<MyFile> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<MyFile> findById(UUID id) {
        return fileRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        fileRepository.deleteById(id);
        File localFile = findLocalFile(id);
        localFile.delete();
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String fileUploadTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        MyFile myFile = fileRepository.save(new MyFile(fileName,file.getContentType(),file.getSize(),fileUploadTime));

        File fileUploadDir = new File(fileUploadPath);
        if (!fileUploadDir.exists())
            fileUploadDir.mkdirs();

        File localFile = findLocalFile(myFile.getId());

        file.transferTo(localFile);
    }

    File findLocalFile(UUID uuid) {
        return new File(fileUploadPath + "/" + uuid);
    }
}
