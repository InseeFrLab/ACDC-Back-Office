package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributePair implements Serializable {
    private String attributeKey;
    private List<String> attributeValue;

    @Override
    public String toString() {
        return "UserAttributePair{" +
                "attributeName='" + attributeKey + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
