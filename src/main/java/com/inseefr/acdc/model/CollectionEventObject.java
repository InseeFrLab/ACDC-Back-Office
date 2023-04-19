package com.inseefr.acdc.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    @XmlElement(name="r:ID")
    private UUID id;

    @XmlElement(name="r:URN")
    private String urn;
    @XmlElement(name="r:Agency")
    private String agency;
    @XmlElement(name="r:Version")
    private int version;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:CollectionEventName")
    private CollectionEventName collectionEventName;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:Label")
    private Label label;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:Description")
    private Description description;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:DataCollectionDate")
    private DataCollectionDate dataCollectionDate;
    @OneToMany(cascade = CascadeType.ALL)
    @XmlElement(name="d:ModeOfCollection")
    private List<TypeOfModeOfCollection> typeOfModeOfCollection;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:InstrumentReference")
    private InstrumentReference instrumentReference;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    @XmlElement(name="d:UserAttributePair")
    private List<UserAttributePair> userAttributePair;

    public CollectionEventObject(String id, String agency, int version, Label label, DataCollectionDate dataCollectionDate, List<TypeOfModeOfCollection> typeOfModeOfCollection, InstrumentReference instrumentReference, List<UserAttributePair> userAttributePair, String urn){
        this.id = UUID.fromString(id);
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.dataCollectionDate = dataCollectionDate;
        this.typeOfModeOfCollection = typeOfModeOfCollection;
        this.instrumentReference = instrumentReference;
        this.userAttributePair = userAttributePair;
        this.urn = urn;
    }

    public CollectionEventObject(String id, String agency, int version) {
        this.id = UUID.fromString(id);
        this.agency = agency;
        this.version = version;
    }
}

