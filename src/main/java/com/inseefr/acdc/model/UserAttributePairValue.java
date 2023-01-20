package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributePairValue implements Serializable {
    private List<Map<String,String>> label;
    private List<Map<String,String>> collectionEventReference;

    @Override
    public String toString() {
        return "UserAttributePairValue{" +
                "label=" + label +
                ", collectionEventReference=" + collectionEventReference.toString() +
                '}';
    }
}
