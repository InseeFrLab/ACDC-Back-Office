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
    public String JsonToDDIConverter(DataCollectionObject dataCollection) {
        try {

            // Convert the Java object to XML using JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(DataCollectionObject.class, CollectionEventObject.class, UserAttributePair.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // Set JAXB_FRAGMENT property to true

            StringWriter sw = new StringWriter();
            marshaller.marshal(dataCollection, sw);
            String xmlData = "<Fragment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                    + "    xsi:schemaLocation=\"ddi:instance:3_3 https://ddialliance.org/Specification/DDI-Lifecycle/3.3/XMLSchema/instance.xsd\"\n"
                    + "    xmlns=\"ddi:instance:3_3\" xmlns:r=\"ddi:reusable:3_3\" xmlns:d=\"ddi:datacollection:3_3\">\n"
                    + sw.toString()
                    + "\n</Fragment>";

            return xmlData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
