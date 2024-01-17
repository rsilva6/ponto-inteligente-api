package br.dev.jaysofthouse.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.dev.jaysofthouse.pontointeligente.api.entities.Lancamento;
import br.dev.jaysofthouse.pontointeligente.api.repositories.LancamentoRepository;
import br.dev.jaysofthouse.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	public static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}
	
	@Cacheable("lancamentoPorId")
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lançamento pelo ID {}", id);
		
		// findOne is now findById
		// Use .get after findById to prevent error message
		return Optional.ofNullable(this.lancamentoRepository.findById(id).get());
	}
	
	@CachePut("lancamentoPorId")
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lançamento: {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}
	
	public void remover(Long id) {
		log.info("Removendo o lançamento ID {}", id);
		this.lancamentoRepository.deleteById(id);
	}
}
