package com.inseefr.acdc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ExternalService {
    @Value("${fr.insee.pogues.uri}")
    private String poguesUri;

    @Value("${fr.insee.magma.uri}")
    private String magmaUri;

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
}
