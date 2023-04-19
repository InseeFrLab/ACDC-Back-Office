package com.inseefr.acdc.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataCollectionDate implements Serializable {
    @XmlElement(name = "r:StartDate")
    private String startDate;
    @XmlElement(name = "r:EndDate")
    private String endDate;

    @Override
    public String toString() {
        return "DataCollectionDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
