package com.kitrum.task.manager.user.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThirdPartyService {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String url;

    public ThirdPartyService(CloseableHttpClient httpClient,
                             ObjectMapper objectMapper,
                             @Value(value = "third-party.url") String url) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.url = url;
    }

    public Optional<Integer> getUserRating(String userId) {
        HttpGet request = new HttpGet(String.format("%s/%s", this.url, userId));
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            UserRating userRating = objectMapper.readValue(response.getEntity().getContent(), UserRating.class);

            return Optional.of(userRating.getValue());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Data
    public static class UserRating {

        @JsonProperty("value")
        private Integer value;
    }
}
