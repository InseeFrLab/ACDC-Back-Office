package com.inseefr.acdc.model;

import jakarta.persistence.*;
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
    private List<UserAttributePairValue> attributeValue;

    @Override
    public String toString() {
        return "UserAttributePair{" +
                "attributeKey='" + attributeKey + '\'' +
                ", attributeValue=" + attributeValue.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAttributePair that = (UserAttributePair) o;
        return Objects.equals(attributeKey, that.attributeKey) && Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeKey, attributeValue);
    }
}
