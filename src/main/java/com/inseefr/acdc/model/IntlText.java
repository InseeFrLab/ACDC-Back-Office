package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntlText implements Serializable {

    private String lang;
    private String text;

    @Override
    public String toString() {
        return "IntlText{" +
                "lang='" + lang + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntlText intlText = (IntlText) o;
        return Objects.equals(lang, intlText.lang) && Objects.equals(text, intlText.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, text);
    }
}
