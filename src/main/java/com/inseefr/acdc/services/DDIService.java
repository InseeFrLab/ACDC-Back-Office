package com.inseefr.acdc.services;

import com.inseefr.acdc.model.DataCollectionObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Service
@Slf4j
public class DDIService {
    public static String JsonToDDIConverter(String jsonData) {
        try {
            // Create an instance of ObjectMapper to convert JSON to Java object
            ObjectMapper objectMapper = new ObjectMapper();
            DataCollectionObject DataCollection = objectMapper.readValue(jsonData, DataCollectionObject.class);
            log.info("DataCollectionObject: " + DataCollection.toString());

            // Create an instance of JAXBContext and Marshaller to convert Java object to XML
            JAXBContext jaxbContext = JAXBContext.newInstance(DataCollectionObject.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Convert Java object to XML string
            StringWriter sw = new StringWriter();
            marshaller.marshal(DataCollection, sw);
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
