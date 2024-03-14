package com.aristezio.lampp.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EstatisticaTempo {


	private String tempoDecorrido;

	public String getTempoDecorrido() {
		return tempoDecorrido;
	}

	public void setTempoDecorrido(String tempoDecorrido) {
		this.tempoDecorrido = tempoDecorrido;
	}
	
	
}