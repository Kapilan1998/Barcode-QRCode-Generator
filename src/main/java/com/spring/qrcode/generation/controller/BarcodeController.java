package com.spring.qrcode.generation.controller;


import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.spring.qrcode.generation.service.BarcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/bar-code")
public class BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateBarcode(@RequestParam String text) {
        try {
            byte[] code = barcodeService.generateBarcode(text);
            return ResponseEntity.ok(code);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> decodeBarcode(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        try {
            String decodedText = barcodeService.decodeBarcode(file);
            response.put("decodedText", decodedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Failed to decode barcode.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
