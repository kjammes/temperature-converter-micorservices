package com.neosoft.microservices.temperatureconversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "temperature-exchange") // for load-balancing
//@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface TemperatureExchangeProxy {
	
	@GetMapping("/temperature-exchange/{from}/to/{to}")
	public TemperatureConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
	
}
