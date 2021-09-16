package com.neosoft.microservices.temperatureexchange;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "temperature_exchange")
public class TemperatureExchange {

	@Id
	private Long id;

	@Column(name = "temperature_from")
	private String from;

	@Column(name = "temperature_to")
	private String to;

	private BigDecimal conversionMultiple;
	private String environment;
	
}
