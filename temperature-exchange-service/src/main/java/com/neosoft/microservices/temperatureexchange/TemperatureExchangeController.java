package com.neosoft.microservices.temperatureexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	TemperatureExchangeRepo repo;
	
	@GetMapping("/temperature-exchange/{from}/to/{to}")
	public TemperatureExchange retrieveExchangeValueCToF(
			@PathVariable String from,
			@PathVariable String to
	) {
		TemperatureExchange tE;
		
		if (from.equals("dg") && to.equals("fh"))
			tE = repo.findByFromAndTo("C", "F");
		else if (from.equals("fh") && to.equals("dg"))
			tE = repo.findByFromAndTo("F", "C");
		else
			throw new RuntimeException("Invalid path variables");
		
		if (tE == null)
			throw new RuntimeException("Unable to find data from C to F");
		
		String port = environment.getProperty("local.server.port");
		tE.setEnvironment(port);
		
		return tE;
	}
	
}
