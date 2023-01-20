package com.inseefr.acdc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_collection")
public class DataCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
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
    private List<CollectionEvent> collectionEvents;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<UserAttributePair> userAttributePair;

    public DataCollection(String agency, int version, List<Map<String,String>>  label, List<Map<String,String>> description) {
        this.agency = agency;
        this.version = version;
        this.label = label;
        this.description = description;
    }

}
