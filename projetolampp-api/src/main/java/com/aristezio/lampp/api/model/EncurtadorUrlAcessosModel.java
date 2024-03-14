package com.aristezio.lampp.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncurtadorUrlAcessosModel {

	private String alias;
	private String url;
	private String urlOriginal;
    private int acessos;
	
}
