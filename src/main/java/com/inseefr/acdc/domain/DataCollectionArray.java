package com.inseefr.acdc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCollectionArray {
    private List<Map<String, Object>> dataCollections;

    public List<Map<String, Object>> getDataCollections() {
        return dataCollections;
    }

    public void setDataCollections(List<Map<String, Object>> dataCollections) {
        this.dataCollections = dataCollections;
    }

    @Override
    public String toString() {
        return "DataCollectionArray{" +
                "dataCollections=" + dataCollections +
                '}';
    }
}
