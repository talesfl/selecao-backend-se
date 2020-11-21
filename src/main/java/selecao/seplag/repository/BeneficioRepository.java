package selecao.seplag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import selecao.seplag.dominio.Beneficio;

public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
	
	Beneficio findByNome(String nome);
	
	Page<Beneficio> findByNomeStartingWith(String nome, Pageable pageable);
	
}
