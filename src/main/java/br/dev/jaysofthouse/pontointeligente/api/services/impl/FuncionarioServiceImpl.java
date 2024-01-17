package br.dev.jaysofthouse.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.dev.jaysofthouse.pontointeligente.api.entities.Funcionario;
import br.dev.jaysofthouse.pontointeligente.api.repositories.FuncionarioRepository;
import br.dev.jaysofthouse.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	/**
	 * Only add the annotation @Autowired if your repository is a class
	 * If your repository is an interface you don't need add this
	 * https://stackoverflow.com/questions/42907553/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-ap?page=1&tab=scoredesc#tab-top
	 */
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistindo funcionário: {}", funcionario);
		return this.funcionarioRepository.save(funcionario);
	}
	
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}
	
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionário pelo email {}", email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}
	
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionário pelo IDl {}", id);
		
		// findOne is now findById
		// Use .get after findById to prevent error message
		return Optional.ofNullable(this.funcionarioRepository.findById(id).get());
	}
}
