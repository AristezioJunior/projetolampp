package com.aristezio.lampp.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EncurtadorUrl {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Long id;
	
	@Column
	@JsonProperty
	@NotBlank
	@Size(min = 6, max = 254)
	private String url;
	
	@Column
	private String urlOriginal;
	
	@Column(unique = true)
	@JsonProperty
	@Size(min = 4 ,max = 30)
	private String codigo;
	
    @Column(nullable = false)
    private int acessos;
	
	@Transient
	private EstatisticaTempo tempo;


    public void incrementarAcessos() {
        this.acessos++;
    }
	
	
}
