package com.inseefr.acdc.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Slf4j
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TypeOfModeOfCollection {
    @Id
    @GeneratedValue
    @UuidGenerator
    @XmlElement(name = "r:ID")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @XmlElement(name = "r:URN")
    private final String urn = "urn:ddi:fr.insee:"+id+":1";
    @XmlElement(name = "r:Agency")
    private String agency;
    @XmlElement(name = "r:Version")
    private int version;
    //TODO: enum
    @XmlElement(name = "r:TypeOfModeOfCollection")
    private String type;
    @XmlAttribute(name="isUniversallyUnique")
    private final boolean isUniversallyUnique = true;
}
