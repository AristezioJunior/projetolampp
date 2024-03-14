package com.aristezio.lampp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aristezio.lampp.api.assembler.EncurtadorUrlAssembler;
import com.aristezio.lampp.api.controller.EncurtadorUrlController;
import com.aristezio.lampp.api.model.EncurtadorUrlModel;
import com.aristezio.lampp.api.model.input.EncurtadorUrlInput;
import com.aristezio.lampp.domain.model.EncurtadorUrl;
import com.aristezio.lampp.domain.repository.EncurtadorUrlRepository;
import com.aristezio.lampp.domain.service.EncurtadorUrlService;

@ExtendWith(MockitoExtension.class)
public class EncurtadorUrlTest {

    @Mock
    private EncurtadorUrlRepository repository;

    @Mock
    private EncurtadorUrlService service;

    @Mock
    private EncurtadorUrlAssembler assembler;

    @InjectMocks
    private EncurtadorUrlController controller;

    @Test
    void testBuscar() {
        // ID de exemplo
        Long id = 1L;
        
        // Mock do retorno do repository
        EncurtadorUrl encurtadorUrl = new EncurtadorUrl();
        encurtadorUrl.setCodigo("alias");
        encurtadorUrl.setUrl("http://example.com");
        Optional<EncurtadorUrl> optional = Optional.of(encurtadorUrl);
        when(repository.findById(id)).thenReturn(optional);
        
        // Mock do retorno do assembler
        EncurtadorUrlModel model = new EncurtadorUrlModel();
        model.setAlias("alias");
        model.setUrl("http://example.com");
        when(assembler.toModel(encurtadorUrl)).thenReturn(model);
        
        // Teste do método buscar
        ResponseEntity<EncurtadorUrlModel> result = controller.buscar(id);
        
        // Verifica se o status da resposta é OK
        assertEquals(HttpStatus.OK, result.getStatusCode());
        
        // Verifica se o corpo da resposta é igual ao modelo esperado
        assertEquals(model, result.getBody());
        
        // Verifica se o método findById do repository foi chamado
        verify(repository).findById(id);
        
        // Verifica se o método toModel do assembler foi chamado
        verify(assembler).toModel(encurtadorUrl);
    }
    
    @Test
    void testCadastrar() {
        // Mock dos dados de entrada
        EncurtadorUrlInput input = new EncurtadorUrlInput();
        input.setUrl("http://example.com");
        
        // Mock do retorno do serviço
        EncurtadorUrl url = new EncurtadorUrl();
        url.setCodigo("alias");
        url.setUrl("http://example.com");
        when(service.salvar(any())).thenReturn(url);
        
        // Mock do retorno do assembler
        EncurtadorUrlModel model = new EncurtadorUrlModel();
        model.setAlias("alias");
        model.setUrl("http://example.com");
        when(assembler.toModel(url)).thenReturn(model);
        
        // Teste do método cadastrar
        EncurtadorUrlModel result = controller.cadastrar(input);
        
        // Verifica se o modelo retornado é igual ao esperado
        assertEquals(model, result);
        
        // Verifica se o método salvar do serviço foi chamado
        verify(service).salvar(any());
        
        // Verifica se o método toModel do assembler foi chamado
        verify(assembler).toModel(url);
    }
    
	
}
