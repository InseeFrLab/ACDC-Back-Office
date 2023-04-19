package com.inseefr.acdc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Label")
@XmlAccessorType(XmlAccessType.FIELD)
public class Description {

    @XmlElement(name = "Content")
    private List<Content> contentList;

    public Description() {
    }

    public Description(List<Content> contentList) {
        this.contentList = contentList;
    }

    // Getters and setters
}
