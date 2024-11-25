package com.example.strava_project_uni.config;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ServiceGateway implements IServiceGateway {
    private final String API_URL = "https://api.freecurrencyapi.com/v1/latest";
    private final String API_KEY = "fca_live_DgSapgilaF9fv0FzhAj6VwxXETqUbsIFAKMrGj2s";

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public void CurrencyServiceGateway() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @SuppressWarnings("unchecked")    
    @Override
	public Optional<Float> getExchangeRate(String baseCurrency, String targetCurrency) {
        // Build the URL
        String url = API_URL + "?apikey=" + API_KEY +  "&base_currency=" + baseCurrency + "&currencies=" + targetCurrency;

        try {
            // Create the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Send the request and obtain the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        	
        	// If response is OK, parse the response body
        	if (response.statusCode() == 200) {
				Map<String, Object> exchangeRates = objectMapper.readValue(response.body(), Map.class);
				exchangeRates = (Map<String, Object>) exchangeRates.get("data");			
				return Optional.of(Float.parseFloat(exchangeRates.get(targetCurrency).toString()));			
			} else {
				return Optional.empty();
			}
        } catch (Exception ex) {
        	return Optional.empty();
        }
    }
}
