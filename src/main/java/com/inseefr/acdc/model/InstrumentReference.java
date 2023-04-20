package com.inseefr.acdc.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Slf4j
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InstrumentReference {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @XmlElement(name= "r:ID")
    private String id;
    @XmlElement(name= "r:Agency")
    private String agency;
    @XmlElement(name= "r:Version")
    private int version;
    @XmlElement(name= "r:TypeOfObject")
    private String typeOfObject;
    @XmlElement(name= "r:Label")
    private String label;
}
