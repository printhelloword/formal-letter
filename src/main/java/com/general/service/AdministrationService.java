package com.general.service;

import com.general.dto.administration.NewLetterTypeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;


@Slf4j
@RequiredArgsConstructor
@Service
public class AdministrationService {

    private final TemplateEngine templateEngine;

    public byte[]  addNewLetterType(NewLetterTypeRequest createNewLetterTypeRequest) {
        String htmlContent = generateHtml(createNewLetterTypeRequest);
        generateHtml(createNewLetterTypeRequest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        try {
            renderer.createPDF(outputStream);
        }catch (Exception e){
            log.info("Error create PDF : {}", e);
        }
        renderer.finishPDF();
        return outputStream.toByteArray();
    }

    public String addNewLetterType1(NewLetterTypeRequest createNewLetterTypeRequest) {
        String htmlContent = generateHtml(createNewLetterTypeRequest);
        generateHtml(createNewLetterTypeRequest);
        return htmlContent;
    }

    private String generateHtml(NewLetterTypeRequest data) {
        Context context = new Context();
        context.setVariable("letter", data);
        return templateEngine.process("letter-template", context);
    }

}
