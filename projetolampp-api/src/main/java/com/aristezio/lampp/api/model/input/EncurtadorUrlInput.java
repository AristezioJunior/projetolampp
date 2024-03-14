package com.aristezio.lampp.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncurtadorUrlInput {

	@NotBlank
	@Size(min = 6, max = 254)
	private String url;

	@Size(min = 4, max = 30)
	private String codigo;
	
}
