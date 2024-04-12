package com.general.controller;

import com.general.dto.administration.NewLetterTypeRequest;
import com.general.service.AdministrationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.OutputStream;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("administration")
@RestController
public class AdministrationController {
    private final AdministrationService administrationService;

    @PostMapping("/letter-type/pdf")
    public byte[] addNewLetterType(HttpServletRequest servletRequest,
                                   @RequestBody NewLetterTypeRequest newLetterTypeRequest) {
        byte[] pdfBytes = administrationService.addNewLetterType(newLetterTypeRequest);

        try (OutputStream outputStream = new FileOutputStream("letter_type.pdf")) {
            outputStream.write(pdfBytes);
        } catch (Exception e) {
            // Handle exception
        }
        return pdfBytes;
    }

    @PostMapping("/letter-type/html")
    public String addNewLetterType1(HttpServletRequest servletRequest,
                                   @RequestBody NewLetterTypeRequest newLetterTypeRequest) {
        return administrationService.addNewLetterType1(newLetterTypeRequest);
    }
}
