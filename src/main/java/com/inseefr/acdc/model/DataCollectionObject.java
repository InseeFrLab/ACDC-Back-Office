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

@Getter
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

    public DataCollectionObject(String id, String label, String agency, int version, String description, String versionDate, List<CollectionEventObject> collectionEvents) {
    }

    public DataCollectionObject() {

    }
}

class DataCollectionObjectDeserializer extends JsonDeserializer<DataCollectionObject> {

    @Override
    public DataCollectionObject deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode node = parser.getCodec().readTree(parser);
        String id = node.get("id").asText();
        String label = node.get("label").get("fr-FR").asText();
        String agency = node.get("agency").asText();
        int version = node.get("version").asInt();
        String description = node.get("description").get("fr-FR").asText();
        String versionDate = node.get("versionDate").asText();
        List<CollectionEventObject> collectionEvents = new ArrayList<>();
        JsonNode collectionEventsNode = node.get("collectionEvents");
        if (collectionEventsNode.isArray()) {
            for (JsonNode collectionEventNode : collectionEventsNode) {
                UUID eventId = UUID.fromString(collectionEventNode.get("id").asText());
                Map<String, String> collectionEventName = mapper.convertValue(collectionEventNode.get("collectionEventName"), new TypeReference<Map<String, String>>() {
                });
                Map<String, String> eventLabel = mapper.convertValue(collectionEventNode.get("label"), new TypeReference<Map<String, String>>() {
                });
                String eventAgency = collectionEventNode.get("agency").asText();
                int eventVersion = collectionEventNode.get("version").asInt();
                Map<String, String> eventDescription = mapper.convertValue(collectionEventNode.get("description"), new TypeReference<Map<String, String>>() {
                });
                DataCollectionDate dataCollectionDate = mapper.convertValue(collectionEventNode.get("dataCollectionDate"), DataCollectionDate.class);
                List<TypeOfModeOfCollection> typeOfModeOfCollection = mapper.convertValue(collectionEventNode.get("typeOfModeOfCollection"), new TypeReference<List<TypeOfModeOfCollection>>() {
                });
                InstrumentReference instrumentReference = mapper.convertValue(collectionEventNode.get("instrumentReference"), InstrumentReference.class);
                List<UserAttributePair> userAttributePair = mapper.convertValue(collectionEventNode.get("userAttributePair"), new TypeReference<List<UserAttributePair>>() {
                });

                CollectionEventObject collectionEvent = new CollectionEventObject(eventId, eventAgency, eventVersion, collectionEventName, eventLabel, eventDescription, dataCollectionDate, typeOfModeOfCollection, instrumentReference, userAttributePair);
                collectionEvents.add(collectionEvent);
            }
        }

        return new DataCollectionObject(id, label, agency, version, description, versionDate, collectionEvents);
    }
}
