package com.example.work1_1.dto;

import com.example.work1_1.myFileDao.MyFile;

import java.util.UUID;

public class MyFileDto {

    UUID uuid;
    String fileName;
    String fileType;
    Long fileSize;
    String fileUploadFile;
    String download;

    public MyFileDto(MyFile myFile) {
        this.uuid = myFile.getId();
        this.fileName = myFile.getFileName();
        this.fileType = myFile.getFileType();
        this.fileSize = myFile.getFileSize();
        this.fileUploadFile = myFile.getFileUploadTime();
        this.download = getDownload();
    }

    public MyFileDto(UUID uuid, String fileName, String fileType, Long fileSize, String fileUploadFile, String download) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileUploadFile = fileUploadFile;
        this.download = download;
    }

    public String getDownload(){
        return "app/file" + fileName;
    }
}
