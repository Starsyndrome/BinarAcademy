package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.binaracademy.challenge4.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;


@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping(value = "/generate-invoice", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateInvoice(@RequestHeader("username") String username) throws JRException, FileNotFoundException {
        return ResponseEntity.ok()
                .body(invoiceService.generateInvoice(username));
    }
}