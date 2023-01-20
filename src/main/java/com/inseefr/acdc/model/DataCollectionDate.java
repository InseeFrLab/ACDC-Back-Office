package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataCollectionDate {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "UUID", sequenceName = "UUID", allocationSize = 1)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
