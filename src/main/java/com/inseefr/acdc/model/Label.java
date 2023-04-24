package com.inseefr.acdc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "r:Label")
@XmlAccessorType(XmlAccessType.FIELD)
public class Label {

    @XmlElement(name = "r:Content")
    private List<Content> contentList;

    public Label() {
    }

    public Label(List<Content> contentList) {
        this.contentList = contentList;
    }

    // Getters and setters
}
