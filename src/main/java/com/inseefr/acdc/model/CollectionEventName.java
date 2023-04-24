package com.inseefr.acdc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "d:CollectionEventName")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionEventName {

    @XmlElement(name = "r:Content")
    private List<Content> contentList;

    public CollectionEventName() {
    }

    public CollectionEventName(List<Content> contentList) {
        this.contentList = contentList;
    }
}
