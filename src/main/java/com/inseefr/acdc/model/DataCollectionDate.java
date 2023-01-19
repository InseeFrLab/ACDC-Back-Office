package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataCollectionDate {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
