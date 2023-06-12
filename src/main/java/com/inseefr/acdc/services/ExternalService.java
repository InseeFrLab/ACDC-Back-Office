package com.inseefr.acdc.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.inseefr.acdc.domain.DataCollection;
import com.inseefr.acdc.model.DataCollectionObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@Service
@Slf4j
public class ExternalService {
    @Value("${fr.insee.pogues.uri}")
    private String poguesUri;

    @Value("${fr.insee.magma.uri}")
    private String magmaUri;

    @Value("${fr.insee.metadonnee.uri}")
    private String metadonneeUri;

    //TEMP
    @Value("${fr.insee.metadonnee.token}")
    private String metadonneeToken;

    @Autowired
    private DDIService ddiService;

    @Autowired
    private DataCollectionService dataCollectionService;

    public String getQuestionnaires(){
        log.info("Get questionnaires from Pogues");
        HttpClient client = HttpClient.newHttpClient();
        String url = poguesUri + "api/persistence/questionnaires";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return response.body();
    }

    public String getSeries(){
        log.info("Get series from Magma");
        HttpClient client = HttpClient.newHttpClient();
        String url = magmaUri + "operations/series?survey=true";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return response.body();
    }

    public String getSeriesOperationById(String id){
        log.info("Get series operation by id from Magma");
        HttpClient client = HttpClient.newHttpClient();
        String url = magmaUri + "operations/serie/" + id+"/operations";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return response.body();
    }

    public String convertAndSendToColectica(String dataCollectionID){

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
            //log.info("DataCollection JSON: " + jsonData);

            JsonNode rootNode = objectMapper.readTree(jsonData);
            log.info("RootNode: " + rootNode.toString());
            JsonNode dataCollectionNode = rootNode.get("json");

            DataCollectionObject dataCollection = new DataCollectionObject();
            try {
                dataCollection = objectMapper.treeToValue(rootNode, DataCollectionObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            log.info("DataCollectionObject: " + dataCollection.toString());
            String ddiContent = ddiService.JsonToDDIConverter(dataCollection); // convert JSON to DDI format using the JsonToDDIConverter function

            UUID uuid = UUID.randomUUID();
            String identifier = rootNode.get("id").asText();
            String versionDate = rootNode.get("versionDate").asText();
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("ItemType", "c5084916-9936-47a9-a523-93be9fd816d8");
            item.put("AgencyId", "fr.insee");
            item.put("Version", 1);
            item.put("Identifier", identifier);
            item.put("Item", ddiContent); // pass the DDI-formatted XML string to the "Item" field
            item.put("VersionDate", versionDate);
            item.put("VersionResponsibility", "AD\\\\ylzbwc"); // pass someUser as a command line argument
            item.put("IsPublished", false);
            item.put("IsDeprecated", false);
            item.put("IsProvisional", false);
            item.put("ItemFormat", "DC337820-AF3A-4C0B-82F9-CF02535CDE83");

            JSONObject json = new JSONObject();
            json.put("Items", new JSONArray().put(item));


            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }}

    public String getQualityReportById(String id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = metadonneeUri + "operations/rapportQualite/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + metadonneeToken)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return response.body();

    }
}
