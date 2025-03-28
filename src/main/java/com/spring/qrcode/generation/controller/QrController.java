package com.spring.qrcode.generation.controller;


import com.google.zxing.WriterException;
import com.spring.qrcode.generation.service.QrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/qr-code")
public class QrController {

    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) {
        try {
            byte[] code = qrService.generateQRCode(text);
            return ResponseEntity.ok(code);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String,String>> decodeQRCode(@RequestParam("file") MultipartFile file) {
        Map<String,String> response = new HashMap<>();
        try {
            String decodedText = qrService.decodeQRCode(file);
            response.put("decodedText", decodedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Failed to decode QR code.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);        }
    }


}
