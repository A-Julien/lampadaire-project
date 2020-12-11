package com.lampaderum.web.rest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.lampaderum.service.AwsClientService;
import com.lampaderum.service.StreetlampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class AwsBucketController {
    private final AwsClientService amazonClient;

    @Autowired
    public AwsBucketController(AwsClientService amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
