package com.neosoft.microservices.temperatureconversion;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureConversion {

	private Long id;
	private String from;
	private String to;
	private BigDecimal value;
	private BigDecimal conversionMultiple;
	private BigDecimal convertedTemp;
	private String environment;
	
}
