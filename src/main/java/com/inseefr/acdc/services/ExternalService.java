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
}
