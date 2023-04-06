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
    private Map<String,String> collectionEventName;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String,String> label;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String,String> description;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private DataCollectionDate dataCollectionDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TypeOfModeOfCollection> typeOfModeOfCollection;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private InstrumentReference instrumentReference;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<UserAttributePair> userAttributePair;

    public CollectionEventObject(String agency, int version, Map<String,String> label, DataCollectionDate dataCollectionDate, List<TypeOfModeOfCollection> typeOfModeOfCollection, InstrumentReference instrumentReference, List<UserAttributePair> userAttributePair){
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.dataCollectionDate = dataCollectionDate;
        this.typeOfModeOfCollection = typeOfModeOfCollection;
        this.instrumentReference = instrumentReference;
        this.userAttributePair = userAttributePair;
    }

    public CollectionEventObject(String agency, int version) {

        this.agency = agency;
        this.version = version;
    }
}

