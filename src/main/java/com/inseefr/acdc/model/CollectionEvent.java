package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collection_event")
public class CollectionEvent {
    @Id
    @Column(name = "id")
    private String id;
    private String agency;
    private int version;
    @OneToOne
    private IntlText label;
    @OneToOne
    private DataCollectionDate dataCollectionDate;
    @OneToMany
    private List<ModeOfCollection> modeOfCollection;
    @OneToMany
    private List<InstrumentReference> instrumentReference;


}

