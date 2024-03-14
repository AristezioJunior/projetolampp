package com.aristezio.lampp.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.aristezio.lampp.api.model.EncurtadorUrlModel;
import com.aristezio.lampp.api.model.input.EncurtadorUrlInput;
import com.aristezio.lampp.domain.model.EncurtadorUrl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EncurtadorUrlAssembler {

	 private final ModelMapper modelMapper;

	    public EncurtadorUrl toEntity(EncurtadorUrlInput encurtadorUrlInput) {
	        return modelMapper.map(encurtadorUrlInput, EncurtadorUrl.class);
	    }

	    public EncurtadorUrlModel toModel(EncurtadorUrl encurtadorUrl) {
	        return modelMapper.map(encurtadorUrl, EncurtadorUrlModel.class);
	    }

	    public List<EncurtadorUrlModel> toCollectionModel(List<EncurtadorUrl> encurtadorUrls) {
	        return encurtadorUrls.stream()
	                .map(this::toModel)
	                .toList();
	    }
	
}
