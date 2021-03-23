package com.example.work1_1.myFileDao.repository;

import com.example.work1_1.myFileDao.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile, UUID> {
}
