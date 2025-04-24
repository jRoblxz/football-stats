package com.example.football_stats.sevice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballService {

    @Value("${api.football.url}")
    private String apiUrl;

    @Value("${api.football.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public FootballService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getTeamDetails(String teamName) {
        String url = apiUrl + "/teams?name=" + teamName;
        return callApi(url);
    }

    public String getTeamStatistics(String teamId) {
        String url = apiUrl + "/teams/statistics?team=" + teamId;
        return callApi(url);
    }

    private String callApi(String url) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Erro na API: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API: " + e.getMessage(), e);
        }
    }
}
