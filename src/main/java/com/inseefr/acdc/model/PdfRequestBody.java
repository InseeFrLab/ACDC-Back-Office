package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PdfRequestBody {
    private String xmlContent;
    private MailVariable mailVariable;

    @Override
    public String toString() {
        return "PdfRequestBody{" +
                "xmlContent='" + xmlContent + '\'' +
                ", mailVariable=" + mailVariable.toString() +
                '}';
    }
}
