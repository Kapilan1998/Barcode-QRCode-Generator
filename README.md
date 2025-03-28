# Barcode-QRCode-Generator
generating qr code &amp; bar code and decoding them


<!-- ZXing core library for QR code and barcode generation -->
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.5.3</version>
</dependency>

<!-- ZXing Java SE (Standard Edition) library for QR code and barcode image rendering -->
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.5.3</version>
</dependency>


The core dependency is for data encoding and decoding of QR codes and barcodes.

The javase dependency is for image generation of those QR codes or barcodes so that they can be displayed or transmitted as image files.