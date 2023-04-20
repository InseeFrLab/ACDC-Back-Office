package com.inseefr.acdc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
@Slf4j
public class ExternalService {
    @Value("${fr.insee.pogues.uri}")
    private String poguesUri;

    @Value("${fr.insee.magma.uri}")
    private String magmaUri;

    @Autowired
    private DDIService ddiService;

    public String getQuestionnaires(){

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
        log.info("Response from Pogues: " + response.body());
        return response.body();
    }

    public String getSeries(){
        HttpClient client = HttpClient.newHttpClient();
        String url = magmaUri + "operations/series";
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
        log.info("Response from Magma: " + response.body());
        return response.body();
    }

    public String getSeriesOperationById(String id){
        HttpClient client = HttpClient.newHttpClient();
        String url = magmaUri + "operations/series/" + id+"/operations";
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
        log.info("Response from Magma: " + response.body());
        return response.body();
    }

    public String convertAndSendToColectica(String dataCollectionID){

        try {

            String ddiContent = ddiService.JsonToDDIConverter(dataCollectionID); // convert JSON to DDI format using the JsonToDDIConverter function

            UUID uuid = UUID.randomUUID();
            String identifier = uuid.toString();
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("ItemType", "c5084916-9936-47a9-a523-93be9fd816d8");
            item.put("AgencyId", "fr.insee");
            item.put("Version", 1);
            item.put("Identifier", identifier);
            item.put("Item", ddiContent); // pass the DDI-formatted XML string to the "Item" field
            item.put("VersionDate", "2023-01-23T11:53:37.1700000Z");
            item.put("VersionResponsibility", "AD\\\\idep"); // pass someUser as a command line argument
            item.put("IsPublished", false);
            item.put("IsDeprecated", false);
            item.put("IsProvisional", false);
            item.put("ItemFormat", "DC337820-AF3A-4C0B-82F9-CF02535CDE83");

            List<Map<String, Object>> items = new ArrayList<>();
            items.add(item);

            Map<String, Object> json = new LinkedHashMap<>();
            json.put("Items", items);

            log.info("JSON: " + json.toString());

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }}
}
