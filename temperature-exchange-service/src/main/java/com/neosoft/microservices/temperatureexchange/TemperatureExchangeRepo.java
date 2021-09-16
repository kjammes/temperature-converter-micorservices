package com.neosoft.microservices.temperatureexchange;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureExchangeRepo extends JpaRepository<TemperatureExchange, Long> {

	TemperatureExchange findByFromAndTo(String from, String to);
	
}
