package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @OneToMany
    private List<IntlText>  label;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToMany
    private List<ModeOfCollection> modeOfCollection;
    @OneToMany
    private List<InstrumentReference> instrumentReference;


}

