package com.inseefr.acdc.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.inseefr.acdc.model.CollectionEventObject;
import com.inseefr.acdc.model.DataCollectionObject;
import com.inseefr.acdc.model.UserAttributePair;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Service
@Slf4j
public class DDIService {
    @Autowired
    private DataCollectionService dataCollectionService;
    public String JsonToDDIConverter(String dataCollectionID) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                    .appendOffset("+HH:MM", "+00:00")
                    .toFormatter();

            ObjectMapper objectMapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .registerModule(new SimpleModule()
                            .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE));

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
            objectMapper.registerModule(javaTimeModule);


            String jsonData = objectMapper.writeValueAsString(dataCollectionService.getDataCollectionById(dataCollectionID).getJson());
            log.info("DataCollection JSON: " + jsonData);

            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode dataCollectionNode = rootNode.get("json");
            if (dataCollectionNode == null) {
                log.error("DataCollection JSON does not contain a 'dataCollection' field");
                return "";
            }

            DataCollectionObject dataCollection = objectMapper.treeToValue(dataCollectionNode, DataCollectionObject.class);

            log.info("DataCollectionObject: " + dataCollection.toString());


            // Convert the Java object to XML using JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(DataCollectionObject.class, CollectionEventObject.class, UserAttributePair.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(dataCollection, sw);
            String xmlData = sw.toString();

            // Do something with the DDI-formatted XML string
            log.info("DDI-formatted XML string: " + xmlData);
            return xmlData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
