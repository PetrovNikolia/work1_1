package com.example.work1_1.controller;

import com.example.work1_1.myFileDao.MyFile;
import com.example.work1_1.myFileService.MyFileServiceDB;
import com.example.work1_1.myFileService.MyFileServiceLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/file")
@RestController
@RequiredArgsConstructor
public class MyFileRestController {

    private final MyFileServiceLocal myFileServiceLocal;
    private final MyFileServiceDB myFileServiceDB;

    @GetMapping(path = "/{id}")
    public Optional<MyFile> getFile(@PathVariable UUID id){
        return myFileServiceDB.findById(id);
    }

    @GetMapping(path = "/all")
    public List<MyFile> getAllFile(){
        return myFileServiceDB.findAll();
    }

    @PostMapping("/upload")
    public void uploadFileLocal(@RequestBody MultipartFile file) throws IOException {
        myFileServiceDB.upload(file);
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@RequestParam UUID id) throws IOException {
        MyFile myFile = myFileServiceDB.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(myFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + myFile.getFileName())
                .body(new ByteArrayResource(myFileServiceDB.downloadFile(id)));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteFile(@PathVariable UUID id){
        myFileServiceLocal.deleteById(id);
    }
}
