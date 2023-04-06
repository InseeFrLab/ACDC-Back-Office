package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataCollectionDate implements Serializable {
    private String startDate;
    private String endDate;

    @Override
    public String toString() {
        return "DataCollectionDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
