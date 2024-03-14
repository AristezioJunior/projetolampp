package com.aristezio.lampp.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.aristezio.lampp.api.assembler.EncurtadorUrlAcessosAssembler;
import com.aristezio.lampp.api.model.EncurtadorUrlAcessosModel;
import com.aristezio.lampp.domain.model.EncurtadorUrl;
import com.aristezio.lampp.domain.model.ErrorResponse;
import com.aristezio.lampp.domain.repository.EncurtadorUrlRepository;
import com.aristezio.lampp.domain.service.EncurtadorUrlService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/alias")
public class RecuperarUrlController {

	@Autowired
	EncurtadorUrlRepository repository;
	    
	
	private final EncurtadorUrlAcessosAssembler encurtadorUrlAcessoAssembler;
	
	@GetMapping("/{codigo}")
	ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String codigo) {
        Optional<EncurtadorUrl> encurtadorUrl = repository.findByCodigo(codigo);
        
        if (encurtadorUrl.isPresent()) {
        	EncurtadorUrl url = repository.findByCodigoEquals(codigo);
        	url.incrementarAcessos();
        	repository.save(url);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new RedirectView(url.getUrlOriginal()));
        	
        } else {
            ErrorResponse errorResponse = new ErrorResponse("002", "SHORTENED URL NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }


    }
	
    @GetMapping("/mais-acessadas")
    public List<EncurtadorUrlAcessosModel> listarMaisAcessadas() {
        List<EncurtadorUrl> todasUrls = repository.findAll();


        List<EncurtadorUrl> urlsOrdenadas = todasUrls.stream()
                .sorted((url1, url2) -> Integer.compare(url2.getAcessos(), url1.getAcessos()))
                .limit(10)
                .collect(Collectors.toList());

        return encurtadorUrlAcessoAssembler.toCollectionModel(urlsOrdenadas);
    }
	
	
}
