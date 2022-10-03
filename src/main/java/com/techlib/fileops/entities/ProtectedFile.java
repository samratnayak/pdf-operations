package com.techlib.fileops.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProtectedFile {
    private MultipartFile file;
    private String password;
}
