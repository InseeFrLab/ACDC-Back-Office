package com.inseefr.acdc.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Data Collection (Object model) -> Will probably be useless, but I feel bad deleting it
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_collection_object")
public class DataCollectionObject {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    private String agency;
    private int version;
    private LocalDateTime versionDate;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String,String>> label;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String,String>> description;
    @OneToMany
    private List<CollectionEventObject> collectionEventObjects;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<UserAttributePair> userAttributePair;

    /**
     * Instantiates a new Data collection.
     *
     * @param agency      the agency
     * @param version     the version
     * @param label       the label
     * @param description the description
     */
    public DataCollectionObject(String agency, int version, List<Map<String,String>>  label, List<Map<String,String>> description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }

    /**
     * Instantiates a new Data collection.
     *
     * @param agency            the agency
     * @param version           the version
     * @param label             the label
     * @param description       the description
     * @param collectionEventObjects  the collection events
     * @param userAttributePair the user attribute pair
     */
    public DataCollectionObject(String agency, int version, List<Map<String, String>> label, List<Map<String, String>> description, List<CollectionEventObject> collectionEventObjects, List<UserAttributePair> userAttributePair) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
        this.collectionEventObjects = collectionEventObjects;
        this.userAttributePair = userAttributePair;
    }




}
