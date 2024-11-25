package com.example.strava_project_uni.config;

import java.util.Optional;

public interface IServiceGateway {
	public Optional<Float> getExchangeRate(String baseCurrency, String targetCurrency);
}
