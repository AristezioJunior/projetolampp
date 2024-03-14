package com.aristezio.lampp.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aristezio.lampp.domain.exception.NegocioException;
import com.aristezio.lampp.domain.exception.UrlNotFoundException;
import com.aristezio.lampp.domain.model.EncurtadorUrl;
import com.aristezio.lampp.domain.model.EstatisticaTempo;
import com.aristezio.lampp.domain.repository.EncurtadorUrlRepository;

@Service
public class EncurtadorUrlService {

	@Autowired
	private EncurtadorUrlRepository repository;


	@Transactional
	public EncurtadorUrl salvar(EncurtadorUrl encurtadorUrl) {
		Long inicioDoTempo = System.currentTimeMillis();


		//verificando se o codigo já foi usado
        boolean codigoEmUso = repository.findByCodigo(encurtadorUrl.getCodigo())
                .filter(p -> !p.equals(encurtadorUrl))
                .isPresent();
        
        if(codigoEmUso) {
        	 throw new NegocioException("{ERR_CODE: 001, Description:Código ALREADY EXISTS}");
        }
        
        String alias = (encurtadorUrl.getCodigo() != null) ? encurtadorUrl.getCodigo() : geracaoDeCodigoUnico();
        encurtadorUrl.setCodigo(alias);
        encurtadorUrl.setUrlOriginal(encurtadorUrl.getUrl()); 
        encurtadorUrl.setUrl("https://aristezio.ink/" + alias);
        Long finalTempo = System.currentTimeMillis();
        Long resultadoTempo = finalTempo - inicioDoTempo;
        EstatisticaTempo estatistica = new EstatisticaTempo();
        estatistica.setTempoDecorrido(resultadoTempo.toString() + "ms");
        encurtadorUrl.setTempo(estatistica);
        
		return repository.save(encurtadorUrl);
	}
		
	
    private String geracaoDeCodigoUnico() {

        return UUID.randomUUID().toString().substring(0, 7); 
    }
	
    
    public EncurtadorUrl getUrlByCodigo(String codigo) {
    	return repository.findByCodigo(codigo).orElseThrow(
    			()-> new UrlNotFoundException("Url Not Found!")
    	        );
    }
    
}
