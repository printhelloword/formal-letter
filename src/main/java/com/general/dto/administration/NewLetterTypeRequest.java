package com.general.dto.administration;

import lombok.Data;

import java.util.List;

@Data
public class NewLetterTypeRequest {
    private String title;
    private String introduction;
    private String closing;
    private String city;
    private List<String> header;
    private List<String> form;
    private List<AdministrationLetterSignature> signature;
}
