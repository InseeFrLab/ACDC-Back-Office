package com.inseefr.acdc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inseefr.acdc.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public
class DataCollectionObjectDeserializer extends JsonDeserializer<DataCollectionObject> {

    @Override
    public DataCollectionObject deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode node = parser.getCodec().readTree(parser);
        String id = node.get("id").asText();
        String urn = "urn:ddi:fr.insee:39e00da9-c1bc-4019-9672-0dbfa0d0c73e:1";
        Map<String,String> labelMap = mapper.convertValue(node.get("label"), new TypeReference<Map<String, String>>() {});

        List<Content> labelContentList = labelMap.entrySet().stream()
                .map(entry -> new Content(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        Label label = new Label(labelContentList);
        String agency = node.get("agency").asText();
        int version = node.get("version").asInt();
        Map<String,String> descriptionMap = mapper.convertValue(node.get("description"), new TypeReference<Map<String, String>>() {});

        List<Content> descriptionContentList = descriptionMap.entrySet().stream()
                .map(entry -> new Content(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        Description description = new Description(descriptionContentList);
        String versionDate = node.get("versionDate").asText();
        List<CollectionEventObject> collectionEvents = new ArrayList<>();
        JsonNode collectionEventsNode = node.get("collectionEvents");
        if (collectionEventsNode.isArray()) {
            for (JsonNode collectionEventNode : collectionEventsNode) {
                UUID eventId = UUID.fromString(collectionEventNode.get("id").asText());
                Map<String, String> collectionEventNameMap = mapper.convertValue(collectionEventNode.get("collectionEventName"), new TypeReference<Map<String, String>>() {
                });
                List<Content> collectionEventNameContentList = collectionEventNameMap.entrySet().stream()
                        .map(entry -> new Content(entry.getValue(), entry.getKey()))
                        .collect(Collectors.toList());
                CollectionEventName collectionEventName = new CollectionEventName(collectionEventNameContentList);

                Map<String, String> eventLabelMap = mapper.convertValue(collectionEventNode.get("label"), new TypeReference<Map<String, String>>() {
                });
                List<Content> eventLabelContentList = eventLabelMap.entrySet().stream()
                        .map(entry -> new Content(entry.getValue(), entry.getKey()))
                        .collect(Collectors.toList());
                Label eventLabel = new Label(eventLabelContentList);
                String eventAgency = collectionEventNode.get("agency").asText();
                int eventVersion = collectionEventNode.get("version").asInt();
                Map<String, String> eventDescriptionMap = mapper.convertValue(collectionEventNode.get("description"), new TypeReference<Map<String, String>>() {
                });
                List<Content> eventDescriptionContentList = eventDescriptionMap.entrySet().stream()
                        .map(entry -> new Content(entry.getValue(), entry.getKey()))
                        .collect(Collectors.toList());

                Description eventDescription = new Description(eventDescriptionContentList);
                DataCollectionDate dataCollectionDate = mapper.convertValue(collectionEventNode.get("dataCollectionDate"), DataCollectionDate.class);
                List<TypeOfModeOfCollection> typeOfModeOfCollection = mapper.convertValue(collectionEventNode.get("typeOfModeOfCollection"), new TypeReference<List<TypeOfModeOfCollection>>() {
                });
                InstrumentReference instrumentReference = mapper.convertValue(collectionEventNode.get("instrumentReference"), InstrumentReference.class);
                List<UserAttributePair> userAttributePairCollection = mapper.convertValue(collectionEventNode.get("userAttributePair"), new TypeReference<List<UserAttributePair>>() {
                });

                CollectionEventObject collectionEvent = new CollectionEventObject(eventId, urn, eventAgency, eventVersion, collectionEventName, eventLabel, eventDescription, dataCollectionDate, typeOfModeOfCollection, instrumentReference, userAttributePairCollection);
                collectionEvents.add(collectionEvent);
            }
        }
        JsonNode userAttributePairsNode = node.get("userAttributePair");
        List<UserAttributePair> userAttributePair = mapper.convertValue(userAttributePairsNode, new TypeReference<List<UserAttributePair>>() {
        });
        DataCollectionObject response = new DataCollectionObject(id, label, agency, version, description, versionDate, collectionEvents, userAttributePair, urn);
        return response;
    }
}
