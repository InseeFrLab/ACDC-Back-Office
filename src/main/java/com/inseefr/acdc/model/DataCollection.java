package com.inseefr.acdc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
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
@Entity
@Table(name = "data_collection")
public class DataCollection {
    // TODO : Demander le mode de génération des ID
    @Id
    @Column(name = "id")
    private String id;
    private String agency;
    private int version;
    // TODO : Date Fixé à la création ? Ou à la modification ?
    private LocalDateTime versionDate;
    @OneToMany
    List<IntlText> label;
    @OneToMany
    private List<IntlText> description;
    @OneToMany
    private List<CollectionEvent> collectionEvents;

    public DataCollection(String agency, int version, List<IntlText>  label, List<IntlText> description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }

}
