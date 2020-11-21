package selecao.seplag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import selecao.seplag.dominio.CategoriaDocumento;

public interface CategoriaDocumentoRepository extends JpaRepository<CategoriaDocumento, Long> {
	
	Page<CategoriaDocumento> findByNomeStartingWith(String nome, Pageable pageable);
}
