package com.inseefr.acdc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributePair implements Serializable {
    private String attributeKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String attributeValue;

    @Override
    public String toString() {
        return "UserAttributePair{" +
                "attributeName='" + attributeKey + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
