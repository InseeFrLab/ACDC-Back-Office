package com.inseefr.acdc.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collection_event")
public class CollectionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    private String agency;
    private int version;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<IntlText> label;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private DataCollectionDate dataCollectionDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ModeOfCollection> modeOfCollection;
    @OneToMany(cascade = CascadeType.ALL)
    private List<InstrumentReference> instrumentReference;


}

