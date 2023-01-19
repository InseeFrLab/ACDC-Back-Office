package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataCollection {
    // TODO : Demander le mode de génération des ID
    private String id;
    private String agency;
    private int version;
    // TODO : Date Fixé à la création ? Ou à la modification ?
    private LocalDateTime versionDate;
    private Map<String, String> label;
    private Map<String, String> description;
    private List<CollectionEvent> collectionEvents;

    public DataCollection(String agency, int version, Map<String, String> label, Map<String, String> description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }
}
