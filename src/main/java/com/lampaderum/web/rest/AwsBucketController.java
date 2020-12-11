package com.lampaderum.web.rest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.lampaderum.service.AwsClientService;
import com.lampaderum.service.StreetlampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;

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

    @GetMapping("/api/file/{keyname}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
        ByteArrayOutputStream downloadInputStream = this.amazonClient.downloadFile(keyname);

        return ResponseEntity.ok()
            .contentType(contentType(keyname))
            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
            .body(downloadInputStream.toByteArray());
    }

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length - 1];
        switch (type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
