package com.aristezio.lampp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String errCode;
    private String description;
	
}
