package com.example.work1_1.Controller;

import com.example.work1_1.MyFileService.MyFileService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFileRestController {

    private final MyFileService myFileService;
}
