package com.neosoft.microservices.temperatureconversion;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureExchangeController {

	@Autowired
	TemperatureExchangeProxy proxy;
	
	@GetMapping("/temperature-conversion-feign/{from}/to/{to}/value/{value}")
	public TemperatureConversion calculatetemperatureConversion(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal value
		) {
		
//		return new TemperatureConversion(
//				(long) 101, "C", "F", new BigDecimal(400), new BigDecimal(5.5), new BigDecimal(34), "4000" 
//		);

		TemperatureConversion temperatureConversion = proxy.retrieveExchangeValue(from, to);

		if (from.equals("dg") && to.equals("fh"))
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
					temperatureConversion.getConversionMultiple(),
					value.add(new BigDecimal(32)).multiply(temperatureConversion.getConversionMultiple()),
					temperatureConversion.getEnvironment() + " " + "feign client...");
		
		else if (from.equals("fh") && to.equals("dg"))
			return new TemperatureConversion(temperatureConversion.getId(), from, to, value,
				temperatureConversion.getConversionMultiple(),
				value.subtract(new BigDecimal(32)).multiply(temperatureConversion.getConversionMultiple()),
				temperatureConversion.getEnvironment() + " " + "feign client...");
		
		else
			throw new RuntimeException("Invalid path variables in TemperatureExchangeController");
	}
	
}
