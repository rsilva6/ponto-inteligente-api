package br.dev.jaysofthouse.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.dev.jaysofthouse.pontointeligente.api.entities.Empresa;

@Transactional(readOnly = true)
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByCnpj(String cnpj);
}
