package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionEvent {
    private String id;
    private String agency;
    private int version;
    private Map<String, String> label;
    private DataCollectionDate dataCollectionDate;
    private List<ModeOfCollection> modeOfCollection;
    private List<InstrumentReference> instrumentReference;


}

