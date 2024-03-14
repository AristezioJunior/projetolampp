package com.aristezio.lampp.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncurtadorUrlModel {

	private String alias;
	private String url;
	private EstatisticaTempoModel  estatisticaTempo;
	private String urlOriginal;
	
}
