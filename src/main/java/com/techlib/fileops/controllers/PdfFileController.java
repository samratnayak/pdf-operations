package com.techlib.fileops.controllers;

import com.techlib.fileops.services.PdfUnlockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
@Slf4j
public class PdfFileController {

    @Autowired
    private PdfUnlockService service;

    @PostMapping(value = "/unlock", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> unlockPDF(@RequestPart MultipartFile file, @RequestPart String password) throws IOException {
        log.info("unlocking pdf file {}", file.getOriginalFilename());
        final var byteArrayOp = service.unlockPdf(file.getInputStream(), password);
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArrayOp.toByteArray()));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getOriginalFilename() +"_unlocked_file.pdf")
                .body(inputStreamResource);
    }
}
