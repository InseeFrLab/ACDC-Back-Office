package com.inseefr.acdc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Label", namespace = "urn:oecd:ties:stf:v5")
@XmlAccessorType(XmlAccessType.FIELD)
public class Label {

    @XmlElement(name = "Content", namespace = "urn:oecd:ties:stf:v5")
    private List<Content> contentList;

    public Label() {
    }

    public Label(List<Content> contentList) {
        this.contentList = contentList;
    }

    // Getters and setters
}
