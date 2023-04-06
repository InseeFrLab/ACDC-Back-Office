package com.inseefr.acdc.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.inseefr.acdc.domain.DataCollection;
import com.inseefr.acdc.domain.DataCollectionArray;
import com.inseefr.acdc.model.DataCollectionObject;
import com.inseefr.acdc.utils.AttributeValueDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
                            .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE)
                            .addDeserializer(String.class, new AttributeValueDeserializer()));

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
            objectMapper.registerModule(javaTimeModule);


            String jsonData = objectMapper.writeValueAsString(dataCollectionService.getDataCollectionById(dataCollectionID).getJson());
            log.info("DataCollection JSON: " + jsonData);

            DataCollectionObject dataCollection = objectMapper.readValue(jsonData, DataCollectionObject.class);
            log.info("DataCollectionObject: " + dataCollection.toString());

            // Convert the Java object to XML using JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(DataCollectionObject.class);
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
