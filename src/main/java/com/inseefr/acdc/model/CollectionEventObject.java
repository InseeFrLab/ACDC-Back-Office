package com.inseefr.acdc.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collection_event")
public class CollectionEventObject {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    private String agency;
    private int version;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String,String>> label;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private DataCollectionDate dataCollectionDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ModeOfCollection> modeOfCollection;
    @OneToMany(cascade = CascadeType.ALL)
    private List<InstrumentReference> instrumentReference;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<CollectionUserAttributePair> userAttributePair;

    public CollectionEventObject(String agency, int version, List<Map<String, String>> label, DataCollectionDate dataCollectionDate, List<ModeOfCollection> modeOfCollection, List<InstrumentReference> instrumentReference) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.dataCollectionDate = dataCollectionDate;
        this.modeOfCollection = modeOfCollection;
        this.instrumentReference = instrumentReference;
    }

    public CollectionEventObject(String agency, int version) {

        this.agency = agency;
        this.version = version;
    }
}

