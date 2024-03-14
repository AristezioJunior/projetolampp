package com.aristezio.lampp.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aristezio.lampp.api.model.EncurtadorUrlAcessosModel;
import com.aristezio.lampp.api.model.EncurtadorUrlModel;
import com.aristezio.lampp.domain.model.EncurtadorUrl;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(EncurtadorUrl.class, EncurtadorUrlModel.class)
                .addMappings(mapper -> mapper.map(EncurtadorUrl::getCodigo, EncurtadorUrlModel::setAlias));
        
        modelMapper.createTypeMap(EncurtadorUrl.class, EncurtadorUrlAcessosModel.class)
        .addMappings(mapper -> mapper.map(EncurtadorUrl::getCodigo, EncurtadorUrlAcessosModel::setAlias));

        return modelMapper;
    }

}
