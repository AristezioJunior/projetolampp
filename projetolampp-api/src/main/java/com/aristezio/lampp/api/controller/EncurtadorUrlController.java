package com.aristezio.lampp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aristezio.lampp.api.assembler.EncurtadorUrlAssembler;
import com.aristezio.lampp.api.model.EncurtadorUrlModel;
import com.aristezio.lampp.api.model.input.EncurtadorUrlInput;
import com.aristezio.lampp.domain.model.EncurtadorUrl;
import com.aristezio.lampp.domain.repository.EncurtadorUrlRepository;
import com.aristezio.lampp.domain.service.EncurtadorUrlService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/urls")
public class EncurtadorUrlController {

	@Autowired   
	private EncurtadorUrlRepository repository;
	
	@Autowired
	private EncurtadorUrlService service;
	
	private final EncurtadorUrlAssembler encurtadorUrlAssembler;
	
	@GetMapping
	public List<EncurtadorUrlModel> listar() {
		return encurtadorUrlAssembler.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("/{encurtadorUrlId}")
	public ResponseEntity<EncurtadorUrlModel> buscar(@PathVariable Long encurtadorUrlId) {
		return repository.findById(encurtadorUrlId)
				.map(encurtadorUrlAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EncurtadorUrlModel cadastrar(@Valid @RequestBody EncurtadorUrlInput encurtadorUrlInput) {
		EncurtadorUrl novoEncurtadorUrl = encurtadorUrlAssembler.toEntity(encurtadorUrlInput);
		EncurtadorUrl encurtadorUrlCadastrado = service.salvar(novoEncurtadorUrl);
		
		return encurtadorUrlAssembler.toModel(encurtadorUrlCadastrado);
	}
	
	
	
	
}	
