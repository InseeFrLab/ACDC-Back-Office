package com.inseefr.acdc.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class Content {

    @XmlValue
    private String value;

    @XmlAttribute(name = "lang")
    private String lang;

    public Content() {
    }

    public Content(String value, String lang) {
        this.value = value;
        this.lang = lang;
    }

    // Getters and setters
}

