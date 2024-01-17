package br.dev.jaysofthouse.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.dev.jaysofthouse.pontointeligente.api.entities.Empresa;
import br.dev.jaysofthouse.pontointeligente.api.repositories.EmpresaRepository;
import br.dev.jaysofthouse.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	/**
	 * Only add the annotation @Autowired if your repository is a class
	 * If your repository is an interface you don't need add this
	 * https://stackoverflow.com/questions/42907553/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-ap?page=1&tab=scoredesc#tab-top
	 */
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}
}
