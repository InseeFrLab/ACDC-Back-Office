package com.inseefr.acdc.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inseefr.acdc.utils.DataCollectionObjectDeserializer;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Data Collection (Object model) -> Will probably be useless, but I feel bad deleting it
 */


@Setter
@AllArgsConstructor
@Entity
@Table(name = "data_collection_object")
@JsonDeserialize(using = DataCollectionObjectDeserializer.class)
@XmlRootElement(name = "d:DataCollection")
public class DataCollectionObject {
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

    @XmlAttribute(name="versionDate")
    private String versionDate;

    @XmlElement(name="r:Label")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Label label;

    @XmlElement(name="r:Description")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Description description;

    @XmlElement(name = "d:CollectionEvent")
    @OneToMany
    private ArrayList<CollectionEventObject> collectionEvents;


    @XmlElement(name = "r:UserAttributePair")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<UserAttributePair> userAttributePair;

    public DataCollectionObject(String agency, int version, Label label, Description description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }

    public DataCollectionObject(String agency, int version, Label label, Description description, ArrayList<CollectionEventObject> collectionEvents, ArrayList<UserAttributePair> userAttributePair) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
        this.collectionEvents = collectionEvents;
        this.userAttributePair = userAttributePair;
    }

    public DataCollectionObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readerForUpdating(this).readValue(json);
    }

    public DataCollectionObject(String id, Label label, String agency, int version, Description description, String versionDate, List<CollectionEventObject> collectionEvents, List<UserAttributePair> userAttributePair, String urn) {
        this.id = UUID.fromString(id);
        this.agency = agency;
        this.version = version;
        this.versionDate = versionDate;
        this.label = label;
        this.description = description;
        this.collectionEvents = new ArrayList<>(collectionEvents);
        this.userAttributePair = new ArrayList<>(userAttributePair);
        this.urn = urn;
    }

    public DataCollectionObject() {

    }

    @Override
    public String toString() {
        return "DataCollectionObject{" +
                "id=" + id +
                ", agency='" + agency + '\'' +
                ", version=" + version +
                ", versionDate='" + versionDate + '\'' +
                ", label=" + label +
                ", description=" + description +
                ", collectionEvents=" + collectionEvents +
                ", userAttributePair=" + userAttributePair +
                '}';
    }
}


