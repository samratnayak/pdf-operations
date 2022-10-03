package com.techlib.fileops.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class PdfUnlockService {

    public ByteArrayOutputStream unlockPdf(final InputStream ins, final String password) throws IOException {
        final var op = new ByteArrayOutputStream();
        final var pdd = PDDocument.load(ins, password);
        pdd.setAllSecurityToBeRemoved(true);
        pdd.save(op);
        pdd.close();
        log.info("Decryption done");
        return op;
    }
}
