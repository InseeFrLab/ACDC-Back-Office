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
@XmlRootElement
public class DataCollectionObject {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @XmlElement
    private String agency;

    @XmlElement
    private int version;

    @XmlElement
    private String versionDate;

    @XmlElement
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String,String> label;

    @XmlElement
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String,String> description;

    @XmlElementWrapper
    @XmlElement(name = "collectionEvent")
    @OneToMany
    private ArrayList<CollectionEventObject> collectionEvents;

    @XmlElementWrapper
    @XmlElement(name = "userAttributePair")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<UserAttributePair> userAttributePair;

    public DataCollectionObject(String agency, int version, Map<String,String> label, Map<String,String> description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }

    public DataCollectionObject(String agency, int version, Map<String,String> label, Map<String,String>description, ArrayList<CollectionEventObject> collectionEvents, ArrayList<UserAttributePair> userAttributePair) {
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

    public DataCollectionObject(String id, Map<String,String> label, String agency, int version, Map<String,String> description, String versionDate, List<CollectionEventObject> collectionEvents, List<UserAttributePair> userAttributePair) {
        this.id = UUID.fromString(id);
        this.agency = agency;
        this.version = version;
        this.versionDate = versionDate;
        this.label = label;
        this.description = description;
        this.collectionEvents = new ArrayList<>(collectionEvents);
        this.userAttributePair = new ArrayList<>(userAttributePair);
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


