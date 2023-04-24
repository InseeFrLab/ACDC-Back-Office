package com.inseefr.acdc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributePair implements Serializable {
    @XmlElement(name = "r:AttributeKey")
    private String attributeKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @XmlElement(name = "r:AttributeValue")
    private String attributeValue;

    @Override
    public String toString() {
        return "UserAttributePair{" +
                "attributeName='" + attributeKey + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
